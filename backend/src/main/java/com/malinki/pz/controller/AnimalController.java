package com.malinki.pz.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@WebServlet
@RestController
@RequestMapping(value="/api")            
public class AnimalController {

	@RequestMapping("/animals")
	public List<Animal> getAnimals(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Animal> animals = new ArrayList<>();

		animals.add(new Animal(1, "Pies", 12));
		animals.add(new Animal(2, "Koala", 120));


		doPost(request, response);


		return animals;
	}
	
	private void doGet(HttpServletRequest request){
		System.out.println("lolXXXXXXXX: " + request.getParameter("param"));
	}
	
	private void doPost(HttpServletRequest request, HttpServletResponse response){
		System.out.println("lolPPPPPPPP: " + request.getParameter("value"));
		System.out.println("lolPPPPPPPP2: " + request.getParameter("anotherValue"));

		try {
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			writer.write("Mam 5 gram");
			writer.flush();
			writer.close();
		} catch (Exception e){}
	}
}
