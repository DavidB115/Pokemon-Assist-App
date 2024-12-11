package com.pokemonapp.model;

import java.util.List;

public class TypeNameListDto {
    private List<String> typeNames;

    public TypeNameListDto() {

    }

    public TypeNameListDto(List<String> typeNames){
        this.typeNames = typeNames;
    }

    public List<String> getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(List<String> typeNames) {
        this.typeNames = typeNames;
    }
}
