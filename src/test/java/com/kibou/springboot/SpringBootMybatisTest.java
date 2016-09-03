package com.kibou.springboot;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.google.common.collect.Maps;
import com.kibou.AbstractSpringBootApplicationTest;
import com.kibou.common.domain.User;
import com.kibou.common.repository.mybatis.impl.UserMapper;

public class SpringBootMybatisTest extends AbstractSpringBootApplicationTest {

	@Autowired
	private UserMapper userMapper;

	@Test
    @Rollback
    public void testUserMapper() throws Exception {
        userMapper.insert("shijun.shi", 27);
        User u = userMapper.findByName("shijun.shi");
        Assert.assertEquals(27, u.getAge().intValue());
        
        u.setAge(30);
        userMapper.update(u);
        u = userMapper.findByName("shijun.shi");
        Assert.assertEquals(30, u.getAge().intValue());
        
        userMapper.delete(u.getId());
        u = userMapper.findByName("shijun.shi");
        Assert.assertEquals(null, u);
        
        Map<String,Object> params = Maps.newHashMap();
        params.put("name","Washio Reina");
        params.put("age",22);
        userMapper.insertByMap(params);
    }
}
