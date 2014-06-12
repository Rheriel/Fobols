package com.na76.flapmyballs.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.na76.flapmyballs.gameobjects.Dude;
import com.na76.flapmyballs.gameobjects.Dude.State;
import com.na76.flapmyballs.handlers.AssetLoader;

public class GameRenderer {

	private static final float DUDE_INITIAL_X = 20;

	private static final float DUDE_INITIAL_Y = 20;

	private static final float DUDE_RESIZE_WIDTH = 22;

	private static final float DUDE_RESIZE_HEIGHT = 27;

	private GameWorld myWorld;

	private OrthographicCamera cam;

	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	private static float spikesScaleWidth = AssetLoader.spikes.getWidth() * .5f;
	private static float spikesScaleHeight = AssetLoader.spikes.getHeight() * .5f;



	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {

		myWorld = world;

		// The word "this" refers to this instance.
		// We are setting the instance variables' values to be that of the
		// parameters passed in from GameScreen.
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}

	public void render(float runTime) {

		/*
		 * 1. We draw a black background. This prevents flickering.
		 */

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		AssetLoader.stateTime += Gdx.graphics.getDeltaTime();

		AssetLoader.currentDudeFrame = AssetLoader.dudeWalkLeftAnimation.getKeyFrame(AssetLoader.stateTime, true);

		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);

		// End ShapeRenderer
		shapeRenderer.end();

		// Begin SpriteBatch
		batcher.begin();

		// Draw background.
		batcher.draw(AssetLoader.backGround,0,0);

		// Draw top spikes.
		for(int i = 0; i <= Gdx.graphics.getWidth(); i += spikesScaleWidth) {
			batcher.draw(AssetLoader.spikes, i, 0, spikesScaleWidth, spikesScaleHeight, AssetLoader.spikes.getWidth(),AssetLoader.spikes.getHeight(), AssetLoader.spikes.getWidth(), AssetLoader.spikes.getHeight(), false, false);
		}

		// Draw bottom spikes.
		for(int i = 0; i <= Gdx.graphics.getWidth(); i += spikesScaleWidth) {
			batcher.draw(AssetLoader.spikes, i, gameHeight - spikesScaleHeight, spikesScaleHeight, spikesScaleWidth, AssetLoader.spikes.getWidth(),AssetLoader.spikes.getHeight(),AssetLoader.spikes.getWidth(), AssetLoader.spikes.getHeight(), false, true);
		}

		// Draw dude animation.
		drawBola();

		// End SpriteBatch
		batcher.end();

	}

	private void drawBola(){

		Dude bola = myWorld.getDude();

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			while (AssetLoader.stateTime > AssetLoader.RUNNING_FRAME_DURATION) {
				AssetLoader.stateTime -= AssetLoader.RUNNING_FRAME_DURATION;
				AssetLoader.currentDudeFrame = bola.isFacingLeft() ? AssetLoader.dudeWalkLeftAnimation.getKeyFrame(bola.getStateTime(), true) : AssetLoader.dudeWalkRightAnimation.getKeyFrame(bola.getStateTime(), true);
			}
		}
		
		batcher.draw(AssetLoader.currentDudeFrame, bola.getX(),bola.getY(),bola.getWidth(),bola.getHeight());
	}

}
