package com.roguelike_sandbox.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.roguelike_sandbox.character.EntityManager;
import com.roguelike_sandbox.input.InputListener;
import com.roguelike_sandbox.world.RoguelikeWorld;

public class RoguelikeSandbox extends ApplicationAdapter {

    private static SpriteBatch batch;
    private static RoguelikeWorld world;
    private static InputListener listener;
    private static EntityManager entityManager;
    private static GameSettings settings;

    public static GameSettings getSettings() {
        return settings;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        listener = new InputListener();
        settings = new GameSettings(1f, 1f);
        world = new RoguelikeWorld(settings);
        // world.generateTileMap();

        entityManager = new EntityManager(batch, world);
    }

    @Override
    public void render() {
        batch.begin();
        ScreenUtils.clear(0, 0, 0, 1);
        // keyboard input
        listener.run(entityManager.getPlayer());
        world.render(entityManager.getPlayer());
        entityManager.runEntities();
        entityManager.renderEntities();
        batch.end();
    }

    @Override
    public void dispose() {
        //TODO: dispose all disposable objects
        batch.dispose();
        world.dispose();
    }
}
