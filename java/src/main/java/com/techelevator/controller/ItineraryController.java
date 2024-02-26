package com.techelevator.controller;

import com.techelevator.dao.ItineraryDao;
import com.techelevator.dao.JdbcItineraryDao;
import com.techelevator.dao.LandmarkDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
import com.techelevator.model.LandmarkDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itinerary")
@PreAuthorize("isAuthenticated()")
public class ItineraryController {

    private ItineraryDao itineraryDao;
    private LandmarkDao landmarkDao;

    public ItineraryController(ItineraryDao itineraryDao, LandmarkDao landmarkDao) {
        this.itineraryDao = itineraryDao;
        this.landmarkDao = landmarkDao;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public List<Itinerary> getItineraries(@PathVariable int userId) {
        return itineraryDao.getItinerariesByUser(userId);
    }

    @RequestMapping(path = "/{itineraryId}/landmark", method = RequestMethod.GET)
    public List<LandmarkDto> getLandmarksByItinerary(@PathVariable int itineraryId) {
        List<LandmarkDto> itineraryLandmarks = new ArrayList<>();

        List<Integer> landmarkIds = itineraryDao.getItineraryLandmarks(itineraryId);
        for(int landmarkId : landmarkIds) {
            itineraryLandmarks.add(landmarkDao.getLandmarkById(landmarkId));
        }

        return itineraryLandmarks;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Itinerary addNewItinerary(@Valid @RequestBody Itinerary newItinerary) {
        try {
            return itineraryDao.createItinerary(newItinerary);
        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(path = "/{itineraryId}", method = RequestMethod.PUT)
    public Itinerary editItinerary(@PathVariable int itineraryId, @RequestBody Itinerary dataItinerary) {
        dataItinerary.setItineraryId(itineraryId);
        try {
            return itineraryDao.updateItinerary(dataItinerary);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/{itineraryId}", method = RequestMethod.DELETE)
    public void deleteItinerary(@PathVariable int itineraryId) {
        itineraryDao.dropItineraryLandmarks(itineraryId);
        itineraryDao.deleteItinerary(itineraryId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{itineraryId}/landmark", method = RequestMethod.POST)
    public List<LandmarkDto> createItineraryLandmarks(@PathVariable int itineraryId, @RequestParam List<Integer> landmarkIds) {
        itineraryDao.addItineraryLandmarks(itineraryId, landmarkIds);

        List<LandmarkDto> itineraryLandmarks = new ArrayList<>();

        landmarkIds = itineraryDao.getItineraryLandmarks(itineraryId);
        for(int landmarkId : landmarkIds) {
            itineraryLandmarks.add(landmarkDao.getLandmarkById(landmarkId));
        }

        return itineraryLandmarks;
    }

    @RequestMapping(path = "/{itineraryId}/landmark", method = RequestMethod.PUT)
    public List<LandmarkDto> editItineraryLandmarks(@PathVariable int itineraryId, @RequestParam List<Integer> landmarkIds) {
        itineraryDao.dropItineraryLandmarks(itineraryId);
        itineraryDao.addItineraryLandmarks(itineraryId, landmarkIds);

        List<LandmarkDto> itineraryLandmarks = new ArrayList<>();

        landmarkIds = itineraryDao.getItineraryLandmarks(itineraryId);
        for(int landmarkId : landmarkIds) {
            itineraryLandmarks.add(landmarkDao.getLandmarkById(landmarkId));
        }

        return itineraryLandmarks;
    }
}
