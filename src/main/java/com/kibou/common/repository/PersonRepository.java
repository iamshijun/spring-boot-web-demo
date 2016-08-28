package com.kibou.common.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {
	public PersonRepository(){
		System.out.println("PersonRepository.PersonRepository()");
	}
}
