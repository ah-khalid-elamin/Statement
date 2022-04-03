package com.nagarro.assessments.statement.domains.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<java.util.Date, String> {

    @Override
    public String convertToDatabaseColumn(java.util.Date javaKey) {
        return new SimpleDateFormat("dd.MM.yyyy").format(javaKey);
    }

    @Override
    public java.util.Date convertToEntityAttribute(final String databaseKey) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            return formatter.parse(databaseKey);
        } catch (ParseException e) {
            return null;
        }
    }

}