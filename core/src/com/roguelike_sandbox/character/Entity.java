package com.roguelike_sandbox.character;

public abstract class Entity {

    private final int level;
    private final int vitality;
    private final int constitution;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int luck;
    private final double stamina;

    private double health;
    private double maxHealth;
    private double maxStamina;
    private double dodgeChance;
    private double critChance;

    private double physicDamage;
    private double magicDamage;
    private double physicResistance;
    private double magicResistance;
    private double movementSpeed;

    public Entity(int level, int vitality, int constitution, int strength, int dexterity, int intelligence, int luck) {
        this.level = level;
        this.vitality = vitality;
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.luck = luck;

        calculateStats();

        health = maxHealth;
        stamina = maxStamina;
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

        consoleOutStats();
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

    public abstract void kill();
}