package com.nagarro.assessments.statement.domains.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<java.util.Date, String> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String convertToDatabaseColumn(java.util.Date javaKey) {
		return new SimpleDateFormat("dd.MM.yyyy").format(javaKey);
	}

	@Override
	public Date convertToEntityAttribute(final String databaseKey) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			return formatter.parse(databaseKey);
		} 
		catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}