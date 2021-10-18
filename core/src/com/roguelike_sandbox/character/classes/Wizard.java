package com.roguelike_sandbox.character.classes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.character.EntityTexture;
import com.roguelike_sandbox.character.Player;
import com.roguelike_sandbox.world.RoguelikeWorldBase;

public class Wizard extends Player {

    public Wizard(SpriteBatch batch, TextureAtlas textureAtlas, RoguelikeWorldBase world, Vector2 position, int level) {
        super(batch, textureAtlas, world, position, level, 12, 8, 7, 6, 9, 3, EntityTexture.WIZARD);
    }

    public Wizard(SpriteBatch batch, TextureAtlas textureAtlas, RoguelikeWorldBase world, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        super(batch, textureAtlas, world, position, level, vitality, constitution, strength, dexterity, intelligence, luck, texture);
    }
}
