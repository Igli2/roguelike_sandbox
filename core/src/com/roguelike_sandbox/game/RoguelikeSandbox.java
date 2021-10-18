package com.roguelike_sandbox.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.roguelike_sandbox.character.EntityManager;
import com.roguelike_sandbox.input.InputListener;
import com.roguelike_sandbox.screen.PlayScreen;
import com.roguelike_sandbox.world.RoguelikeWorldBase;
import com.roguelike_sandbox.world.RoguelikeWorldGenerated;
import com.roguelike_sandbox.world.RoguelikeWorldStatic;

public class RoguelikeSandbox extends Game {

    public static final int V_WIDTH = 1080, V_HEIGHT = 720;
    public SpriteBatch batch;
    private static InputListener listener;
    private static EntityManager entityManager;
    private static GameSettings settings;
    private static RoguelikeWorldStatic lobbyWorld;
    private static RoguelikeWorldGenerated generatedWorld;

    public static GameSettings getSettings() {
        return settings;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));

        listener = new InputListener();
        settings = new GameSettings(1f, 1f);
        lobbyWorld = new RoguelikeWorldStatic(settings, "tilemaps/lobby.tmx");
        generatedWorld = new RoguelikeWorldGenerated(settings);

        entityManager = new EntityManager(batch, lobbyWorld);
    }

    @Override
    public void render() {
        super.render();

        batch.begin();

        // keyboard input
        listener.run(entityManager.getPlayer());

        // lobbyWorld.render(entityManager.getPlayer());
        generatedWorld.render(entityManager.getPlayer());
        entityManager.runEntities();
        entityManager.renderEntities();

        batch.end();
    }

    @Override
    public void dispose() {
        //TODO: dispose all disposable objects
        batch.dispose();
    }
}
