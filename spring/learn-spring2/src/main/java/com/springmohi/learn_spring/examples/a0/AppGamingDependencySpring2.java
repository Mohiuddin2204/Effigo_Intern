package com.springmohi.learn_spring.examples.a0;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.springmohi.learn_spring.game.GameRunner;

@Configuration
@ComponentScan
public class AppGamingDependencySpring2 {
	
	public static void main(String[] args) {
		try(var context = new 
				AnnotationConfigApplicationContext(AppGamingDependencySpring2.class)){
		
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}
	}
}