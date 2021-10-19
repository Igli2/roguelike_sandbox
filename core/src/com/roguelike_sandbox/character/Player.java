package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.behaviour.Behaviour;
import com.roguelike_sandbox.input.InputListener;
import com.roguelike_sandbox.world.AbstractRoguelikeWorld;

public abstract class Player extends Entity {

    private final AbstractRoguelikeWorld world;
    private final int experience;
    private final InputListener listener;
    private int experienceNeeded;

    public Player(TextureAtlas textureAtlas, AbstractRoguelikeWorld world, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        super(textureAtlas, world, position, Behaviour.BEHAVIOUR_TYPE.WIZARD, level, vitality, constitution, strength, dexterity, intelligence, luck, texture);
        experience = 0;
        this.world = world;
        listener = new InputListener();
    }

    @Override
    public void update(float dt) {
        world.setCameraPos(getPosition());
        behaviour.AI.update(this, dt);
        move();
    }

    @Override
    public void kill() {

    }
}
