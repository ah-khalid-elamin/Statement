package com.nagarro.assessments.statement.domains.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.nagarro.assessments.statement.domains.util.Crypt;


@Converter(autoApply = false)
public class AccountConverter implements AttributeConverter<String, String> {

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
            //TODO: handle exception
        }
        return encrStr; 
    }

}