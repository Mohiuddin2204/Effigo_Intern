package com.springmohi.learn_spring.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.springmohi.learn_spring.game")
public class AppGamingBasicSpring {
	
	public static void main(String[] args) {
		try(var context = new 
				AnnotationConfigApplicationContext(AppGamingBasicSpring.class)){
		
		context.getBean(GameRunner.class).run();
		}
	}
}