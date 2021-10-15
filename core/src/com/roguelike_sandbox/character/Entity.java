package com.roguelike_sandbox.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    public static final double GROUND_FRICTION = 0.7D;
    public static final int SPRITE_SIZE = 50;
    private final SpriteBatch batch;
    private final int level;
    private final int vitality;
    private final int constitution;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int luck;
    private final double stamina;
    protected Vector2 position;
    private Sprite sprite;
    private Texture texture;
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
    private double movementSpeed;

    public Entity(SpriteBatch batch, Vector2 position, int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck, EntityTexture texture) {
        this.velocity = new Vector2(0, 0);
        this.position = position;
        this.level = level;
        this.vitality = vitality;
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.luck = luck;

        this.batch = batch;

        setTexture(texture);
        calculateStats();

        health = maxHealth;
        stamina = maxStamina;
    }

    public Entity(SpriteBatch batch, Vector2 position, EntityTexture texture) {//test only
        this.velocity = new Vector2(0, 0);
        this.position = position;
        this.level = 2;
        this.vitality = 2;
        this.constitution = 2;
        this.strength = 2;
        this.dexterity = 2;
        this.intelligence = 2;
        this.luck = 2;

        this.batch = batch;

        setTexture(texture);
        calculateStats();

        health = maxHealth;
        stamina = maxStamina;
    }

    public abstract void run();

    public abstract void kill();


    private void setTexture(EntityTexture texture) {
        this.texture = new Texture(Gdx.files.internal(texture.texture));
        sprite = new Sprite(this.texture, SPRITE_SIZE, SPRITE_SIZE);
        sprite.setPosition(23, 23);
        // sprite.draw(batch);
    }

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

    private void setPosition(Vector2 newPosition) {
        position = newPosition;
        sprite.setPosition(position.x, position.y);
    }

    public void addForce(Vector2 direction) {
        direction.setLength((float) (movementSpeed / 10f));
        velocity = velocity.add(direction);
        if (velocity.len() > movementSpeed / 20f) {
            velocity.setLength((float) movementSpeed);
        }
    }

    public void move() {
        setPosition(position.add(velocity));
        velocity.setLength((float) (velocity.len() * GROUND_FRICTION));
    }

    public void render() {
        sprite.draw(batch);
    }
}