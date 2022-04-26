package com.learning.hellowordservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
	
	@GetMapping(value = "/hello-world")
	public String getHelloworld() {
		System.out.println("Called");
		return "Hello world";
	}

}
