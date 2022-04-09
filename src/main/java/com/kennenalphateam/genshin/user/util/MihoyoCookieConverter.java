package com.kennenalphateam.genshin.user.util;

import com.kennenalphateam.genshin.util.CryptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component
@RequiredArgsConstructor
@Slf4j
public class MihoyoCookieConverter implements AttributeConverter<String, String> {

    private final CryptService cryptService;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return cryptService.encrypt(attribute);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return cryptService.decrypt(dbData);
        } catch (Exception e) {
            return null;
        }
    }
}
