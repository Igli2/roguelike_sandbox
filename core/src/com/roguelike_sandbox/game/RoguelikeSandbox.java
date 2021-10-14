package com.roguelike_sandbox.game;

import com.badlogic.gdx.ApplicationAdapter;
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
        batch = new SpriteBatch();

        listener = new InputListener();
        world = new World();
        world.generateTileMap();

        entityManager = new EntityManager(batch, world);
    }

    @Override
    public void render() {
        batch.begin();
        ScreenUtils.clear(0, 0, 0, 1);
        // keyboard input
        listener.run(null);
        world.render();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
