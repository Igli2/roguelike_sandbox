package com.roguelike_sandbox.behaviour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.character.Entity;

public class PlayerBehaviour implements Behaviour {

    @Override
    public void update(Entity entity, float dt) {
        handleInput(entity, dt);
    }

    private void handleInput(Entity entity, float dt) {
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
    }
}
