package com.pokemonapp.model;

import java.util.List;

public class EffectivenessDto {

    private List<Long> effectivenessValues;

    public EffectivenessDto() {

    }

    public EffectivenessDto(List<Long> effectivenessValues) {
        this.effectivenessValues = effectivenessValues;
    }

    public List<Long> getEffectivenessValues() {
        return effectivenessValues;
    }

    public void setEffectivenessValues(List<Long> effectivenessValues) {
        this.effectivenessValues = effectivenessValues;
    }
}
