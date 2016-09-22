package com.kibou.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kibou.common.domain.Custom;
import com.kibou.common.domain.User;
import com.kibou.common.exception.UserNotFoundException;
import com.kibou.common.repository.IUserRepository;
import com.kibou.common.repository.jdbc.impl.UserRepositoryImpl;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
//	@Resource(name = "memUserRepository")
	@Resource(name = UserRepositoryImpl.COMPONENT_NAME)
	private IUserRepository userRepository; 

	//value=summary , notes=description , ...
	@ApiOperation(value="获取用户列表", notes="",response = List.class,httpMethod="GET")
	@GetMapping("")
	public List<User> listUsers(@RequestParam(defaultValue="0") int start,@RequestParam(defaultValue="10") int limit){
		return userRepository.listUsers(start, limit);
	}
	
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@PostMapping("")
	public User postUser(@RequestBody User user){
		return userRepository.addUser(user);
	}
	
	/*@ApiOperation(value="创建用户", notes="使用名称和年龄创建用户")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String"),
        @ApiImplicitParam(name = "age", value = "用户年龄", required = true, dataType = "int")
	})
	@PostMapping("")
	public User postUser(@RequestParam String name,@RequestParam int age){
		return userRepository.addUser(User.create(null, name, age));
	}*/
	
	
	//XXX 注意如果是@PathVaraible这样在路径上的参数描述 需要加上 paramType="path"
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
	@GetMapping("/{id}")
//  @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
		User user = userRepository.getUser(id);
		if(user == null)
			throw new UserNotFoundException("User with specified id : "+ id + " is not exist");
    	return user;
    }
	
	//e.g :使用Advance REST client
	//设置 POST , Custom	content type : 选中 application/json
	//设置 raw payload :  {"name":"shisj","age":27}
	@PutMapping("/{id}")
	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体", required = true, dataType = "User")
    })
	//@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id,@RequestBody User user){
		//XXX 另外这里的@ReuqestBody和@ResponseBody一样使用到了容器当前使用到的HttpMessageConverter来进行转换,
		//而且当前的请求就会强制要求使用 application/json 的 Content-Type头
		User oldUser = userRepository.getUser(id);
		oldUser.setName(user.getName());
		oldUser.setAge(user.getAge());
		userRepository.updateUser(oldUser);
		return "success";
	}
	
	/*@PutMapping("/{id}")
	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的用户信息来更新用户详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "name", value = "用户详细实体", required = true, dataType = "User"),
		@ApiImplicitParam(name = "age", value = "用户详细实体", required = true, dataType = "User")
	})
	//@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id,@RequestParam String name,@RequestParam int age){
		User user = userRepository.getUser(id);
		if(user == null) //user not exist
			return "fail";
		user.setName(name);
		user.setAge(age);
		userRepository.updateUser(user);
		return "success";
	}*/
	
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id){
		User user = userRepository.getUser(id);
		return userRepository.deleteUser(user) ? "success" : "fail";
	}

	@ApiOperation(value="获取用户顾客列表(未实现)", notes="根据url的id来指定用户对象")
    @GetMapping("/{id}/customers")
    public List<Custom> getUserCustomers(@PathVariable Long id) {
        // ...
    	return null;
    }

}