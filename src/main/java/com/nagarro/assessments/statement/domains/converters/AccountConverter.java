package com.nagarro.assessments.statement.domains.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.assessments.statement.domains.util.Crypt;

@Converter(autoApply = false)
public class AccountConverter implements AttributeConverter<String, String> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String convertToDatabaseColumn(String accountNumber) {
		return accountNumber;
	}

	@Override
	public String convertToEntityAttribute(final String accountNumber) {
		String encrStr = accountNumber;
		try {
			encrStr = Crypt.encrypt(accountNumber, Crypt.encodedBase64Key);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return encrStr;
	}

}