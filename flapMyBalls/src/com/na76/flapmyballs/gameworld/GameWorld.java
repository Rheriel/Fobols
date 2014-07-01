package com.na76.flapmyballs.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.interfaces.GameObject;
import com.na76.flapmyballs.screens.GameScreen;


public class GameWorld {

	public static final int BOLA_WIDTH = 22;
	public static final int BOLA_HEIGHT = 27;


	private Bola bola;
	private Spikes topSpikes;
	private Spikes bottomSpikes;

	private List<GameObject> gameObjectsPool; 

	public GameWorld(int midPointY){

		bola = new Bola(Bola.STARTING_X, midPointY - 5,BOLA_WIDTH, BOLA_HEIGHT);

		topSpikes = new Spikes(0, 0, Spikes.SCALED_SPIKES_WIDTH, Spikes.SCALED_SPIKES_HEIGHT);
		bottomSpikes = new Spikes(0, GameScreen.GAME_HEIGHT - Spikes.SCALED_SPIKES_HEIGHT , Spikes.SCALED_SPIKES_WIDTH,  Spikes.SCALED_SPIKES_HEIGHT);

		bottomSpikes.setNewBounds(0, GameScreen.GAME_HEIGHT - Spikes.SCALED_SPIKES_HEIGHT / 2 , GameScreen.GAME_WIDTH, Spikes.SCALED_SPIKES_HEIGHT / 2);
		
		gameObjectsPool = new ArrayList<GameObject>();

		gameObjectsPool.add(bola);
		gameObjectsPool.add(topSpikes);
		gameObjectsPool.add(bottomSpikes);

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

}
