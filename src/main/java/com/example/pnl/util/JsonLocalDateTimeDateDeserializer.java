package com.example.pnl.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDateTime;

public class JsonLocalDateTimeDateDeserializer extends JsonDeserializer <LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        TextNode textNode = objectCodec.readTree(jsonParser);
        String dateString = textNode.textValue();
        return LocalDateTime.parse(dateString, JsonLocalDateTimeFormatter.FORMATTER);
    }
}