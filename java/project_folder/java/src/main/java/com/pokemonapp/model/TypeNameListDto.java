package com.pokemonapp.model;

import java.util.List;

public class TypeNameListDto {
    private List<TypeNameDto> typeNames;

    public TypeNameListDto() {

    }

    public TypeNameListDto(List<TypeNameDto> typeNames){
        this.typeNames = typeNames;
    }

    public List<TypeNameDto> getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(List<TypeNameDto> typeNames) {
        this.typeNames = typeNames;
    }
}
