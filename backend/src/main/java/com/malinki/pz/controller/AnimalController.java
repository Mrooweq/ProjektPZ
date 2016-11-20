package com.malinki.pz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnimalController {

	@RequestMapping("/animals")
	public List<Animal> getAnimals() {
		ArrayList<Animal> animals = new ArrayList<>();
		
		animals.add(new Animal(1, "Pies", 12));
		animals.add(new Animal(2, "Koala", 120));
		
		return animals;
	}
}
