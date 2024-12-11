package com.pokemonapp.dao;

import com.pokemonapp.model.EffectivenessDto;

import java.util.List;

public interface EffectivenessDao {

    EffectivenessDto getEffectivenessByName(String typeName);
    EffectivenessDto calculateEffectiveness(String firstTypeName, String secondTypeName);
}
