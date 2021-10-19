package com.roguelike_sandbox.behaviour;

import com.roguelike_sandbox.behaviour.player.PlayerBehaviour;
import com.roguelike_sandbox.behaviour.player.WizardBehaviour;
import com.roguelike_sandbox.character.Entity;

public abstract class Behaviour {

    public void update(Entity entity, float dt) {
        move(entity, dt);
    }

    public abstract void move(Entity entity, float dt);

    public abstract void attack(Entity entity, float dt);

    public abstract void retreat(Entity entity, float dt);


    public enum BEHAVIOUR_TYPE {
        WIZARD(new WizardBehaviour()),
        PROJECTILE(new ProjectileBehaviour());

        public final Behaviour AI;

        BEHAVIOUR_TYPE(Behaviour AI) {
            this.AI = AI;
        }
    }
}