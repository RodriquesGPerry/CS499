package com.rodriquesperry.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rodriquesperry.backend.pojo.Animal;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

public class JacksonSerializationTest {

    @Test
    public void testOffsetDateTimeSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Animal animal = new Animal();
        animal.setDateOfBirth(OffsetDateTime.now());

        String json = mapper.writeValueAsString(animal);
        System.out.println("Serialized JSON: " + json);

        Animal deserializedAnimal = mapper.readValue(json, Animal.class);
        System.out.println("Deserialized Object: " + deserializedAnimal.getDateOfBirth());
    }
}

