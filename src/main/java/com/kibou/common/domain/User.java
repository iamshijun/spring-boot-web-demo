package com.kibou.common.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
	private Long id;
	private String name;
	private Integer age;

	public User() {
	}

	public static User create(Long id, String name, Integer age) {
		return new User(id, name, age);
	}
	
	public User(Long id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String toJsonString(){
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) {
		User postUser = User.create(1L, "测试大师", 20);
		System.out.println(postUser.toJsonString());
	}
}
