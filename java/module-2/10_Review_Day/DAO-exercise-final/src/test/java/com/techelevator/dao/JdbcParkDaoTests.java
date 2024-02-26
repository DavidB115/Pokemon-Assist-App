package com.techelevator.dao;

import com.techelevator.model.Park;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcParkDaoTests extends BaseDaoTests {

    private ParkDao dao;

    @Before
    public void setup() {
        dao = new JdbcParkDao(dataSource);
    }

    @Test
    public void getParks_Should_Return_All_Parks() {
        List<Park> parks = dao.getParks();

        assertEquals("Incorrect number of parks returned", 2, parks.size());
        assertEquals("Incorrect state for park at index 0", "Ohio", parks.get(0).getLocation());
        assertEquals("Incorrect state for park at index 1", "Pennsylvania", parks.get(1).getLocation());
    }

}
