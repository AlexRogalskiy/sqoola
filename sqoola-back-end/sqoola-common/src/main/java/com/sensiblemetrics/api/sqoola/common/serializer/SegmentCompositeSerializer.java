package com.sensiblemetrics.api.sqoola.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import de.pearl.pem.product.fetcher.model.wrapper.segment.Segment;
import de.pearl.pem.product.fetcher.model.wrapper.segment.SegmentKey;
import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonComponent
public class SegmentCompositeSerializer {

    public static class SegmentJsonSerializer extends JsonSerializer<List<Segment>> {

        @Override
        public void serialize(final List<Segment> segments, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartArray();
            final List<Segment> result = Optional.ofNullable(segments).orElseGet(Collections::emptyList);
            for (final Segment segment : result) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeObjectField("segment", segment);
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
    }

    public static class SegmentJsonDeserializer extends JsonDeserializer<List<Segment>> {

        @Override
        public List<Segment> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final List<Segment> result = new ArrayList<>();
            final ArrayNode segments = jsonParser.getCodec().readTree(jsonParser);
            segments.elements().forEachRemaining(segment -> {
                result.add(
                    Segment.builder()
                        .key(SegmentKey.of(segment.get("id").asLong()))
                        .index(segment.get("index").asInt())
                        .build());
            });
            return result;
        }
    }
}
