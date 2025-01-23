package com.springmohi.learn_spring.examples.a1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.springmohi.learn_spring.game.GameRunner;
@Component
class YourBusinessClass{
	dep1 d1;
	
	dep2 d2;
//@Autowired
public YourBusinessClass(dep1 d1, dep2 d2) {
		super();
		System.out.println("Constructor Injection");
		this.d1 = d1;
		this.d2 = d2;
	}

/*
	@Autowired
	public void setD1(dep1 d1) {
		System.out.println("Setter injection d1");
		this.d1 = d1;
	}



	@Autowired
	public void setD2(dep2 d2) {
		this.d2 = d2;
	}
*/

	public String toString() {
		return "Using"+ d1+ "and"+d2;
	}
}
@Component
class dep1{
	
}
@Component
class dep2{
	
}


@Configuration
@ComponentScan
public class SimpleGamingDependencySpring2 {
	
	public static void main(String[] args) {
		try(var context = new 
				AnnotationConfigApplicationContext(SimpleGamingDependencySpring2.class)){
		
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			System.out.println(context.getBean(YourBusinessClass.class));
		}
	}
}