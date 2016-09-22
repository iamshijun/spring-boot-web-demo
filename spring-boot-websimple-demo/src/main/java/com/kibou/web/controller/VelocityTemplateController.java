package com.kibou.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * POM.xml :
 	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-velocity</artifactId>
	</dependency> 
 *
 */
@Controller
@RequestMapping("/velocity")
public class VelocityTemplateController {

	@RequestMapping("")
	public String index(ModelMap map){
		map.addAttribute("host", "https://velocity.apache.org");
		return "velocity_index";
	}
}
