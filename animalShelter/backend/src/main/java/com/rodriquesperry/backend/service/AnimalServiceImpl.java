package com.rodriquesperry.backend.service;

// Import necessary libraries for the service layer, Redis, MongoDB, and other utilities
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodriquesperry.backend.pojo.Animal;
import com.rodriquesperry.backend.repository.MongoAnimalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private MongoAnimalRepository animalRepository; // Repository for MongoDB CRUD operations

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // Template for interacting with Redis

    @Autowired
    private MongoTemplate mongoTemplate; // Template for more advanced MongoDB operations

    @Autowired
    private ObjectMapper objectMapper; // Inject ObjectMapper for JSON conversion

    private static final String CACHE_PREFIX = "animal:"; // Prefix for Redis cache keys

    /**
     * Initializes indexes on specific fields of the Animal collection for optimized querying.
     */
    @PostConstruct
    public void createIndexes() {
        // Creating indexes for fields: animalType, breed, and ageUponOutcome
        mongoTemplate.indexOps(Animal.class).ensureIndex(new Index().on("animalType", Sort.Direction.ASC));
        mongoTemplate.indexOps(Animal.class).ensureIndex(new Index().on("breed", Sort.Direction.ASC));
        mongoTemplate.indexOps(Animal.class).ensureIndex(new Index().on("ageUponOutcome", Sort.Direction.ASC));
    }

    /**
     * Fetches a paginated list of all animals.
     *
     * @param page The page number for pagination.
     * @param size The size of each page.
     * @return A paginated result of animals.
     */
    @Override
    public Page<Animal> getAllAnimals(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return animalRepository.findAll(pageable);
    }

    /**
     * Retrieves an animal by its ID, checking Redis cache first for efficiency.
     *
     * @param id The unique identifier for the animal.
     * @return The animal with the given ID.
     */
    @Override
    public Animal getAnimalById(String id) {
        String cacheKey = CACHE_PREFIX + id; // Constructing the cache key

        // Check if the animal is present in the cache
        Object cachedAnimal = redisTemplate.opsForValue().get(cacheKey);
        if (cachedAnimal != null) {
            System.out.println("Cache hit: " + cachedAnimal); // Cache hit
            if (cachedAnimal instanceof Animal) {
                return (Animal) cachedAnimal;
            } else {
                return objectMapper.convertValue(cachedAnimal, Animal.class); // Convert to Animal if necessary
            }
        }

        // If not found in cache, retrieve from MongoDB and cache the result
        Animal animal = animalRepository.findByAnimalId(id);
        if (animal != null) {
            redisTemplate.opsForValue().set(cacheKey, animal, 10, TimeUnit.MINUTES); // Cache the animal for 10 minutes
            System.out.println("Cache miss: " + animal); // Cache miss
        }
        return animal;
    }

    /**
     * Saves a new animal to the repository and invalidates its cache entry.
     *
     * @param animal The animal to be saved.
     */
    @Override
    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
        // Invalidate the cache to ensure consistency after saving
        String cacheKey = CACHE_PREFIX + animal.getAnimalId();
        redisTemplate.delete(cacheKey);
    }

    /**
     * Updates an existing animal in the repository and updates its cache.
     *
     * @param id The ID of the animal to update.
     * @param animal The updated animal object.
     */
    @Override
    public void updateAnimal(String id, Animal animal) {
        animal.setAnimalId(id); // Ensure the animal ID is set correctly
        animalRepository.save(animal);
        // Update the cache with the new animal data
        String cacheKey = CACHE_PREFIX + id;
        redisTemplate.opsForValue().set(cacheKey, animal, 10, TimeUnit.MINUTES); // Cache the updated animal for 10 minutes
    }

    /**
     * Deletes an animal from the repository and removes its cache entry.
     *
     * @param id The ID of the animal to delete.
     */
    @Override
    public void deleteAnimal(String id) {
        animalRepository.deleteById(id);
        // Remove the corresponding cache entry
        String cacheKey = CACHE_PREFIX + id;
        redisTemplate.delete(cacheKey);
    }

    /**
     * Retrieves a list of animals filtered by breed.
     *
     * @param breed The breed to filter animals by.
     * @return A list of animals matching the given breed.
     */
    public List<Animal> getAnimalsByBreed(String breed) {
        return animalRepository.findByBreed(breed);
    }

    /**
     * Retrieves a list of animals filtered by age.
     *
     * @param age The age to filter animals by.
     * @return A list of animals matching the given age.
     */
    public List<Animal> getAnimalsByAge(String age) {
        return animalRepository.findByAgeUponOutcome(age);
    }

    /**
     * Counts the number of animals of a specific breed.
     *
     * @param breed The breed to count animals by.
     * @return The number of animals of the given breed.
     */
    public long countAnimalsByBreed(String breed) {
        return animalRepository.countByBreed(breed);
    }

    /**
     * Retrieves a list of animals filtered by both breed and age.
     *
     * @param breed The breed to filter animals by.
     * @param age The age to filter animals by.
     * @return A list of animals matching the given breed and age.
     */
    public List<Animal> getAnimalsByBreedAndAge(String breed, String age) {
        return animalRepository.findByBreedAndAgeUponOutcome(breed, age);
    }
}
