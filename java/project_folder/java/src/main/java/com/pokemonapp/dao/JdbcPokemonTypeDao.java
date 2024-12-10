package com.pokemonapp.dao;

import com.pokemonapp.exception.DaoException;
import com.pokemonapp.model.Type;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

public class JdbcPokemonTypeDao implements PokemonTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPokemonTypeDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
}
