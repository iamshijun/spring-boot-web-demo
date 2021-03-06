package com.kibou.common.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
public class User implements Serializable{
	
	private static final long serialVersionUID = -4564939067876079885L;
	
	private Long id;
	private String name;
	private Integer age;

	public User() {
	}

	public static User create(String name, Integer age) {
		return new User(null, name, age);
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User){
			User other = (User)obj;
			return Objects.equals(this.id, other.id)
					&& Objects.equals(this.name, other.name)
					 && Objects.equals(this.age, other.age);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id,this.name,this.age);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("name", name)
				.add("age", age)
			.toString();
	}
	
	public static void main(String[] args) {
		User postUser = User.create(1L, "测试大师", 20);
		System.out.println(postUser.toJsonString());
	}
}
