package com.techelevator.dao;

import com.techelevator.model.Itinerary;

import java.util.List;

public interface ItineraryDao {
    List<Itinerary> getItinerariesByUser(int userId);
    Itinerary getItineraryById(int itineraryId);
    List<Integer> getItineraryLandmarks(int itineraryId);
    Itinerary createItinerary(Itinerary newItinerary);
    Itinerary addItineraryLandmarks(int itineraryId, List<Integer> landmarkIds);
    Itinerary updateItinerary(Itinerary dataItinerary);
    void deleteItinerary(int itineraryId);
    void dropItineraryLandmarks(int itineraryId);



}
