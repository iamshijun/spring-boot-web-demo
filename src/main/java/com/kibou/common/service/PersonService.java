package com.kibou.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public PersonService() {
		logger.info("PersonService.<init>()");
	}
}
