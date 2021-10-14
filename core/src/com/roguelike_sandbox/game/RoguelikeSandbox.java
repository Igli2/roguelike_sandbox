package com.roguelike_sandbox.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.roguelike_sandbox.character.EntityManager;
import com.roguelike_sandbox.input.InputListener;
import com.roguelike_sandbox.world.World;

public class RoguelikeSandbox extends ApplicationAdapter {
	SpriteBatch batch;

    World world;
    InputListener listener;
    EntityManager entityManager;

    @Override
    public void create() {
        entityManager = new EntityManager();
        //entityManager.addPlayer();
        listener = new InputListener();
        batch = new SpriteBatch();

        world = new World();
        world.generateTileMap();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        // keyboard input
        listener.run(null);
        world.render();

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
    }
}
