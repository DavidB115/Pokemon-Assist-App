package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, title, overview, tagline, poster_path, home_page, release_date, length_minutes, director_id, collection_id " +
                     "FROM public.movie;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        return new Movie(-1, "Not implemented yet", "", "", "", "", null, 0, -1, -1);
    }

    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {
        return null;
    }

    @Override
    public List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear,
                                                              int endYear, boolean useWildCard) {
        List<Movie> movies = new ArrayList<>();

        if(useWildCard){
            directorName = "%" + directorName + "%";
        }
        LocalDate startDate = LocalDate.of(startYear, 01, 01);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);
        String sql = "SELECT movie_id, title, overview, tagline, poster_path, movie.home_page, release_date, length_minutes, director_id, collection_id " +
                "FROM movie " +
                "JOIN person ON movie.director_id = person.person_id " +
                "WHERE person_name ILIKE ? AND release_date BETWEEN ? AND ? " +
                "ORDER BY release_date;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, directorName, startDate, endDate);
        while (results.next()){
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    private Movie mapRowToMovie(SqlRowSet sqlRowSet){
        Movie movie = new Movie();
        movie.setId(sqlRowSet.getInt("movie_id"));
        movie.setTitle(sqlRowSet.getString("title"));
        movie.setOverview(sqlRowSet.getString("overview"));
        movie.setTagline(sqlRowSet.getString("tagline"));
        movie.setPosterPath(sqlRowSet.getString("poster_path"));
        movie.setHomePage(sqlRowSet.getString("home_page"));
        if(sqlRowSet.getDate("release_date") != null){
             movie.setReleaseDate(sqlRowSet.getDate("release_date").toLocalDate());
        }
        movie.setLengthMinutes(sqlRowSet.getInt("length_minutes"));
        movie.setDirectorId(sqlRowSet.getInt("director_id"));
        movie.setCollectionId(sqlRowSet.getInt("collection_id"));
        return movie;
    }

}
