package com.rodriquesperry.backend.config;

// Import necessary libraries for configuring Jackson, logging, and handling date serialization
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class for setting up Jackson ObjectMapper.
 * This configuration customizes the serialization and deserialization of Java objects,
 * particularly for handling Java 8 date and time types.
 */
@Configuration
public class JacksonConfig {

    // Logger to log the configuration process
    private static final Logger logger = LoggerFactory.getLogger(JacksonConfig.class);

    /**
     * Defines a customized ObjectMapper bean.
     *
     * @return A configured ObjectMapper with support for JavaTimeModule and custom date serialization settings.
     */
    @Bean
    public ObjectMapper objectMapper() {
        // Create a new instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Register JavaTimeModule to support Java 8 date/time types (like LocalDate, LocalDateTime)
        objectMapper.registerModule(new JavaTimeModule());

        // Disable serialization of dates as timestamps (default behavior for some date types)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Log that the ObjectMapper has been configured
        logger.info("ObjectMapper configured with JavaTimeModule.");

        // Return the customized ObjectMapper
        return objectMapper;
    }
}
