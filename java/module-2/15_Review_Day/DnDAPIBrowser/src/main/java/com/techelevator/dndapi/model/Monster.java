package com.techelevator.dndapi.model;

public class Monster {
    private String index;
    private String name;
    private String size;
    private String type;
    private String alignment;
    //private String armor_class;
    private String hit_points;

    public Monster(){};

    public Monster(String index, String name, String size, String type, String alignment, String hit_points) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.alignment = alignment;

        this.hit_points = hit_points;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }


    public String getHit_points() {
        return hit_points;
    }

    public void setHit_points(String hit_points) {
        this.hit_points = hit_points;
    }
}
