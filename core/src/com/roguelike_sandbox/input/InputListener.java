package com.roguelike_sandbox.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.roguelike_sandbox.character.Player;

public class InputListener {

    public void run(Player p) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
