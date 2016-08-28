package com.kibou.java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kibou.common.repository.PersonRepository;

@Configuration
public class RepoConf {

	@Bean
	public PersonRepository personRepository(){
		return new PersonRepository();
	}
	
	public RepoConf() {
		System.out.println("RepoConf.RepoConf()");
	}
	
	@Configuration
	static class ResourceConf {
		
		public ResourceConf(PersonRepository repository) {//@Autowired
			System.out.println("WebAppConf.ResourceConf.ResourceConf()..");
			System.out.println("PersonRepository" + repository); //这里如果@Configuration存在含有参数的构造函数,参数会在beanFactory中查找
		}
	}
}
