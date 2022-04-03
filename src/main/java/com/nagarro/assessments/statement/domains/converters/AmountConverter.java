package com.nagarro.assessments.statement.domains.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AmountConverter implements AttributeConverter<Double, String> {

	@Override
	public String convertToDatabaseColumn(Double amount) {
		return amount.toString();
	}

	@Override
	public Double convertToEntityAttribute(final String amount) {
		return Double.parseDouble(amount);
	}

}