package com.roguelike_sandbox.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.character.Player;

public class InputListener {

    public void run(Player p) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            p.addForce(new Vector2(0, 1));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            p.addForce(new Vector2(0, -1));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            p.addForce(new Vector2(-1, 0));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            p.addForce(new Vector2(1, 0));
        }
    }
}
