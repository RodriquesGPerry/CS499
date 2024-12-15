package com.rockdigital.animalshelter.repository;

import com.rockdigital.animalshelter.POJO.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalShelterRepository {
    List<Animal> animals = new ArrayList<>();


    public List<Animal> getAnimals() {
        return animals;
    }

    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void updateAnimal(int index, Animal animal) {
        animals.set(index, animal);
    }
}
