package com.sensiblemetrics.api.sqoola.common.converter;

import com.sensiblemetrics.api.sqoola.common.model.wrapper.SegmentKey;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

/**
 * {@link SegmentKey} converter implementation
 */
@Configuration
public class SegmentConverter {

    @Bean
    @ConfigurationPropertiesBinding
    public String2SegmentKey stringToSegmentKey() {
        return new String2SegmentKey();
    }

    @Bean
    @ConfigurationPropertiesBinding
    public Integer2SegmentKey integerToSegmentKey() {
        return new Integer2SegmentKey();
    }

    public class String2SegmentKey implements Converter<String, SegmentKey> {
        @Override
        public SegmentKey convert(final String source) {
            if (Objects.nonNull(source) && source.length() > 0) {
                return SegmentKey.of(Long.parseLong(source));
            }
            return null;
        }
    }

    public class Integer2SegmentKey implements Converter<Integer, SegmentKey> {
        @Override
        public SegmentKey convert(final Integer source) {
            if (Objects.nonNull(source)) {
                return SegmentKey.of(source.longValue());
            }
            return null;
        }
    }
}
