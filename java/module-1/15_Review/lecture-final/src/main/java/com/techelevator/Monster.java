package com.techelevator;

//This is the Monster class for all monsters in the dungeon. You can Inherit from this class to create specialized versions.
public class Monster extends Creature implements Combatable{
    private String attackDescription; //Each Monster has a different attack
    private int damage; // This will be used to subtract from the hero's health when the monster attacks

    private int Id; //This will be the unique ID of the monster
    static int monsterCount=0; //This keeps track of how many monsters have been created

    public Monster(String name, int health, int defense, String attackDescription, int damage) {
        super(name, health, defense);
        this.attackDescription = attackDescription;
        this.damage = damage;
    }

    public String getAttackDescription() {
        return attackDescription;
    }

    public int getDamage() {
        return damage;
    }

}
