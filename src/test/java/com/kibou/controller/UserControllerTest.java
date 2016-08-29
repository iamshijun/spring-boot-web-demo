package com.kibou.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kibou.common.domain.User;
import com.kibou.web.controller.UserController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class UserControllerTest {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	
	@Test 
    public void testUserController() throws Exception { 
		
        // 测试UserController 
        RequestBuilder request = null; 

        long userCount = UserController.usersCounter.get();
 
        User expectedUser = User.create(userCount+1, "new_user", 20);
        request = post("/users/") 
                .param("name", expectedUser.getName()) 
                .param("age", expectedUser.getAge()+""); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo(expectedUser.toJsonString()))); 

//        ObjectMapper om = new ObjectMapper();
//        om.convertValue(postUser, Map.class)
        request = get("/users/"); 
        mvc.perform(request) 
                .andExpect(status().isOk()) 
                .andExpect(MockMvcResultMatchers.jsonPath("$.["+userCount+"].id", Matchers.equalTo((expectedUser.getId().intValue())))); 
        
        //modify user
        request = put("/users/" + expectedUser.getId()) 
                .param("name", "user_new") 
                .param("age", "30"); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo("success"))); 
        expectedUser.setName("user_new");
        expectedUser.setAge(30);
        
        request = get("/users/" + (expectedUser.getId())); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo(expectedUser.toJsonString()))); 

        request = delete("/users/1"); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo("success"))); 

        UserController.users.clear();
        // get查一下user列表，应该为空 
        request = MockMvcRequestBuilders.get("/users/"); 
        mvc.perform(request) 
                .andExpect(MockMvcResultMatchers.status().isOk()) 
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]"))); 


    } 

}
