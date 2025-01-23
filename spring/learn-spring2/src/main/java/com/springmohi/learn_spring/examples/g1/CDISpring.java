package com.springmohi.learn_spring.examples.g1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.springmohi.learn_spring.game.GameRunner;

import jakarta.inject.Inject;
import jakarta.inject.Named;
@Named
class BusinessService{
	private DataService dataService;
	
	
	public DataService getDataService() {
		return dataService;
	}
	
	@Inject
	public void setDataService(DataService dataService) {
		System.out.println("setter injection");
		this.dataService = dataService;
	}
	
}

@Named
class DataService{
	
	
}

@Configuration
@ComponentScan
public class CDISpring {
	
	public static void main(String[] args) {
		try(var context = new 
				AnnotationConfigApplicationContext(CDISpring.class)){
		
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			System.out.println(context.getBean(BusinessService.class).getDataService());
		}
	}
}