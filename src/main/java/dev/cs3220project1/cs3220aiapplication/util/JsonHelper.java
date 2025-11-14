package dev.cs3220project1.cs3220aiapplication.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JsonHelper {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())              // ✅ support Instant, LocalDate, etc.
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // ✅ pretty ISO format
            .enable(SerializationFeature.INDENT_OUTPUT);

    private JsonHelper() {}

    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            return "{\"error\": \"JSON serialization failed\", \"details\": \"" +
                    e.getMessage().replace("\"", "'") + "\"}";
        }
    }
}
