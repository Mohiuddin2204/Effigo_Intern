package com.springmohi.learn_spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springmohi.learn_spring.game.GameRunner;
import com.springmohi.learn_spring.game.GamingConsole;
import com.springmohi.learn_spring.game.MarioGame;
import com.springmohi.learn_spring.game.PacManGame;
import com.springmohi.learn_spring.game.SuperContraGame;

public class AppGamingBasicSpring {

	public static void main(String[] args) {
		try(var context = new 
				AnnotationConfigApplicationContext(GamingConfiguration.class)){
		
		context.getBean(GamingConsole.class).up();
		context.getBean(GameRunner.class).run();
		}
	}
}