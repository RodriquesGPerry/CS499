package com.rodriquesperry.backend.repository;

import com.rodriquesperry.backend.pojo.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoAnimalRepository extends MongoRepository<Animal, String> {
    // Pagination for finding all animals
    Page<Animal> findAll(Pageable pageable);

    // Find an animal by its animalId (mapped to _id in MongoDB)
    Animal findByAnimalId(String id);

    // Find all animals by breed
    List<Animal> findByBreed(String breed);

    // Find all animals of a certain age
    List<Animal> findByAgeUponOutcome(String age);

    // Find all animals by multiple criteria (e.g., breed and age)
    List<Animal> findByBreedAndAgeUponOutcome(String breed, String age);

    // Count animals by breed
    long countByBreed(String breed);
}
