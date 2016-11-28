package com.malinki.pz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnimalController {
	private List<Animal> animals;

	public AnimalController() {
		animals = new ArrayList<>();
		animals.add(new Animal(1, "Pies", 12));
		animals.add(new Animal(2, "Koala", 120));
	}

	public Integer returnAnimalIndex(int id) {
		for (Animal animal : animals) {
			if (animal.getId() == id)
				return animals.indexOf(animal);
		}
		return -1;
	}

	@RequestMapping(value = "/animals", method = RequestMethod.GET)
	public List<Animal> getAnimals() {
		return animals;
	}

	@RequestMapping(value = "/animals", method = RequestMethod.POST)
	public Animal addAnimal(@RequestBody Animal animal) {
		System.out.println(animal.getName());
		animals.add(animal);
		return animal;

	}

	@RequestMapping(value = "/animals/{id}", method = RequestMethod.DELETE, headers="authorization=delete")
	public void remove(@PathVariable("id") int animalId) {
		int index = returnAnimalIndex(animalId);
		if (index != -1)
			animals.remove(index);
		System.out.println(animalId);

	}

	@RequestMapping(value = "/animals/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") int animalId, @RequestBody Animal animal) {
		int index = returnAnimalIndex(animalId);
		if (index != -1) {
			animals.remove(index);
			animals.add(index, animal);
		}

		System.out.println("Update: " + animalId);

	}
}
