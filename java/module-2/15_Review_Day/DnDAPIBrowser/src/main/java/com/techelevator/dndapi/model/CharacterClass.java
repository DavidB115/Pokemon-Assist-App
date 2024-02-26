package com.techelevator.dndapi.model;

public class CharacterClass {
    private String index;
    private String name;
    private String hit_die;

    public CharacterClass(){};
    public CharacterClass(String index, String name, String hit_die) {
        this.index = index;
        this.name = name;
        this.hit_die = hit_die;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHit_die() {
        return hit_die;
    }

    public void setHit_die(String hit_die) {
        this.hit_die = hit_die;
    }
}
