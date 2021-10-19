package com.roguelike_sandbox.behaviour.player;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.roguelike_sandbox.Projectile;
import com.roguelike_sandbox.character.Entity;
import com.roguelike_sandbox.character.EntityTexture;

public class WizardBehaviour extends PlayerBehaviour {

    @Override
    public void attack(Entity entity, float dt) {
        new Projectile(new TextureAtlas("images/textureatlas/charactertest.txt"), entity.getAbstractWorld(), entity.getPosition(), entity.getFaceDirection(), EntityTexture.WIZARD, 0, 0);
    }

    @Override
    public void retreat(Entity entity, float dt) {

    }
}