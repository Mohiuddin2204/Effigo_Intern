package com.springmohi.learn_spring.helloworld;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppSpringHelloWorld {

	public static void main(String[] args) {
		//Launch a spring context
		try(var context=new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)){
				//configure things that we want spring manage
				//@Configuration
				//Retrieving beans of spring
				System.out.println(context.getBean("name"));
				System.out.println(context.getBean("age"));
				System.out.println(context.getBean("person"));
				System.out.println(context.getBean("person2MethodCall"));
				System.out.println(context.getBean("person3parameters"));
				System.out.println(context.getBean(Address.class));
				//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
				System.out.println(context.getBean("pi"));		
				
		}
		
		}
}