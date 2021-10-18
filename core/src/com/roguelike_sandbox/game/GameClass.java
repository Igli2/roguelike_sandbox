package com.roguelike_sandbox.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.roguelike_sandbox.character.EntityManager;
import com.roguelike_sandbox.input.InputListener;
import com.roguelike_sandbox.screen.PlayScreen;
import com.roguelike_sandbox.world.RoguelikeWorldGenerated;
import com.roguelike_sandbox.world.RoguelikeWorldStatic;

public class GameClass extends Game {

    public static final int V_WIDTH = 1080, V_HEIGHT = 720;
    private static InputListener listener;
    private static EntityManager entityManager;
    private static RoguelikeWorldStatic lobbyWorld;
    private static RoguelikeWorldGenerated generatedWorld;
    public SpriteBatch batch;
    public GameSettings settings;

    @Override
    public void create() {
        batch = new SpriteBatch();
        settings = new GameSettings(1f, 1f);


        setScreen(new PlayScreen(this, new RoguelikeWorldStatic(this, "")));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        //TODO: dispose all disposable objects
        batch.dispose();
    }
}
