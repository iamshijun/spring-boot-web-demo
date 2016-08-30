package com.kibou.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * POM.xml
 	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-freemarker</artifactId>
	</dependency>
 *
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerTemplateController {
	@RequestMapping("")
	public String index(ModelMap map) {
		map.addAttribute("host", "http://freemarker.org/");
		return "freemarker_index";
	}
}
