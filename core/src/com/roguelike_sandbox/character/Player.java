package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.behaviour.Behaviour;
import com.roguelike_sandbox.input.InputListener;
import com.roguelike_sandbox.world.RogueLikeWorld;

public abstract class Player extends Entity {

    private final RogueLikeWorld world;
    private final int experience;
    private final InputListener listener;
    private int experienceNeeded;

    public Player(TextureAtlas textureAtlas, RogueLikeWorld world, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        super(textureAtlas, world.getBox2DWorld(), position, Behaviour.BEHAVIOUR_TYPE.PLAYER, level, vitality, constitution, strength, dexterity, intelligence, luck, texture);
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
