package com.rodriquesperry.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

@Document(collection = "animalshelter")
public class Animal {

    @Id
    private String animalId;

    @Field("age_upon_outcome") // Aligns the field with the MongoDB field.
    @Indexed // Indexes field for faster searching.
    @NotBlank(message = "Age upon outcome cannot be blank.")
    private String ageUponOutcome;

    @Field("animal_type")
    @Indexed
    @NotBlank(message = "Animal type is required.")
    private String animalType;  // This field can store "Dog", "Cat", "Other."

    @Indexed
    @NotBlank(message = "Breed is required.")
    private String breed;

    @Size(max = 50, message = "Color cannot exceed 50 characters.")
    private String color;

    @Field("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Matches the ISO 8601 format
    private OffsetDateTime dateOfBirth;

    private String datetime;

    @Field("monthyear")
    @NotNull(message = "Month and year is required.")
    private String monthYear;

    @NotNull(message = "Name is required.")
    private String name;

    @Field("outcome_subtype")
    private String outcomeSubtype;

    @Field("outcome_type")
    private String outcomeType;

    @Field("sex_upon_outcome")
    private String sexUponOutcome;

    @Field("age_upon_outcome_in_weeks")
    private Double ageUponOutcomeInWeeks;


    public Animal() {this.animalId = UUID.randomUUID().toString();}

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAgeUponOutcome() {
        return ageUponOutcome;
    }

    public void setAgeUponOutcome(String ageUponOutcome) {
        this.ageUponOutcome = ageUponOutcome;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public OffsetDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(OffsetDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutcomeSubtype() {
        return outcomeSubtype;
    }

    public void setOutcomeSubtype(String outcomeSubtype) {
        this.outcomeSubtype = outcomeSubtype;
    }

    public String getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(String outcomeType) {
        this.outcomeType = outcomeType;
    }

    public String getSexUponOutcome() {
        return sexUponOutcome;
    }

    public void setSexUponOutcome(String sexUponOutcome) {
        this.sexUponOutcome = sexUponOutcome;
    }

    public Double getAgeUponOutcomeInWeeks() {
        return ageUponOutcomeInWeeks;
    }

    public void setAgeUponOutcomeInWeeks(Double ageUponOutcomeInWeeks) {
        this.ageUponOutcomeInWeeks = ageUponOutcomeInWeeks;
    }

}
