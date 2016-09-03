package com.kibou.common.repository.jpa.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kibou.common.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Long>{

	//User findById(Long id); // == findOne(Serializable)
	
	User findByName(String name);
	
	@Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
