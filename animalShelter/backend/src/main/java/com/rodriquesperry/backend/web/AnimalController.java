package com.rodriquesperry.backend.web;

import com.rodriquesperry.backend.pojo.Animal;
import com.rodriquesperry.backend.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// The @RestController annotation combines @Controller and @ResponseBody, indicating that this class
// is a Spring-managed controller that handles web requests and returns data directly in the response body.
@RestController
public class AnimalController {

    // The @Autowired annotation allows Spring to inject the AnimalService implementation at runtime.
    @Autowired
    private AnimalService animalService;

    // A GET endpoint to retrieve all animals.
    // Maps the HTTP GET request for "/animal/all" to this method.
    @GetMapping("/animal/all")
    // ResponseEntity is a class that represents the entire HTTP response. It allows for customized HTTP status code,
    // headers, and body of the response.
    public ResponseEntity<Page<Animal>> getAnimals(
            @RequestParam(defaultValue = "0") int page, // Default page 0
            @RequestParam(defaultValue = "10") int size // Default page size 10
    ) {
        // Calls the service layer to fetch the list of all animals.
        Page<Animal> animalsPage = animalService.getAllAnimals(page, size);
        // Returns the list of animals in the response body with an HTTP 200 (OK) status.
        return new ResponseEntity<>(animalsPage, HttpStatus.OK);
    }


    // A GET endpoint to retrieve a single animal by its ID.
    // Maps the HTTP GET request for "/animal/{id}" to this method, where {id} is a path variable.
    @GetMapping("/animal/{id}")
    //@PathVariable extracts the value of a URI template variable from the incoming request URL and binds it to the
    // method parameter and is more suitable than @RequestParam for REST
    public ResponseEntity<Animal> getAnimal(@PathVariable String id) {
        // Calls the service layer to fetch the animal by its ID.
        Animal animal = animalService.getAnimalById(id);
        // Returns the animal object in the response body with an HTTP 200 (OK) status.
//        return new ResponseEntity<>(animal, HttpStatus.OK);
        return ResponseEntity.ok(animal);
    }

    // A POST endpoint to create a new animal.
    // Maps the HTTP POST request for "/animal" to this method.
    @PostMapping("/animal")
    // @RequestBody deserializes the JSON into a Java object.
    public ResponseEntity<HttpStatus> createAnimal(@Valid @RequestBody Animal animal) {
        // Calls the service layer to save the new animal.
        animalService.saveAnimal(animal);
        // Returns an HTTP 201 (Created) status to indicate successful creation.
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    // A PUT endpoint to update an existing animal by its ID.
    // Maps the HTTP PUT request for "/animal/{id}" to this method, where {id} is a path variable.
    @PutMapping("/animal/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable String id, @Valid @RequestBody Animal animal) {
        // Calls the service layer to update the animal by its ID.
        animalService.updateAnimal(id, animal);
        // Returns the updated contact as conformation to the user that we did make the update, as well as the status
        // code.
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    // A DELETE endpoint to remove an animal by its ID.
    // Maps the HTTP DELETE request for "/animal/{id}" to this method.
    @DeleteMapping("/animal/{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable String id) {
        // Calls the service layer to delete the animal by its ID.
        animalService.deleteAnimal(id);
        // Returns an HTTP 204 (No Content) status to indicate successful deletion with no response body.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
