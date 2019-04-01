package com.sensiblemetrics.api.sqoola.common.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.pearl.pem.product.fetcher.model.wrapper.segment.Segment;
import edu.emory.mathcs.backport.java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * {@link Segment} converter implementation
 */
@Slf4j
@Component
@Converter
public class SegmentListConverter implements AttributeConverter<List<Segment>, String> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(final List<Segment> segments) {
        try {
            this.mapper.writeValueAsString(Optional.ofNullable(segments).orElseGet(Collections::emptyList));
        } catch (JsonProcessingException e) {
            log.error("ERROR: cannot process input segments={}", StringUtils.join(segments, "|"));
        }
        return null;
    }

    @Override
    public List<Segment> convertToEntityAttribute(final String source) {
        try {
            return this.mapper.reader().forType(new TypeReference<List<Segment>>() {
            }).readValue(source);
        } catch (IOException e) {
            log.error("ERROR: cannot process input source={}", source);
        }
        return Collections.emptyList();
    }
}
