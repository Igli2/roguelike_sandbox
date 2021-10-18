package com.roguelike_sandbox.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.roguelike_sandbox.behaviour.Behaviour;

public abstract class Entity extends Sprite {

    public static final float GROUND_FRICTION = 0.7F;
    public static final int SPRITE_SIZE = 50;
    protected final Behaviour.BEHAVIOUR_TYPE behaviour;
    private final int level;
    private final int vitality;
    private final int constitution;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int luck;
    public World world;
    public Body body;
    protected Sprite sprite;
    protected double movementSpeed;
    //physics
    private Vector2 acceleration;
    private double stamina;
    private Vector2 velocity;
    private double health;
    private double maxHealth;
    private double maxStamina;
    private double dodgeChance;
    private double critChance;
    private double charisma;
    private double physicDamage;
    private double magicDamage;
    private double physicResistance;
    private double magicResistance;

    public Entity(TextureAtlas textureAtlas, World world, Vector2 position, Behaviour.BEHAVIOUR_TYPE behaviour, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        this.behaviour = behaviour;
        this.velocity = new Vector2(0, 0);
        this.level = level;
        this.vitality = vitality;
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.luck = luck;

        this.world = world;

        initialise(textureAtlas, texture, position);
    }

    public Entity(TextureAtlas textureAtlas, World world, Vector2 position, Behaviour.BEHAVIOUR_TYPE behaviour, EntityTexture texture) {//test only
        this.behaviour = behaviour;
        this.velocity = new Vector2(0, 0);
        this.level = 2;
        this.vitality = 2;
        this.constitution = 2;
        this.strength = 2;
        this.dexterity = 2;
        this.intelligence = 2;
        this.luck = 2;

        this.world = world;

        initialise(textureAtlas, texture, position);
    }

    private void initialise(TextureAtlas textureAtlas, EntityTexture texture, Vector2 position) {
        calculateStats();

        sprite = textureAtlas.createSprite(texture.texture);
        health = maxHealth;
        stamina = maxStamina;


        BodyDef bd = new BodyDef();
        bd.fixedRotation = true;
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(position);

        body = world.createBody(bd);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(sprite.getWidth());
        fixtureDef.shape = shape;

        body.setActive(true);
        body.setAwake(true);
        body.createFixture(fixtureDef);
        shape.dispose();

        acceleration = new Vector2(0, 0);
    }

    public void update(float dt) {
        behaviour.AI.update(this, dt);
        move();
    }

    public abstract void kill();

    private void calculateStats() {

        //TODO: add item values

        maxHealth = vitality * 10 + constitution * 4 + strength * 2;
        maxStamina = constitution * 10 + dexterity * 5 + vitality * 2;
        dodgeChance = ((float) dexterity * 3f + (float) luck * 2f + (float) intelligence) / 100f; //100 = 1%
        critChance = ((float) dexterity * 2f + (float) luck * 3f + (float) intelligence) / 100f;
        physicDamage = strength * 3 + vitality;
        magicDamage = intelligence * 6 + constitution * 2;
        physicResistance = constitution * 3 + vitality * 2;
        magicResistance = constitution * 2 + vitality * 3;
        movementSpeed = dexterity * 3;

        charisma = ((float) vitality * 3f + (float) constitution * 4f + (float) intelligence * 4f) * (health / maxHealth);

        //consoleOutStats();
    }

    private void consoleOutStats() {

    }

    public int getLevel() {
        return level;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public double getMaxStamina() {
        return maxStamina;
    }

    public double getStamina() {
        return stamina;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public double getCritChance() {
        return critChance;
    }

    public double getPhysicDamage() {
        return physicDamage;
    }

    public double getMagicDamage() {
        return magicDamage;
    }

    public double getPhysicResistance() {
        return physicResistance;
    }

    public double getMagicResistance() {
        return magicResistance;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public double getCharisma() {
        return charisma;
    }

    private void heal(double amount) {
        health += amount;
        if (health >= maxHealth) {
            health = maxHealth;
        }
    }

    private void damage(Entity damager, double amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            kill();

            //TODO: add EXP & GOLD if damager instanceof Player
        }
    }

    private void damagePhysical(Entity damager) {
        damage(damager, (100D / (100D + physicResistance)) * damager.getPhysicDamage());

    }

    private void damageMagical(Entity damager) {
        damage(damager, (100D / (100D + magicResistance)) * damager.getMagicDamage());
    }

    public void addForce(Vector2 direction) {
        direction.setLength((float) movementSpeed * 2);
        acceleration = acceleration.add(direction);
    }

    public void move() {
        accelerate();
        body.setLinearVelocity(velocity);

        sprite.setPosition((body.getPosition().x) - sprite.
                        getWidth() / 2,
                (body.getPosition().y) - sprite.getHeight() / 2);
    }

    private void accelerate() {
        velocity.setLength(velocity.len() * GROUND_FRICTION);
        velocity = velocity.add(acceleration);
        if (velocity.len() > movementSpeed * 20) {
            velocity.setLength((float) movementSpeed * 20);
        }
        acceleration = new Vector2(0, 0);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.
                        getScaleY(), sprite.getRotation());
    }

    public void remove() {
        world.destroyBody(body);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Fixture getFixture() {
        return body.getFixtureList().first();
    }
}