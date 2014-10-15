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
	
	public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final float SCREEN_HEIGHT =  Gdx.graphics.getHeight();
	public static final float GAME_WIDTH = 136;
	public static final float GAME_HEIGHT = SCREEN_HEIGHT  / (SCREEN_WIDTH / GAME_WIDTH);
	public static final int GAME_MID_POINT_Y = (int) (GAME_HEIGHT / 2);
	public static final float GAME_VELOCITY = 20;
	
	public GameScreen(){
		
        world = new GameWorld(GAME_MID_POINT_Y);
        renderer = new GameRenderer(world, (int) GAME_HEIGHT, GAME_MID_POINT_Y);
        
        Gdx.input.setInputProcessor(new InputHandler(world.getBola()));
        
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
