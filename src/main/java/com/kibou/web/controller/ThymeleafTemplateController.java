package com.kibou.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafTemplateController {

	@RequestMapping("")
	public String index(ModelMap map){
		map.addAttribute("host","http://www.thymeleaf.org/");
		return "thymeleaf_index";
	}
	
 /* Thymeleaf 默认配置
  * # Enable template caching.
	spring.thymeleaf.cache=true  
	# Check that the templates location exists.
	spring.thymeleaf.check-template-location=true  
	# Content-Type value.
	spring.thymeleaf.content-type=text/html  
	# Enable MVC Thymeleaf view resolution.
	spring.thymeleaf.enabled=true  
	# Template encoding.
	spring.thymeleaf.encoding=UTF-8  
	# Comma-separated list of view names that should be excluded from resolution.
	spring.thymeleaf.excluded-view-names=  
	# Template mode to be applied to templates. See also StandardTemplateModeHandlers.
	spring.thymeleaf.mode=HTML5  
	# Prefix that gets prepended to view names when building a URL.
	spring.thymeleaf.prefix=classpath:/templates/  
	# Suffix that gets appended to view names when building a URL.
	spring.thymeleaf.suffix=.html  
	spring.thymeleaf.template-resolver-order= # Order of the template resolver in the chain. 
	spring.thymeleaf.view-names=  # Comma-separated list of view names that can be resolved.
  */
}
