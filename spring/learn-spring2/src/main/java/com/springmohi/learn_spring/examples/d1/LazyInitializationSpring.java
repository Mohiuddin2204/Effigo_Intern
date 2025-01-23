package com.springmohi.learn_spring.examples.d1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.springmohi.learn_spring.game.GameRunner;
@Component
class ClassA{
	
}
@Component
@Lazy
class ClassB{
	private ClassA classA;
	public ClassB(ClassA classA) {
		System.out.println("Initialization");
		this.classA=classA;
	}
	public void dosome() {
		System.out.println("doingggg");
	}
	
}

@Configuration
@ComponentScan
public class LazyInitializationSpring {
	
	public static void main(String[] args) {
		try(var context = new 
				AnnotationConfigApplicationContext(LazyInitializationSpring.class)){
		
		System.out.println("Iniiii ::");
		context.getBean(ClassB.class).dosome();
		}
	}
}