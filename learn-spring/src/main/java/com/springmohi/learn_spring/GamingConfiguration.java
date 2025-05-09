package com.springmohi.learn_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springmohi.learn_spring.game.GameRunner;
import com.springmohi.learn_spring.game.GamingConsole;
import com.springmohi.learn_spring.game.PacManGame;

@Configuration
public class GamingConfiguration {
	@Bean
	public GamingConsole game() {
		var game =new PacManGame();
		return game;
	}
	
	@Bean
	public GameRunner gameRunner(GamingConsole game) {
		var gameRunner =new GameRunner(game);
		return gameRunner;
	}

}