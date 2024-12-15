package com.rodriquesperry.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "animalshelter"; // Replace with your database name
    }

    @Override
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new DateToOffsetDateTimeConverter());
        converters.add(new OffsetDateTimeToDateConverter());
        return new MongoCustomConversions(converters);
    }

    // Converter from java.util.Date to java.time.OffsetDateTime
    static class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(Date source) {
            return source.toInstant().atOffset(ZoneOffset.UTC);
        }
    }

    // Converter from java.time.OffsetDateTime to java.util.Date
    static class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {
        @Override
        public Date convert(OffsetDateTime source) {
            return Date.from(source.toInstant());
        }
    }

}
