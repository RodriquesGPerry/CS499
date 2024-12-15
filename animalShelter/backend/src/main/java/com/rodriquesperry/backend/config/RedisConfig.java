package com.rodriquesperry.backend.config;

// Import necessary libraries for configuring Redis and serialization
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuration class for Redis. This class sets up the Redis connection
 * factory and RedisTemplate to facilitate interaction with a Redis database.
 */
@Configuration
public class RedisConfig {

    private final ObjectMapper objectMapper;

    /**
     * Constructor for RedisConfig.
     *
     * @param objectMapper The ObjectMapper instance used for JSON serialization.
     */
    public RedisConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Defines a RedisConnectionFactory bean.
     *
     * @return A LettuceConnectionFactory configured to connect to a Redis server
     *         running on localhost:6379.
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Creates a connection factory pointing to the Redis instance.
        return new LettuceConnectionFactory("localhost", 6379);
    }

    /**
     * Configures and provides a RedisTemplate bean for interacting with Redis.
     *
     * @param connectionFactory The RedisConnectionFactory to establish Redis connections.
     * @return A configured RedisTemplate for performing Redis operations.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // Create a RedisTemplate for Redis operations.
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use GenericJackson2JsonRedisSerializer for value serialization,
        // enabling conversion of objects to and from JSON using the provided ObjectMapper.
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        // Configure serializers for keys, values, and hash keys/values.
        template.setKeySerializer(new StringRedisSerializer()); // Use String serializer for keys.
        template.setValueSerializer(serializer);                // Use JSON serializer for values.
        template.setHashKeySerializer(new StringRedisSerializer()); // Use String serializer for hash keys.
        template.setHashValueSerializer(serializer);               // Use JSON serializer for hash values.

        // Return the fully configured RedisTemplate.
        return template;
    }
}
