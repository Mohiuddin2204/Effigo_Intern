package com.springmohi.learn_spring.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	private GamingConsole game;
	public GameRunner(@Qualifier("supercontragamequalifier") GamingConsole game) {
		this.game=game;
	}
	public void run() {
		System.out.println("Running :"+game);
		game.down();
		game.up();
		game.left();
		game.right();
	}
}