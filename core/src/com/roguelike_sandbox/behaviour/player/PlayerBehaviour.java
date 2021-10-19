package com.roguelike_sandbox.behaviour.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.behaviour.Behaviour;
import com.roguelike_sandbox.character.Entity;

public abstract class PlayerBehaviour extends Behaviour {

    @Override
    public void retreat(Entity entity, float dt) {
        return;
        //player does not retreat
    }

    @Override
    public void move(Entity entity, float dt) {
        handleMovementInput(entity, dt);
    }

    private void handleMovementInput(Entity entity, float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            entity.addForce(new Vector2(0, dt));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            entity.addForce(new Vector2(0, -dt));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            entity.addForce(new Vector2(-1, dt));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            entity.addForce(new Vector2(1, dt));
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            attack(entity, dt);
        }
    }
}
