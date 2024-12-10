package com.pokemonapp.model;

import com.fasterxml.jackson.databind.jsontype.impl.TypeNameIdResolver;

public class TypeNameDto {

    private int typeId;
    private String typeName;

    public TypeNameDto(){

    }

    public TypeNameDto(int typeId, String typeName){
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
