package com.roguelike_sandbox.character.classes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.character.EntityTexture;
import com.roguelike_sandbox.character.Player;
import com.roguelike_sandbox.world.RogueLikeWorld;

public class Wizard extends Player {

    public Wizard(TextureAtlas textureAtlas, RogueLikeWorld world, Vector2 position) {
        super(textureAtlas, world, position, 1, 12, 8, 7, 6, 9, 3, EntityTexture.WIZARD);
    }

    public Wizard(TextureAtlas textureAtlas, RogueLikeWorld world, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        super(textureAtlas, world, position, level, vitality, constitution, strength, dexterity, intelligence, luck, texture);
    }
}
