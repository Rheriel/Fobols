package com.na76.flapmyballs.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.interfaces.GameObject;
import com.na76.flapmyballs.screens.GameScreen;


public class GameWorld {

	public static final int BOLA_WIDTH = 22;
	public static final int BOLA_HEIGHT = 26;


	private Bola bola;
	private Spikes topSpikes;
	private Spikes bottomSpikes;
	
	private Rectangle worldBounds;

	private List<GameObject> gameObjectsPool; 

	public GameWorld(int midPointY){

		bola = new Bola(Bola.STARTING_X, midPointY - 5,BOLA_WIDTH, BOLA_HEIGHT);
		

		int topSpikesWidth = AssetLoader.spike.getRegionWidth() / 2;
		int topSpikesHeight = AssetLoader.spike.getRegionHeight() / 2;
		
		int bottomSpikesWidth = AssetLoader.flippedSpike.getRegionWidth() / 2;
		int bottomSpikesHeight = AssetLoader.flippedSpike.getRegionHeight() / 2;
		
		topSpikes = new Spikes(0, 0,topSpikesWidth ,topSpikesHeight , AssetLoader.spike);
		bottomSpikes = new Spikes(0, GameScreen.GAME_HEIGHT - bottomSpikesHeight , bottomSpikesWidth, bottomSpikesHeight,  AssetLoader.flippedSpike);
		
		topSpikes.setNewBounds(0,0, GameScreen.GAME_WIDTH, topSpikes.getHeight());
		bottomSpikes.setNewBounds(0,GameScreen.GAME_HEIGHT - topSpikes.getHeight(), GameScreen.GAME_WIDTH, topSpikes.getHeight());

		
		gameObjectsPool = new ArrayList<GameObject>();

		gameObjectsPool.add(bola);
		
		worldBounds = new Rectangle(0, 0, GameScreen.GAME_WIDTH, GameScreen.GAME_HEIGHT);
		
	}

	public void update(float delta) {
		checkCollitions();
		for (GameObject gameObject : gameObjectsPool) {
			gameObject.update(delta);
		}
	}

	private void checkCollitions() {

		Rectangle bolaHitbox = bola.getHitbox();
		Rectangle topSpikesHitbox = topSpikes.getHitbox();
		Rectangle bottommSpikesHitbox = bottomSpikes.getHitbox();

		// Collides with bottom
		if(topSpikesHitbox.y + topSpikesHitbox.height >= bolaHitbox.y)                                  
			bola.onCollide(); 
		
		// Collides with top
		if(bottommSpikesHitbox.y - bottommSpikesHitbox.height <= bolaHitbox.y)                                  
			bola.onCollide(); 
		
		// TODO: Fix world collitions and boundaries
		if(worldBounds.y - worldBounds.height <= bolaHitbox.y)
			bola.onCollide();
		
		if(worldBounds.y + worldBounds.height >= bolaHitbox.y)
			bola.onCollide();
		
		if(worldBounds.x >= bolaHitbox.x)
			bola.onCollide();
		
		if(worldBounds.x + worldBounds.width >= bolaHitbox.x)
			bola.onCollide();
		
	}

	public Bola getBola(){
		return bola;
	}

	public Spikes getTopSpikes() {
		return topSpikes;
	}

	public Spikes getBottomSpikes() {
		return bottomSpikes;
	}
	
	public List<GameObject> getObjectsPool(){
		return gameObjectsPool;
	}

}
