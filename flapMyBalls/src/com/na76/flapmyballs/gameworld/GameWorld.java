package com.na76.flapmyballs.gameworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameobjects.GameObject;
import com.na76.flapmyballs.gameobjects.Platform;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.screens.GameScreen;


public class GameWorld {

	public static final int BOLA_WIDTH = 22;
	public static final int BOLA_HEIGHT = 26;


	private Bola bola;
	private Spikes topSpikes;
	private Spikes bottomSpikes;
	public List<Platform> platforms;

	public final Random rand;
	public static final Vector2 gravity = new Vector2(0, -12);

	private Rectangle worldBounds;

	private List<GameObject> gameObjectsPool; 

	public GameWorld(int midPointY){

		bola = new Bola(Bola.STARTING_X, midPointY - 5,BOLA_WIDTH, BOLA_HEIGHT);

		int topSpikesWidth = AssetLoader.spike.getRegionWidth() / 3;
		int topSpikesHeight = AssetLoader.spike.getRegionHeight() / 3;

		int bottomSpikesWidth = AssetLoader.flippedSpike.getRegionWidth() / 3;
		int bottomSpikesHeight = AssetLoader.flippedSpike.getRegionHeight() / 3;

		topSpikes = new Spikes(0, 0,topSpikesWidth ,topSpikesHeight , AssetLoader.spike);
		bottomSpikes = new Spikes(0, GameScreen.GAME_HEIGHT - bottomSpikesHeight , bottomSpikesWidth, bottomSpikesHeight,  AssetLoader.flippedSpike);

		topSpikes.setNewBounds(0,0, GameScreen.GAME_WIDTH, topSpikes.getHeight());
		bottomSpikes.setNewBounds(0,GameScreen.GAME_HEIGHT - topSpikes.getHeight(), GameScreen.GAME_WIDTH, topSpikes.getHeight());

		rand = new Random();

		gameObjectsPool = new ArrayList<GameObject>();
		platforms = new ArrayList<Platform>();

		gameObjectsPool.add(bola);

		worldBounds = new Rectangle(0 + 1, GameScreen.GAME_HEIGHT - 1, GameScreen.GAME_WIDTH - 1, 0 + 1);
		generateLevel();
	}

	public void update(float delta) {
		checkCollitions();
		for (GameObject gameObject : gameObjectsPool) {
			gameObject.update(delta);
		}
		updatePlatforms(delta);
	}

	private void updatePlatforms(float delta){
		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			platform.update(delta);
		}
	}

	private void checkCollitions() {
		checkSpikesCollitions();
		checkPlatformCollitions();
	}

	private void checkPlatformCollitions() {
		// TODO Auto-generated method stub

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

	private void checkSpikesCollitions(){

		Rectangle bolaHitbox = bola.getHitbox();
		Rectangle topSpikesHitbox = topSpikes.getHitbox();
		Rectangle bottommSpikesHitbox = bottomSpikes.getHitbox();

		if(topSpikesHitbox.y + topSpikesHitbox.height >= bolaHitbox.y)                                  
			bola.onCollide(); 

		if(bottommSpikesHitbox.y - bottommSpikesHitbox.height <= bolaHitbox.y)                                  
			bola.onCollide(); 
	}

	private void generateLevel () {
		float y = Platform.PLATFORM_HEIGHT / 2;
		float maxBolaVelocity = Bola.BOLA_FALLING_VELOCITY * Bola.BOLA_FALLING_VELOCITY / (2 * -gravity.y);

		while (y < GameScreen.GAME_HEIGHT - GameScreen.GAME_WIDTH / 2) {
			float x = rand.nextFloat() * (GameScreen.GAME_WIDTH - Platform.PLATFORM_WIDTH) + Platform.PLATFORM_WIDTH / 2;

			Platform platform = new Platform(x, y);
			platforms.add(platform);

			// TODO Change for spiked bar
			//			if (rand.nextFloat() > 0.9f) {
			//				Spring spring = new Spring(platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT / 2
			//					+ Spring.SPRING_HEIGHT / 2);
			//				springs.add(spring);
			//			}

			y += (maxBolaVelocity - 0.5f);
			y -= rand.nextFloat() * (maxBolaVelocity / 3);
		}
	}

}
