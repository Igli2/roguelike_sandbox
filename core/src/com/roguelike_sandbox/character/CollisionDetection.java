package com.roguelike_sandbox.character;

import com.badlogic.gdx.physics.box2d.*;

public class CollisionDetection implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        System.out.println("CONTACT");
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
