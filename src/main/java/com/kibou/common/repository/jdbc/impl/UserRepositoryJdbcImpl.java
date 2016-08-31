package com.kibou.common.repository.jdbc.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kibou.common.domain.User;
import com.kibou.common.repository.IUserRepository;

@Repository(UserRepositoryJdbcImpl.COMPONENT_NAME)
public class UserRepositoryJdbcImpl implements IUserRepository {

	public final static String COMPONENT_NAME = "jdbcUserRepository";
	
	@Override
	public List<User> listUsers(int start, int limit) {
		return null;
	}

	@Override
	public void updateUser(User user) {
		
	}

	@Override
	public boolean deleteUser(User user) {
		return false;
	}

	@Override
	public User addUser(User user) {
		return null;
	}

	@Override
	public User getUser(Long id) {
		return null;
	}

}
