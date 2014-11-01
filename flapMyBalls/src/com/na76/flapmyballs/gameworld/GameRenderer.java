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
		myWorld.getScore().addToScore(GameConstants.SCORE_INCREMENT_PER_FRAME);
		myWorld.getScore().draw(batcher);

		// Draws everything
		List<GameObject> gameObjects = myWorld.getObjectsPool();
		
		drawPlatform(batcher);
		
		for (GameObject gameObject : gameObjects) {
			gameObject.draw(batcher);
		}

	}
	
	private void drawBackground(SpriteBatch batcher){
		
		Spikes topSpikes = myWorld.getTopSpikes();
		Spikes bottomSpikes = myWorld.getBottomSpikes();
		
		batcher.draw(AssetLoader.backGround,0,0);
		
		for(int i = 0; i <= Gdx.graphics.getWidth(); i += topSpikes.getWidth()) {
			batcher.draw(AssetLoader.spike, i, 0, topSpikes.getWidth(), topSpikes.getHeight());
		}

		for(int i = 0; i <= Gdx.graphics.getWidth(); i += bottomSpikes.getWidth()) {
			batcher.draw(AssetLoader.flippedSpike, i, bottomSpikes.getY(), bottomSpikes.getWidth(), bottomSpikes.getHeight());
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
