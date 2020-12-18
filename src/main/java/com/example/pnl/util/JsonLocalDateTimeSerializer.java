package com.example.pnl.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

public class JsonLocalDateTimeSerializer extends JsonSerializer <LocalDateTime>{

    @Override
    public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider serializerProvider)
            throws IOException {
        String dateString = date.format(JsonLocalDateTimeFormatter.FORMATTER);
        generator.writeString(dateString);
    }
}