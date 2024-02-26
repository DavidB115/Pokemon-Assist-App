package com.techelevator;
//This will be the parent class for all creatures in the dungeon (Hero, Monster, etc...
//This class cannot be instantiated.
public abstract class Creature {
    private String name; //The name of the creature
    private int health; //How much health does the creature have
    private int defense; //Useless Defense Rating for now until attacks have a chance to miss. At this point all attacks automatically hit.

    public Creature(String name, int health, int defense) {
        this.name = name;
        this.health = health;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }
}
