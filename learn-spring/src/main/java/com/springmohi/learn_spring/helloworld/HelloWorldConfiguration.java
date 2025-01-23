package com.springmohi.learn_spring.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name,int age) {};
record Address(String line,String city) {};
record PersonInfo(String name,Address add) {};
@Configuration
public class HelloWorldConfiguration {
	@Bean
	public String name() {
		return "Mohiuddin";
	}
	@Bean
	public int age() {
		return 20;
	}
	@Bean
	public Person person() {
		return new Person("Ravi",55);
	}
	@Bean
	public Person person2MethodCall() {
		return new Person(name(),age());
	}
	
	@Bean
	public Person person3parameters(String name,int age) {
		return new Person(name,age);
	}
	
	@Bean
	@Primary
	public Address a1() {
		return new Address("kokapet","hyd");
	}
	
	@Bean
	public PersonInfo pi(String name,@Qualifier("address2qualifier") Address add) {
		return new PersonInfo(name,add);
	}
	@Bean
	@Qualifier("address2qualifier")
	public Address a2() {
		return new Address("mandi bazar","wgl");
	}
}