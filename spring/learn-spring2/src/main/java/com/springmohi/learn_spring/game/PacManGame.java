package com.springmohi.learn_spring.game;

import org.springframework.stereotype.Component;

@Component
public class PacManGame implements GamingConsole {
	public void up() {
		System.out.println("Jump");
	}
	public void down() {
		System.out.println("Go into hole");
	}
	public void left() {
		System.out.println("Pac Back");
	}
	public void right() {
		System.out.println("Pac Forward");
	}
}
