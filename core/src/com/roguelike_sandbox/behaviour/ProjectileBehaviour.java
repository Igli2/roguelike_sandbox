package com.roguelike_sandbox.behaviour;

import com.roguelike_sandbox.Projectile;
import com.roguelike_sandbox.character.Entity;

public class ProjectileBehaviour extends Behaviour {

    @Override
    public void move(Entity entity, float dt) {
        if(entity instanceof Projectile) {
            Projectile p = (Projectile) entity;
            p.setVelocity(p.getProjectileVelocity());
        }
    }

    @Override
    public void attack(Entity entity, float dt) {

    }

    @Override
    public void retreat(Entity entity, float dt) {

    }
}
