package com.techelevator;
//This is the Room Class.
//You can Inherit from this class to create specialized versions
public class Room {
    private String description;
    private int Id; //This is the unique ID of the room
    static int roomCount=0; //This keeps track of how many room objects have been instantiated

    public Room(String description) {
        this.description = description;
        roomCount++;
        Id = roomCount;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return Id;
    }

}
