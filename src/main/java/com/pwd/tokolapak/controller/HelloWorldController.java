package com.pwd.tokolapak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class HelloWorldController {
	@GetMapping("/hello")
	public String helloWorld() {
		return "Haloo semua";
	}
	
	@GetMapping("/hello/{name}")
	public String helloName(@PathVariable() String name) {
		return "Hello " + name;
	}
	
	@GetMapping("/angka/{number}")
	public int helloNumber(@PathVariable() int number) {
		return number;
	}
}
