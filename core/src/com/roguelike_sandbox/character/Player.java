package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.world.RoguelikeWorld;

public abstract class Player extends Entity {

    RoguelikeWorld world;
    int experience;
    int experienceNeeded;

    public Player(SpriteBatch batch, TextureAtlas textureAtlas, RoguelikeWorld world, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        super(batch, textureAtlas, world.getBox2DWorld(), position, level, vitality, constitution, strength, dexterity, intelligence, luck, texture);
        experience = 0;
        this.world = world;
    }

    @Override
    public void run() {
        world.setCameraPos(body.getPosition());
        move();
    }

    @Override
    public void kill() {

    }
}
