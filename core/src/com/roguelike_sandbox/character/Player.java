package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    int experience;
    int experienceNeeded;

    public Player(SpriteBatch batch, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        super(batch, position, level, vitality, constitution, strength, dexterity, intelligence, luck, texture);
    }

    @Override
    public void kill() {

    }
}
