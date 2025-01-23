package com.mohi.springboot.learn_spring_boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:9090/courses		


@RestController
public class CurrencyConfigController {
	@Autowired
	private CurrencyServiceConfig configuration;
	
	@RequestMapping("/currency")
	public CurrencyServiceConfig retrieveCourses(){
		return configuration;
	}
}