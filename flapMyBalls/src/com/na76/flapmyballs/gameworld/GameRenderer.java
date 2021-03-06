package com.na76.flapmyballs.gameworld;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.na76.flapmyballs.constants.GameConstants;
import com.na76.flapmyballs.gameobjects.GameObject;
import com.na76.flapmyballs.gameobjects.Platform;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.handlers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;


	public GameRenderer(GameWorld world) {
		myWorld = world;
	}

	public void render(float runTime, SpriteBatch batcher) {

		// We draw a black background. This prevents flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);

		AssetLoader.stateTime += Gdx.graphics.getDeltaTime();

		drawBackground(batcher);
		myWorld.getTime().count(Gdx.graphics.getDeltaTime());

		if(myWorld.isRunning()){
			myWorld.getScore().addToScore(GameConstants.SCORE_INCREMENT_PER_FRAME);
		}

		// Draws everything
		List<GameObject> gameObjects = myWorld.getObjectsPool();

		if (GameConstants.TOGGLE_PLATFORMS)
			drawPlatform(batcher);
		
		
		for (GameObject gameObject : gameObjects) {
			gameObject.draw(batcher);
		}
		
		drawSpikes(batcher);
		
		myWorld.getScore().draw(batcher);


	}

	private void drawBackground(SpriteBatch batcher){

		batcher.draw(AssetLoader.backGround, 0, 0, GameConstants.GAME_WIDTH,GameConstants.GAME_HEIGHT);

	}

	private void drawSpikes(SpriteBatch batcher) {
		Spikes topSpikes = myWorld.getTopSpikes();
		Spikes bottomSpikes = myWorld.getBottomSpikes();
		
		float spikeDistance = topSpikes.getWidth() - (topSpikes.getWidth() / GameConstants.SPIKE_OVERLAP_X);
		int numberOfSpikesPerScreen = (int) (GameConstants.GAME_WIDTH / spikeDistance);

		for(int i = 0; i < numberOfSpikesPerScreen; i++) {
			batcher.draw(topSpikes.getTexture(), i*spikeDistance, topSpikes.getY(), topSpikes.getWidth(), topSpikes.getHeight());
			batcher.draw(bottomSpikes.getTexture(), i*spikeDistance, bottomSpikes.getY(), bottomSpikes.getWidth(), bottomSpikes.getHeight());
		}

	}

	private void drawPlatform(SpriteBatch batcher){
		int len = myWorld.platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = myWorld.platforms.get(i);
			platform.draw(batcher);
		}
	}



}
