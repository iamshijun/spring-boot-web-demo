package com.kibou.common.repository.mem.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.kibou.common.domain.User;
import com.kibou.common.repository.IUserRepository;

@Repository(UserRepositoryMemImpl.COMPONENT_NAME)
public class UserRepositoryMemImpl implements IUserRepository{
	
	public final static String COMPONENT_NAME = "memUserRepository";

	private static ConcurrentMap<Long,User> users;
	private static AtomicLong usersCounter;
	
	static{
		users = new ConcurrentHashMap<>();
		
		users.put(1L, new User(1L,"shijun.shi",27));
		users.put(2L, new User(2L,"washio reina",22));
		users.put(3L, new User(3L,"kayano aiyi",28));
		users.put(4L, new User(4L,"doug walker",24));
		users.put(5L, new User(5L,"john titer",19));
		
		usersCounter = new AtomicLong(users.size());
		
	}
	
	@Override
	public User getUser(Long id){
		return users.get(id);
	}
	
	@Override
	public User addUser(User user){
		if(user == null)
			throw new NullPointerException("User is not given");
		long nextKey = usersCounter.incrementAndGet();
		user.setId(nextKey);
		users.put(nextKey, user);
		return user;
	}
	
	@Override
	public boolean deleteUser(User user){
		if(user == null)
			return false;
		User removed = users.remove(user.getId());
		return removed != null;
	}
	
	@Override
	public void updateUser(User user){
		if(user == null)
			return;
		users.put(user.getId(), user);
	}
	
	@Override
	public List<User> listUsers(int start,int limit){
		if(start > users.size())
			return Collections.emptyList();
		List<User> values = new ArrayList<>(users.values());
		Collections.sort(values,userIdComparator);
		return values.subList(start, Math.min(users.size(), start + limit));
	}
	
	static Comparator<User> userIdComparator = new Comparator<User>() {
		@Override
		public int compare(User o1, User o2) {
			return (int) (o1.getId() - o2.getId());
		}
	};
}
