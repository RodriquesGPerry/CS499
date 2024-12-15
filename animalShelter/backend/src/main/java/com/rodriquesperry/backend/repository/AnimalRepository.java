package com.rodriquesperry.backend.repository;

import com.rodriquesperry.backend.pojo.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// The @Repository annotation marks this class as a repository,
// a specialization of the @Component annotation used for data access logic.
@Repository
public class AnimalRepository {

    // A private field that stores a list of Animal objects.
    // This simulates a database in memory for storing animal data.
    private List<Animal> animals = new ArrayList<>();

    // A getter method to retrieve the list of animals.
    public List<Animal> getAnimals() { return animals; }

    // A method to retrieve a specific Animal object from the list by its index.
    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    // A method to add a new Animal object to the list.
    // This simulates saving a record in a database.
    public void saveAnimal(Animal animal) {
        animals.add(animal);
    }

    // A method to update an existing Animal object in the list at the specified index. Simulates updating a record in
    // a database.
    public void updateAnimal(int index, Animal animal) {
        animals.set(index, animal);
    }

    // A method to delete an Animal object from the list based on the specified index. Simulates deleting a record in
    // a database.
    public void deleteAnimal(int index) {
        animals.remove(index);
    }
}
