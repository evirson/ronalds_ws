package br.com.vetorsistemas.ronalds_ws.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToSmallIntConverter implements AttributeConverter<Boolean, Short> {

    @Override
    public Short convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) return null;
        return (short) (attribute ? 1 : 0);
    }

    @Override
    public Boolean convertToEntityAttribute(Short dbData) {
        if (dbData == null) return null;
        return dbData != 0;
    }
}

