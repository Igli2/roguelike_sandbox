package com.roguelike_sandbox.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.roguelike_sandbox.game.RoguelikeSandbox;

import java.awt.*;

public class Hud {

    public Stage stage;
    private Viewport viewport;
    private Label health;
    private Label experience;
    private Label level;

    public Hud(SpriteBatch batch) {
        viewport = new FitViewport(RoguelikeSandbox.V_WIDTH, RoguelikeSandbox.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();table.setFillParent(true);

        health = new Label();
    }

    public void update(float health, float experience, int level) {//float: range [0,1] so 1 is 100%

    }
}