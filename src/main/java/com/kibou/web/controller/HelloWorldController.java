package com.kibou.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@RequestMapping //ROOT, localhost:8080
	public String home(){
		return "Hello World";
	}
}
