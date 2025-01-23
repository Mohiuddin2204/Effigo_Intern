package com.springmohi.learn_spring;

import com.springmohi.learn_spring.game.GameRunner;
import com.springmohi.learn_spring.game.MarioGame;
import com.springmohi.learn_spring.game.PacManGame;
import com.springmohi.learn_spring.game.SuperContraGame;

public class AppGamingBasic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//var game =new MarioGame();
		//var game=new SuperContraGame();
		var game=new PacManGame();
		var gameRunner = new GameRunner(game);
		//object creation + wiring of dependencies
		//game is dependency of gamerunner
		gameRunner.run();
	}
}