package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Itinerary;
import com.techelevator.model.LandmarkDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcItineraryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Itinerary> getItinerariesByUser(int userId) {
        List<Itinerary> listOfUsersItineraries = new ArrayList<>();

        String sqlSelectQuery = (
                "SELECT itinerary_id, user_id, itinerary_name, starting_address \n" +
                        "FROM itineraries " +
                        "WHERE user_id = ?"
        );

        try {
            SqlRowSet resultsFromQuery = jdbcTemplate.queryForRowSet(sqlSelectQuery, userId);
            while(resultsFromQuery.next()) {
                listOfUsersItineraries.add(mapRowToItinerary(resultsFromQuery));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return listOfUsersItineraries;
    }

    @Override
    public Itinerary getItineraryById(int itineraryId) {
        Itinerary useItinerary = null;
        String sqlQuery = "SELECT itinerary_id, user_id, itinerary_name, starting_address " +
                            "FROM itineraries " +
                            "WHERE itinerary_id = ?";

        try {
            SqlRowSet resultsFromQuery = jdbcTemplate.queryForRowSet(sqlQuery, itineraryId);
            if(resultsFromQuery.next()) {
                useItinerary = mapRowToItinerary(resultsFromQuery);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return useItinerary;
    }

    @Override
    public List<Integer> getItineraryLandmarks(int itineraryId) {
        List<Integer> landmarkIds = new ArrayList<>();
        String sqlQuery = "SELECT landmark_id FROM itinerary_landmarks WHERE itinerary_id = ?";

        try {
            SqlRowSet resultsFromQuery = jdbcTemplate.queryForRowSet(sqlQuery, itineraryId);
            while(resultsFromQuery.next()) {
                landmarkIds.add(resultsFromQuery.getInt("landmark_id"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return landmarkIds;
    }


    @Override
    public Itinerary createItinerary(Itinerary newItinerary) {
        Itinerary createdItinerary = null;
        String sqlQuery = "INSERT INTO itineraries (user_id, itinerary_name, starting_address) " +
                            "VALUES (?,?,?) " +
                            "RETURNING itinerary_id";

        try {
            int createdItineraryId = jdbcTemplate.queryForObject(
                    sqlQuery,
                    int.class,
                    newItinerary.getUserId(),
                    newItinerary.getItineraryName(),
                    newItinerary.getStartingAddress()
            );
            createdItinerary = getItineraryById(createdItineraryId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch(DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return createdItinerary;
    }

    //TODO: V V V Change Return Type! V V V
    @Override
    public Itinerary addItineraryLandmarks(int itineraryId, List<Integer> landmarkIds) {
        Itinerary changedItinerary = getItineraryById(itineraryId);
        String sqlQuery = "INSERT INTO itinerary_landmarks (itinerary_id, landmark_id) " +
                            "VALUES (?,?)";
        try {
            for(int landmarkId: landmarkIds) {
                jdbcTemplate.update(sqlQuery, itineraryId, landmarkId);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch(DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return changedItinerary;
    }

    @Override
    public Itinerary updateItinerary(Itinerary dataItinerary) {
        Itinerary updatedItinerary = null;
        String sqlQuery = "UPDATE itineraries SET itinerary_name = ?, starting_address = ? WHERE itinerary_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sqlQuery, dataItinerary.getItineraryName(), dataItinerary.getStartingAddress(), dataItinerary.getItineraryId());
            if (rowsAffected == 0) {
                throw new DaoException("Nothing was changed.");
            }
            updatedItinerary = getItineraryById(dataItinerary.getItineraryId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedItinerary;
    }

    @Override
    public void deleteItinerary(int itineraryId) {
        String sqlQuery = "DELETE FROM itineraries WHERE itinerary_id = ?";
        jdbcTemplate.update(sqlQuery, itineraryId);
    }

    @Override
    public void dropItineraryLandmarks(int itineraryId) {
        String sqlQuery = "DELETE FROM itinerary_landmarks WHERE itinerary_id = ?";
        jdbcTemplate.update(sqlQuery, itineraryId);
    }

    public Itinerary mapRowToItinerary(SqlRowSet resultsToMap){
        Itinerary itineraryToCreate = new Itinerary();

        itineraryToCreate.setItineraryId(resultsToMap.getInt("itinerary_id"));
        itineraryToCreate.setUserId(resultsToMap.getInt("user_id"));
        itineraryToCreate.setItineraryName(resultsToMap.getString("itinerary_name"));
        itineraryToCreate.setStartingAddress(resultsToMap.getString("starting_address"));

        return itineraryToCreate;
    }
}
