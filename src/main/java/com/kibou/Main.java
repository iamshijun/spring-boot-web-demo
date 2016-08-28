package com.kibou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		
//		SpringApplication springApplication = new SpringApplicationBuilder(Parent.class)  // or .sources(Parent.class)
//				.child(Application.class).build();

//		springApplication.run(args);
		
		SpringApplication.run(Main.class, args);
	}
}
