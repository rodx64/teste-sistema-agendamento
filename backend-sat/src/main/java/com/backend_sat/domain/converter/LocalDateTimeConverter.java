package com.backend_sat.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {
        return (attribute == null) ? null : attribute.format(formatter);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : LocalDateTime.parse(dbData, formatter);
    }
}
