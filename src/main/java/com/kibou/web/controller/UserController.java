package com.kibou.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kibou.common.domain.Custom;
import com.kibou.common.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static ConcurrentMap<Long,User> users;
	public static AtomicLong usersCounter;
	private static Object[] mutexs;
	
	static{
		users = new ConcurrentHashMap<>();
		users.put(1L, new User(1L,"shijun.shi",27));
		users.put(2L, new User(2L,"washio reina",22));
		users.put(3L, new User(3L,"kayano aiyi",28));
		users.put(4L, new User(4L,"doug walker",24));
		users.put(5L, new User(5L,"john titer",19));
		
		usersCounter = new AtomicLong(users.size());
		
		mutexs = new Object[users.size()];
		for(int i = 0 ; i < mutexs.length; ++i){
			mutexs[i] = new Object();
		}
	}

	@GetMapping("")
	public List<User> listUsers(@RequestParam(defaultValue="0") int start,@RequestParam(defaultValue="10") int limit){
		ArrayList<User> list = new ArrayList<>(users.values());
		Collections.sort(list,new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return (int) (o1.getId() - o2.getId());
			}
		});
		return list.subList(start, Math.min(users.size(), start + limit));
	}
	
	@PostMapping("")
	public User addUser(@RequestParam String name,@RequestParam int age){
		long userId = usersCounter.incrementAndGet();
		User newUser = new User(userId, name, age);
		users.put(userId,newUser);
		return newUser;
	}
	
	@GetMapping("/{id}")
//  @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
    	return users.get(id);
    }
	
	@DeleteMapping("/{id}")
	//@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		User removedUser = users.remove(id);
		if(removedUser != null){
			logger.info("{} is deleted",removedUser);
			return "success";
		}
		return "fail";
		//return removedUser;
	}

	@PutMapping("/{id}")
	//@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String modifyUser(@PathVariable Long id,@RequestParam String name,@RequestParam int age){
		User user = users.get(id);
		if (user != null && mutexs != null) {
			Object mutext = mutexs[(int) (id % mutexs.length)];
			synchronized (mutext) {
				user.setName(name);
				user.setAge(age);
				
				users.put(id, user);
			}
		}
		//return user;
		return "success";
	}

    @GetMapping("/{id}/customers")
    List<Custom> getUserCustomers(@PathVariable Long id) {
        // ...
    	return null;
    }

}