package com.roguelike_sandbox.behaviour;

import com.roguelike_sandbox.character.Entity;

public interface Behaviour {

    void update(Entity entity, float dt);

    enum BEHAVIOUR_TYPE {
        PLAYER(new PlayerBehaviour());

        public final Behaviour AI;

        BEHAVIOUR_TYPE(Behaviour AI) {
            this.AI = AI;
        }
    }
}