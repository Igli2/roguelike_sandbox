package com.roguelike_sandbox;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.behaviour.Behaviour;
import com.roguelike_sandbox.character.Entity;
import com.roguelike_sandbox.character.EntityTexture;
import com.roguelike_sandbox.world.AbstractRoguelikeWorld;

public class Projectile extends Entity {

    private int physDamage;
    private int magDamage;
    private Vector2 velocity;

    public Projectile(TextureAtlas textureAtlas, AbstractRoguelikeWorld abstractWorld, Vector2 position, Vector2 velocity, EntityTexture texture, int physDamage, int magDamage) {
        super(textureAtlas, abstractWorld, position, Behaviour.BEHAVIOUR_TYPE.PROJECTILE, texture);
        this.physDamage = physDamage;
        this.magDamage = magDamage;
        this.velocity = velocity;
    }

    @Override
    public void kill() {

    }

    public int getPhysDamage() {
        return physDamage;
    }

    public int getMagDamage() {
        return magDamage;
    }

    public Vector2 getProjectileVelocity() {
        return velocity;
    }
}
