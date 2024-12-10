package com.pokemonapp.dao;

import com.pokemonapp.exception.DaoException;
import com.pokemonapp.model.EffectivenessDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
public class JdbcEffectivenessDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEffectivenessDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
}
