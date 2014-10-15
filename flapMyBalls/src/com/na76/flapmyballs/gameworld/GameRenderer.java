package com.na76.flapmyballs.gameworld;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.na76.flapmyballs.gameobjects.GameObject;
import com.na76.flapmyballs.gameobjects.Platform;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.handlers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;

	private OrthographicCamera cam;

	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {

		myWorld = world;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}

	public void render(float runTime) {

		// We draw a black background. This prevents flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);

		AssetLoader.stateTime += Gdx.graphics.getDeltaTime();

		drawBackground();

		// Draws everything
		List<GameObject> gameObjects = myWorld.getObjectsPool();
		
		drawPlatform();
		
		for (GameObject gameObject : gameObjects) {
			gameObject.draw(batcher);
			gameObject.drawBounds(shapeRenderer);
		}
		

	}
	
	private void drawBackground(){
		
		Spikes topSpikes = myWorld.getTopSpikes();
		Spikes bottomSpikes = myWorld.getBottomSpikes();
		
		Rectangle topBounds = topSpikes.getHitbox();
		Rectangle bottomBounds = bottomSpikes.getHitbox();

		batcher.begin();

		batcher.draw(AssetLoader.backGround,0,0);
		
		for(int i = 0; i <= Gdx.graphics.getWidth(); i += topSpikes.getWidth()) {
			batcher.draw(AssetLoader.spike, i, 0, topSpikes.getWidth(), topSpikes.getHeight());
		}

		for(int i = 0; i <= Gdx.graphics.getWidth(); i += bottomSpikes.getWidth()) {
			batcher.draw(AssetLoader.flippedSpike, i, bottomSpikes.getY(), bottomSpikes.getWidth(), bottomSpikes.getHeight());
		}
		
		batcher.end();

		shapeRenderer.begin(ShapeType.Line);
		
		shapeRenderer.rect(topBounds.x, topBounds.y, topBounds.width, topBounds.height);
		shapeRenderer.rect(bottomBounds.x, bottomBounds.y, bottomBounds.width, bottomBounds.height);
		
		shapeRenderer.end();
	}
	
	private void drawPlatform(){
		int len = myWorld.platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = myWorld.platforms.get(i);
			platform.draw(batcher);
			platform.drawBounds(shapeRenderer);
		}
	}
	
}
