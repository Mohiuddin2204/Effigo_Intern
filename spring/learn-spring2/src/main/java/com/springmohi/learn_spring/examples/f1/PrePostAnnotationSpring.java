package com.springmohi.learn_spring.examples.f1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.springmohi.learn_spring.game.GameRunner;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
class SomeClass{
	private SomeDependency someDependency;
	public SomeClass(SomeDependency someDependency) {
		this.someDependency=someDependency;
		System.out.println("Dependencies");
	}
	@PostConstruct
	public void initialise() {
		someDependency.getReady();
	}
	
	@PreDestroy
	public void cleanup() {
		System.out.println("CleanUp");
	}
}

@Component
class SomeDependency{

	public void getReady() {
		System.out.println("some logic dependency..");
	}
	
}

@Configuration
@ComponentScan
public class PrePostAnnotationSpring {
	
	public static void main(String[] args) {
		try(var context = new 
				AnnotationConfigApplicationContext(PrePostAnnotationSpring.class)){
		
		}
	}
}