package com.pokemonapp.dao;

import com.pokemonapp.model.Type;
import com.pokemonapp.model.TypeNameDto;

import java.util.List;

public interface PokemonTypeDao {

    List<TypeNameDto> getAllTypeNames();
    TypeNameDto getTypeNameById();
}
