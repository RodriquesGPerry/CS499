package com.rockdigital.animalshelter.POJO;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public class Animal {
    @NotBlank(message = "This field cannot be blank.")
    private String ageUponOutcome;
    @NotBlank(message = "This field cannot be blank.")
    private String animalId;
    @NotBlank(message = "This field cannot be blank.")
    private String animalType;  // This field can store "Dog", "Cat", "Other"
    @NotBlank(message = "This field cannot be blank.")
    private String breed;
    @NotBlank(message = "This field cannot be blank.")
    private String color;
    @NotBlank(message = "This field cannot be blank.")
    private String dateOfBirth;
    @NotBlank(message = "This field cannot be blank.")
    private String datetime;
    @NotBlank(message = "This field cannot be blank.")
    private String monthYear;
    @NotBlank(message = "This field cannot be blank.")
    private String name;
    @NotBlank(message = "This field cannot be blank.")
    private String outcomeSubtype;
    @NotBlank(message = "This field cannot be blank.")
    private String outcomeType;
    @NotBlank(message = "This field cannot be blank.")
    private String sexUponOutcome;
    private Double locationLat;
    private Double locationLong;
    private Double ageUponOutcomeInWeeks;

//    public Animal(
//            String ageUponOutcome, String animalType, String breed, String color,
//            String dateOfBirth,String datetime, String monthYear, String name, String outcomeSubtype, String outcomeType,
//            String sexUponOutcome, Double locationLat, Double locationLong, Double ageUponOutcomeInWeeks
//                  ) {
//        this.animalType = animalType;
//        this.breed = breed;
//        this.color = color;
//        this.dateOfBirth = dateOfBirth;
//        this.datetime = datetime;
//        this.monthYear = monthYear;
//        this.name = name;
//        this.outcomeSubtype = outcomeSubtype;
//        this.outcomeType = outcomeType;
//        this.sexUponOutcome = sexUponOutcome;
//        this.locationLat = locationLat;
//        this.locationLong = locationLong;
//        this.ageUponOutcomeInWeeks = ageUponOutcomeInWeeks;
//        this.ageUponOutcome = ageUponOutcome;
//    }


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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(Double locationLong) {
        this.locationLong = locationLong;
    }

    public Double getAgeUponOutcomeInWeeks() {
        return ageUponOutcomeInWeeks;
    }

    public void setAgeUponOutcomeInWeeks(Double ageUponOutcomeInWeeks) {
        this.ageUponOutcomeInWeeks = ageUponOutcomeInWeeks;
    }

}
