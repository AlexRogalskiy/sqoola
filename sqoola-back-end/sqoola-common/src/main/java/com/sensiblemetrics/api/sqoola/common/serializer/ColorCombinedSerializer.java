package com.sensiblemetrics.api.sqoola.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;

import java.awt.*;
import java.io.IOException;

@JsonComponent
public class ColorCombinedSerializer {

    public static class UserJsonSerializer extends JsonSerializer<User> {

        @Override
        public void serialize(final User user, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("favoriteColor", getColorAsWebColor(user.getFavoriteColor()));
            jsonGenerator.writeEndObject();
        }

        private static String getColorAsWebColor(final Color color) {
            int r = (int) Math.round(color.getRed() * 255.0);
            int g = (int) Math.round(color.getGreen() * 255.0);
            int b = (int) Math.round(color.getBlue() * 255.0);
            return String.format("#%02x%02x%02x", r, g, b);
        }
    }

    public static class UserJsonDeserializer extends JsonDeserializer<User> {

        @Override
        public User deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            final TextNode favoriteColor = (TextNode) treeNode.get("favoriteColor");
            return new User(Color.decode(favoriteColor.asText()));
        }
    }
}
