package com.techelevator;

//This is the Hero Class for the dungeon. The class is marked Final since this is the only version of Hero that should exist.
public final class Hero extends Creature implements Combatable {
    private String heroProfession; //so far the hero only has one member variable. The user can enter any string value for this. (It doesn't matter yet).
    public Hero(String name, int health, int defense, String heroProfession) {
        super(name, health, defense);
        this.heroProfession = heroProfession;
    }

    public String getHeroProfession() {
        return heroProfession;
    }
    public String getAttackDescription() {
        return "You attack with your rubber duck!"; //To be Combatable we must have this method
    }

    public int getHeroDamage(){
        return 1;
    }

}
