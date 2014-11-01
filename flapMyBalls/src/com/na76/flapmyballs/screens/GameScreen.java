package com.na76.flapmyballs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.na76.flapmyballs.constants.GameConstants;
import com.na76.flapmyballs.gameworld.GameRenderer;
import com.na76.flapmyballs.gameworld.GameWorld;
import com.na76.flapmyballs.handlers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	
	public GameScreen(){
		Gdx.app.log("Game Screen", "Constructor called");
        world = new GameWorld(GameConstants.GAME_MID_POINT_Y);
        renderer = new GameRenderer(world, (int) GameConstants.GAME_HEIGHT, GameConstants.GAME_MID_POINT_Y);
        Gdx.input.setInputProcessor(new InputHandler(world));
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
