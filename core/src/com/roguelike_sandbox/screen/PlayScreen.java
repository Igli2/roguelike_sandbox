package com.roguelike_sandbox.screen;

import com.badlogic.gdx.Screen;
import com.roguelike_sandbox.game.GameClass;
import com.roguelike_sandbox.scenes.Hud;
import com.roguelike_sandbox.world.AbstractRoguelikeWorld;

public class PlayScreen implements Screen {

    private final Hud hud;
    private final GameClass game;
    private final AbstractRoguelikeWorld world;

    public PlayScreen(GameClass game, AbstractRoguelikeWorld world) {
        hud = new Hud(world.getPlayer());
        this.game = game;
        this.world = world;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        game.batch.begin();
        world.render(delta);
        game.batch.end();
        hud.render();
    }

    @Override
    public void resize(int width, int height) {
        world.resizeViewport(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
