package com.pokemonapp.dao;

import com.pokemonapp.exception.DaoException;
import com.pokemonapp.model.Type;
import com.pokemonapp.model.TypeNameDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

public class JdbcPokemonTypeDao implements PokemonTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPokemonTypeDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<TypeNameDto> getAllTypeNames() {
        return null;
    }

    @Override
    public TypeNameDto getTypeNameById() {
        return null;
    }
}
