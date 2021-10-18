package com.roguelike_sandbox.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.roguelike_sandbox.game.RoguelikeSandbox;
import com.roguelike_sandbox.scenes.Hud;

public class PlayScreen implements Screen {

    private Hud hud;
    private RoguelikeSandbox game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public PlayScreen(RoguelikeSandbox game) {
        hud = new Hud(null);
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(RoguelikeSandbox.V_WIDTH, RoguelikeSandbox.V_HEIGHT, gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
