package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.character.classes.Wizard;
import com.roguelike_sandbox.world.RoguelikeWorld;

import java.util.ArrayList;

public class EntityManager {

    private final Player player;
    private final TextureAtlas textureAtlas;
    ArrayList<Entity> entities = new ArrayList<>();

    public EntityManager(SpriteBatch batch, RoguelikeWorld world) {
        textureAtlas = new TextureAtlas("images/textureatlas/charactertest.txt");
        player = new Wizard(batch, textureAtlas, world, new Vector2(1000, 1000), 1);
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

    public void runEntities() {
        for (Entity all : entities) {
            all.run();
        }
    }

    public void renderEntities() {
        for (Entity all : entities) {
            all.render();
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