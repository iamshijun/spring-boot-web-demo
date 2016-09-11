package com.kibou.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kibou.common.exception.JunkWordException;

@RestController
public class HelloWorldController {
	
	@RequestMapping("/") //
	// PS: Cautious!!!! XXX
	// @RequestMapping("")  如果配置成这样的话 好像和 servlet中的ulr-pattern=/那样拦截所有的请求
	// 所以致使静态资源的请求都被拦截,所有都会走这个请求里面
	public String home(){
		return "Hello World";
	}
	
	@RequestMapping("/junk/{name}")
	public String json(@PathVariable String name) throws JunkWordException{
		throw new JunkWordException(name + "is junkword"); 
	}
	
	@RequestMapping("/hello/{name}")
	public String sayHello(@PathVariable String name) throws JunkWordException{
		return "Hello : " + name;
	}
}

