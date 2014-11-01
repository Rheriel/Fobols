package com.na76.flapmyballs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.na76.flapmyballs.constants.GameConstants;
import com.na76.flapmyballs.gameworld.GameRenderer;
import com.na76.flapmyballs.gameworld.GameWorld;
import com.na76.flapmyballs.handlers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	
	public GameScreen(){
		Gdx.app.log("Game Screen", "Constructor called");
		cam = new OrthographicCamera();
		cam.setToOrtho(true, (int) GameConstants.GAME_WIDTH, (int) GameConstants.GAME_HEIGHT);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
		world = new GameWorld(GameConstants.GAME_MID_POINT_Y);
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler(world));
	}

	@Override
	public void render(float delta) {		
		runTime += delta;
        world.update(delta);
        batcher.begin();
        renderer.render(runTime, batcher);
        batcher.end();
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
		batcher.dispose();

	}

}
