package com.springmohi.learn_spring.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("supercontragamequalifier")
public class SuperContraGame implements GamingConsole {
	public void up() {
		System.out.println("sc Jump");
	}
	public void down() {
		System.out.println("sc Go into hole");
	}
	public void left() {
		System.out.println("sc Back");
	}
	public void right() {
		System.out.println("sc Forward bullet");
	}
}