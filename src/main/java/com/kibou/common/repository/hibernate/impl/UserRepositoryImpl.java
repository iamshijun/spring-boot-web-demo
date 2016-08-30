package com.kibou.common.repository.hibernate.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kibou.common.domain.User;
import com.kibou.common.repository.IUserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements IUserRepository {

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

	public User getUser(Long id) {
		return null;
	}

}
