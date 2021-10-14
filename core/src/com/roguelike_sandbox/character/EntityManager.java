package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.roguelike_sandbox.character.classes.Wizard;
import com.roguelike_sandbox.world.World;

public class EntityManager {

    Player player;

    public EntityManager(SpriteBatch batch, World world) {
        player = new Wizard(batch, world, new Vector2(1000, 1000), 1);
    }
}