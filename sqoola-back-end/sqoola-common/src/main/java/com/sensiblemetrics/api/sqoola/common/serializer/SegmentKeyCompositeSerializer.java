package com.sensiblemetrics.api.sqoola.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import de.pearl.pem.product.fetcher.model.wrapper.segment.SegmentKey;
import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonComponent
public class SegmentKeyCompositeSerializer {

    public static class SegmentKeyJsonSerializer extends JsonSerializer<List<SegmentKey>> {

        @Override
        public void serialize(final List<SegmentKey> segments, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartArray();
            final List<SegmentKey> result = Optional.ofNullable(segments).orElseGet(Collections::emptyList);
            for (final SegmentKey segment : result) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeObjectField("segment", segment);
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
    }

    public static class SegmentKeyJsonDeserializer extends JsonDeserializer<List<SegmentKey>> {

        @Override
        public List<SegmentKey> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final List<SegmentKey> result = new ArrayList<>();
            final ArrayNode segments = jsonParser.getCodec().readTree(jsonParser);
            segments.elements().forEachRemaining(segment -> result.add(SegmentKey.of(segment.get("id").asLong())));
            return result;
        }
    }
}
