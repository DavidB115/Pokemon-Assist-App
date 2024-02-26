package com.techelevator.model;

public class Itinerary {

    private int itineraryId;
    private int userId;
    private String itineraryName;
    private String startingAddress;

    public Itinerary(){

    }

    public Itinerary(int itineraryId, int userId, String itineraryName, String startingAddress) {
        this.itineraryId = itineraryId;
        this.userId = userId;
        this.itineraryName = itineraryName;
        this.startingAddress = startingAddress;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public String getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(String startingAddress) {
        this.startingAddress = startingAddress;
    }
}
