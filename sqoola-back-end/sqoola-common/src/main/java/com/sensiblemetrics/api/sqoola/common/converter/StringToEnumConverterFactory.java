package com.sensiblemetrics.api.sqoola.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    private static class StringToEnumConverter<T extends Enum> implements Converter<String, T> {

        private Class<T> enumType;

        public StringToEnumConverter(final Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(final String source) {
            return (T) Enum.valueOf(this.enumType, source.trim());
        }
    }

    @Override
    public <T extends Enum> Converter<String, T> getConverter(final Class<T> targetType) {
        return new StringToEnumConverter(targetType);
    }
}
