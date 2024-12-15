package com.rockdigital.animalshelter.controller;

import com.rockdigital.animalshelter.POJO.Animal;
import com.rockdigital.animalshelter.service.AnimalShelterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnimalShelterController {

    AnimalShelterService animalShelterService;

    public AnimalShelterController(AnimalShelterService animalShelterService) {
        this.animalShelterService = animalShelterService;
    }

    @GetMapping("/")
    public String getAnimals(Model model) {
        model.addAttribute("animals", animalShelterService.getAnimals());
        return "animals";
    }

    @GetMapping("/form")
    public String animalForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("animal", animalShelterService.getAnimalFromId(id));
        return "form";
    }


    @PostMapping("/submitAnimal")
    public String submitForm(@Valid Animal animal, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "form";

        String status = animalShelterService.handleSubmit(animal);

        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/";
    }
}
