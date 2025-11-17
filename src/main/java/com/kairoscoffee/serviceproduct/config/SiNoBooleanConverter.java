package com.kairoscoffee.serviceproduct.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SiNoBooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return value != null && value ? "S" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        return dbValue != null && dbValue.equalsIgnoreCase("S");
    }
}
