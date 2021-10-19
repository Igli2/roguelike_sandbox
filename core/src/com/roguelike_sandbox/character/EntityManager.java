package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.character.classes.Wizard;
import com.roguelike_sandbox.game.GameClass;
import com.roguelike_sandbox.world.AbstractRoguelikeWorld;

import java.util.ArrayList;

public class EntityManager {

    private final GameClass game;
    private final Player player;
    private final TextureAtlas textureAtlas;
    ArrayList<Entity> entities = new ArrayList<>();

    public EntityManager(GameClass game, AbstractRoguelikeWorld world) {
        this.game = game;
        textureAtlas = new TextureAtlas("images/textureatlas/charactertest.txt");
        player = new Wizard(textureAtlas, world, new Vector2(1000, 1000));
        addEntity(player);
    }

    public void addEntity(Entity e) {
        if (!entities.contains(e)) {
            entities.add(e);
        }
    }

    public void removeEntity(Entity e) {
        if (containsEntity(e)) {
            entities.remove(e);
        }
    }

    public boolean containsEntity(Entity e) {
        return entities.contains(e);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void update(float dt) {
        for (Entity all : entities) {
            all.update(dt);
        }
    }

    public void render() {
        for (Entity all : entities) {
            all.draw(game.batch);
        }
    }

    public void removeAll() {
        for (Entity e : entities) {
            e.remove();
        }
        entities = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }
}