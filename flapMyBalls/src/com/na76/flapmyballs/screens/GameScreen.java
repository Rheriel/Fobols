package com.na76.flapmyballs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.na76.flapmyballs.gameworld.GameRenderer;
import com.na76.flapmyballs.gameworld.GameWorld;
import com.na76.flapmyballs.handlers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	
	public GameScreen(){
		
		float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();      
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        
        int midPointY = (int) (gameHeight / 2);
        int midPointX = (int) (gameWidth / 2);

        world = new GameWorld(midPointX, midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);
        
        Gdx.input.setInputProcessor(new InputHandler(world.getDude()));
        
	}

	@Override
	public void render(float delta) {
		runTime += delta;
        world.update(delta);
        renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing!");
	}

	@Override
	public void show() {
		System.out.println("GameScreen - showing!");
	}

	@Override
	public void hide() {
		System.out.println("GameScreen - hiding!");
	}

	@Override
	public void pause() {
		System.out.println("GameScreen - pausing!");
	}

	@Override
	public void resume() {
		System.out.println("GameScreen - resuming!");
	}

	@Override
	public void dispose() {
		// Leave blank
	}

}
