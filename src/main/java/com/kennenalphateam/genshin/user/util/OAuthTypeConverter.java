package com.kennenalphateam.genshin.user.util;

import com.kennenalphateam.genshin.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OAuthTypeConverter implements AttributeConverter<OAuthType, String> {

    @Override
    public String convertToDatabaseColumn(OAuthType attribute) {
        if (attribute == null)
            return null;
        return attribute.name();
    }

    @Override
    public OAuthType convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData))
            return null;
        return OAuthType.valueOf(dbData);
    }
}
