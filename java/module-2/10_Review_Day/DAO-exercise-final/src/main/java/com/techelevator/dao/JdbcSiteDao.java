package com.techelevator.dao;

import com.techelevator.model.Site;
import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Site> getSitesWithRVAccessByParkId(int parkId) {

        List<Site> sites = new ArrayList<>();
        String sql = "SELECT site_id, s.campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities FROM site s "
                + "INNER JOIN campground c ON c.campground_id = s.campground_id "
                + "INNER JOIN park p ON p.park_id = c.park_id "
                + "WHERE max_rv_length > 0 "
                + "AND p.park_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
            while (results.next()) {
                Site site = mapRowToSite(results);
                sites.add(site);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sites;
    }

    @Override
    public List<Site> getSitesAvailableTodayByParkId(int parkId) {

        List<Site> sites = new ArrayList<>();
        String sql = "SELECT site_id, s.campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities "
                + "FROM site s "
                + "INNER JOIN campground c ON c.campground_id = s.campground_id "
                + "WHERE park_id = ? "
                + "AND site_id NOT IN ( "
                + "SELECT site_id FROM reservation "
                + "WHERE CURRENT_DATE <= to_date AND CURRENT_DATE >= from_date)";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
            while (results.next()) {
                Site site = mapRowToSite(results);
                sites.add(site);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sites;
    }

    private Site mapRowToSite(SqlRowSet results) {
        Site site = new Site();
        site.setSiteId(results.getInt("site_id"));
        site.setCampgroundId(results.getInt("campground_id"));
        site.setSiteNumber(results.getInt("site_number"));
        site.setMaxOccupancy(results.getInt("max_occupancy"));
        site.setAccessible(results.getBoolean("accessible"));
        site.setMaxRvLength(results.getInt("max_rv_length"));
        site.setUtilities(results.getBoolean("utilities"));
        return site;
    }
}
