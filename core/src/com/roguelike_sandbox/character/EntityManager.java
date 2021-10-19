package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.Projectile;
import com.roguelike_sandbox.character.classes.Wizard;
import com.roguelike_sandbox.game.GameClass;
import com.roguelike_sandbox.world.AbstractRoguelikeWorld;

import java.util.ArrayList;

public class EntityManager {

    private final GameClass game;
    private Player player;
    private final TextureAtlas textureAtlas;
    ArrayList<Entity> entities = new ArrayList<>();
    ArrayList<Projectile> projectiles = new ArrayList<>();

    public EntityManager(GameClass game) {
        this.game = game;
        textureAtlas = new TextureAtlas("images/textureatlas/charactertest.txt");
    }

    public void addPlayer(AbstractRoguelikeWorld world) {
        player = new Wizard(textureAtlas, world, new Vector2(1000, 1000));
    }

    public void addEntity(Entity e) {
        if (!(e instanceof Projectile)) {
            if (!entities.contains(e)) {
                entities.add(e);
            }
        } else {
            if (!projectiles.contains(e)) {
                projectiles.add((Projectile) e);
            }
        }
    }

    public void removeEntity(Entity e) {
        if (!(e instanceof Projectile)) {
            if (containsEntity(e)) {
                entities.remove(e);
            }
        } else {
            if (containsProjectile((Projectile) e)) {
                projectiles.remove(e);
            }
        }
    }

    public boolean containsEntity(Entity e) {
        return entities.contains(e);
    }

    public boolean containsProjectile(Projectile e) {
        return projectiles.contains(e);
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
        for (Projectile all : projectiles) {
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