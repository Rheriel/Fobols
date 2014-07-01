package com.na76.flapmyballs.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameobjects.Bola.State;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.handlers.AssetLoader;

public class GameRenderer {

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

		// Begin SpriteBatch
		batcher.begin();

		// Draw background.
		batcher.draw(AssetLoader.backGround,0,0);

		// Draws top and bottom spikes
		drawSpikes();

		// Draw bola animation.
		drawBola();

		// End SpriteBatch
		batcher.end();
		
		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Line);
		
		drawBolaBounds();
		drawSpikesBounds();

		// End ShapeRenderer
		shapeRenderer.end();

	}

	private void drawSpikesBounds() {
		Rectangle bounds = myWorld.getTopSpikes().getHitbox();
		drawBounds(bounds);
		bounds = myWorld.getBottomSpikes().getHitbox();
		drawBounds(bounds);
	}

	private void drawBounds(Rectangle bounds) {
		shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	private void drawBolaBounds() {
		Rectangle bounds = myWorld.getBola().getHitbox();
		drawBounds(bounds);
	}

	// TODO Refactor:
	// This should belong to the Spikes class instead.
	private void drawSpikes() {

		Spikes topSpikes = myWorld.getTopSpikes();
		Spikes bottomSpikes = myWorld.getBottomSpikes();

		// Draw top spikes.
		for(int i = 0; i <= Gdx.graphics.getWidth(); i += Spikes.SCALED_SPIKES_WIDTH) {
			batcher.draw(AssetLoader.spikes, i, 0, topSpikes.getWidth(), topSpikes.getHeight());
		}

		// Draw bottom spikes.
		for(int i = 0; i <= Gdx.graphics.getWidth(); i += Spikes.SCALED_SPIKES_WIDTH) {
			batcher.draw(AssetLoader.spikes, i, bottomSpikes.getY(), bottomSpikes.getWidth(), bottomSpikes.getHeight(), AssetLoader.spikes.getWidth(), AssetLoader.spikes.getHeight(),AssetLoader.spikes.getWidth(), AssetLoader.spikes.getHeight(), false, true);
		}
	}

	// TODO Refactor:
	// This must belong to Bola's draw method instead.
	private void drawBola(){

		Bola bola = myWorld.getBola();

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			bola.setFacingLeft(true);
			bola.setState(State.WALKING);
			while (AssetLoader.stateTime > AssetLoader.RUNNING_FRAME_DURATION) {
				AssetLoader.stateTime -= AssetLoader.RUNNING_FRAME_DURATION;
				AssetLoader.currentDudeFrame = bola.isFacingLeft() ? AssetLoader.dudeWalkLeftAnimation.getKeyFrame(bola.getStateTime(), true) : AssetLoader.dudeWalkRightAnimation.getKeyFrame(bola.getStateTime(), true);
			}
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			bola.setFacingLeft(false);
			bola.setState(State.WALKING);
			while (AssetLoader.stateTime > AssetLoader.RUNNING_FRAME_DURATION) {
				AssetLoader.stateTime -= AssetLoader.RUNNING_FRAME_DURATION;
				AssetLoader.currentDudeFrame = bola.isFacingLeft() ? AssetLoader.dudeWalkLeftAnimation.getKeyFrame(bola.getStateTime(), true) : AssetLoader.dudeWalkRightAnimation.getKeyFrame(bola.getStateTime(), true);
			}
		} else if ((Gdx.input.isKeyPressed(Keys.LEFT)) && (Gdx.input.isKeyPressed(Keys.RIGHT)) ||
				((!Gdx.input.isKeyPressed(Keys.LEFT)) && (!Gdx.input.isKeyPressed(Keys.RIGHT)))) {
			bola.setState(State.IDLE);
			AssetLoader.currentDudeFrame = bola.isFacingLeft() ? AssetLoader.dudeWalkLeftFrames[0] : AssetLoader.dudeWalkRightFrames[0];
		}

		batcher.draw(AssetLoader.currentDudeFrame, bola.getX(),bola.getY(),bola.getWidth(),bola.getHeight());

	}

}
