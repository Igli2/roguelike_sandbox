package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.world.RoguelikeWorld;

public abstract class Player extends Entity {

    RoguelikeWorld world;
    int experience;
    int experienceNeeded;

    public Player(SpriteBatch batch, RoguelikeWorld world, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        super(batch, position, level, vitality, constitution, strength, dexterity, intelligence, luck, texture);
        this.world = world;
    }

    public void run() {
        world.setCameraPos(position);
    }

    @Override
    public void kill() {

    }
}
