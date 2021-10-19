package com.roguelike_sandbox.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.roguelike_sandbox.character.Player;
import com.roguelike_sandbox.game.GameClass;

import java.awt.*;

public class Hud {

    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Player player;

    public Hud(Player player) {
        this.player = player;
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(GameClass.V_WIDTH, GameClass.V_HEIGHT, new OrthographicCamera());
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawHealth();
        drawStamina();
        shapeRenderer.end();
    }

    private void drawHealth() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(
                width * 0.059f, height - height * 0.101f, width * 0.201f, height * 0.037f);
        shapeRenderer.setColor(Color.RED);

        player.setHealth(20);

        shapeRenderer.rect(
                width * 0.06f, height - height * 0.1f, (float) player.getHealth() / (float) player.getMaxHealth() * width * 0.2f, height * 0.035f);
    }

    private void drawStamina() {
        if (true) return;

        //TODO: create real stamina drawing
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(
                width * 0.059f, height - height * 0.101f, width * 0.201f, height * 0.037f);
        shapeRenderer.setColor(Color.RED);

        player.setHealth(20);

        shapeRenderer.rect(
                width * 0.06f, height - height * 0.1f, (float) player.getHealth() / (float) player.getMaxHealth() * width * 0.2f, height * 0.035f);
    }
}