package com.malinki.pz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;;

@WebServlet
@RestController
@RequestMapping(value="/api")            
public class AnimalController {

	@RequestMapping("/animals")
	public List<Animal> getAnimals(HttpServletRequest request) {
		ArrayList<Animal> animals = new ArrayList<>();

		animals.add(new Animal(1, "Pies", 12));
		animals.add(new Animal(2, "Koala", 120));
		
		System.out.println("lolXXXXXXXX: " + request.getParameter("param"));
		
		return animals;
	}
}
