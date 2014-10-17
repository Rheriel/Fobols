package com.na76.flapmyballs;

import com.badlogic.gdx.Game;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.screens.GameScreen;
import com.na76.flapmyballs.screens.SplashScreen;

public class FlapMyBalls extends Game {

	@Override
	public void create() {
		System.out.println("Flappy Balls created!");
		AssetLoader.load();
		setScreen(new SplashScreen());
//		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}
	
}
