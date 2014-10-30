package com.na76.flapmyballs.gameworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameobjects.EvilPlatform;
import com.na76.flapmyballs.gameobjects.GameObject;
import com.na76.flapmyballs.gameobjects.Platform;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.constants.GameConstants;


public class GameWorld {

	public enum GameState {
		READY, RUNNING, GAMEOVER
	}

	private GameState currentState;

	public static final int BOLA_WIDTH = 22;
	public static final int BOLA_HEIGHT = 26;
	
	public static final int STARTING_BOLA_X = (int) ((GameConstants.GAME_WIDTH/2) - (BOLA_WIDTH/2));
	public static final int STARTING_BOLA_Y = (int) ((GameConstants.GAME_HEIGHT/2) - (BOLA_HEIGHT/2));
	
	private Bola bola;
	private Spikes topSpikes;
	private Spikes bottomSpikes;
	private float lastPlatformXPosition = 0f;
	public List<Platform> platforms;

	public final Random rand;
	public static final Vector2 gravity = new Vector2(0, -12);

	private Rectangle worldBounds;

	private List<GameObject> gameObjectsPool;

	private int score = 0; 
	private int oddsToGetAnEvilPlatform = 5;

	private int difficulty = 1;

	public GameWorld(int midPointY){

		currentState = GameState.READY;
		
		bola = new Bola(STARTING_BOLA_X, STARTING_BOLA_Y,BOLA_WIDTH, BOLA_HEIGHT);

		int topSpikesWidth = AssetLoader.spike.getRegionWidth() / 3;
		int topSpikesHeight = AssetLoader.spike.getRegionHeight() / 3;

		int bottomSpikesWidth = AssetLoader.flippedSpike.getRegionWidth() / 3;
		int bottomSpikesHeight = AssetLoader.flippedSpike.getRegionHeight() / 3;

		topSpikes = new Spikes(0, 0,topSpikesWidth ,topSpikesHeight , AssetLoader.spike);
		bottomSpikes = new Spikes(0, GameConstants.GAME_HEIGHT - bottomSpikesHeight , bottomSpikesWidth, bottomSpikesHeight,  AssetLoader.flippedSpike);

		topSpikes.setNewBounds(0,0, GameConstants.GAME_WIDTH, topSpikesHeight / 2);
		bottomSpikes.setNewBounds(0,GameConstants.GAME_HEIGHT - (bottomSpikesHeight / 2), GameConstants.GAME_WIDTH, bottomSpikesHeight / 2);
		rand = new Random();
		
		worldBounds = new Rectangle(0 + 1, GameConstants.GAME_HEIGHT - 1, GameConstants.GAME_WIDTH - 1, 0 + 1);

		generateLevel();
	}

	private void generateLevel(){
		Random random = new Random();
		
		gameObjectsPool = new ArrayList<GameObject>();
		platforms = new ArrayList<Platform>();

		gameObjectsPool.add(bola);

		int numberOfPlatformsPerScreen = (int) (GameConstants.GAME_HEIGHT / bola.getHeight());

		for (int i = 0; i < numberOfPlatformsPerScreen; i++) {
			boolean isEvilPlatform = false;
			int evilPlatformGenerator = random.nextInt(getOddsToGetAnEvilPlatform());
			System.out.println("EVIL PLATFORM GENERATOR: " + evilPlatformGenerator);
			
			if (evilPlatformGenerator > getDifficulty()) {
				isEvilPlatform = true;
			}
			
			Platform platform = createRandomPlatform(isEvilPlatform);
			platform.setY(GameConstants.GAME_HEIGHT + (bola.getHeight() * i));
			platforms.add(platform);
		}
	}

	public void update(float delta) {

		switch (currentState) {
		case READY:
			updateReady(delta);
			break;

		case RUNNING:
		default:
			updateRunning(delta);
			break;
		}

	}

	private void updateReady(float delta) {

	}

	private void updateRunning(float delta) {
		checkCollitions();
		for (GameObject gameObject : gameObjectsPool) {
			gameObject.update(delta);
		}
		updatePlatforms(delta);
	}

	private void updatePlatforms(float delta){

		for (Platform platform : platforms) {
			if(platform.isVisible){
				platform.update(delta);
			} else {
				cleanUpPlatform(platform);
			}

			if (platform.getY() + platform.getHeight() <= 0){
				platform.isVisible = false;
			}
		}

	}

	private Platform createRandomPlatform(boolean isEvilPlatform){

		float positionX = generatePlatformRandomXPosition();
		Platform platform = null;
		if ((lastPlatformXPosition - positionX) < (bola.getWidth() + (bola.getWidth() / 2))) {
			positionX += bola.getWidth();
			lastPlatformXPosition = positionX;
		}

		if (isEvilPlatform){
			platform = new EvilPlatform(positionX, GameConstants.GAME_HEIGHT);
		} else {
			platform = new Platform(positionX, GameConstants.GAME_HEIGHT);
		}
		
		return platform;

	}

	private void cleanUpPlatform(Platform platform) {
		platform.setX(generatePlatformRandomXPosition());
		platform.setY(GameConstants.GAME_HEIGHT);
		platform.isVisible = true;
	}

	private float generatePlatformRandomXPosition(){
		return (float)Math.random() * GameConstants.GAME_WIDTH;
	}

	private void checkCollitions() {
		checkSpikesCollitions();
		checkPlatformCollitions();
	}

	private void checkPlatformCollitions() {

		bola.isCollidingWithPlatform = false;

		for (Platform platform : platforms) {
			if (platform.isVisible) {
				Rectangle bolaHitbox = bola.getHitbox();



				if(Math.abs(platform.getY() - (bolaHitbox.getY() + (float)bolaHitbox.height)) < 1 &&
						((platform.getX() <= (bolaHitbox.x + bolaHitbox.getWidth())) &&
								((platform.getX() + platform.getWidth()) >= (bolaHitbox.x)))){                                
					System.out.println("Collinde detected");
					
					if (platform instanceof EvilPlatform)
						bola.collideWithEvilPlatform(platform);
					else
						bola.collideWithPlatform(platform);
				} 
			}
		}

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

	private void checkSpikesCollitions(){

		Rectangle bolaHitbox = bola.getHitbox();
		Rectangle topSpikesHitbox = topSpikes.getHitbox();
		Rectangle bottommSpikesHitbox = bottomSpikes.getHitbox();

		if(topSpikesHitbox.y + topSpikesHitbox.height >= bolaHitbox.y) {                                
			currentState = GameState.GAMEOVER;
			bola.collideWithSpikes();
		}

		if(bottommSpikesHitbox.y - bottommSpikesHitbox.height <= (bolaHitbox.y + bolaHitbox.getHeight() / 2)) {                                
			currentState = GameState.GAMEOVER;
			bola.collideWithSpikes();
		}
	}

	public boolean isReady(){
		return currentState == GameState.READY;			
	}
	
	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public void start(){
		currentState = GameState.RUNNING;
		generateLevel();
	}
	
	public void restart() {
		bola.onRestart(STARTING_BOLA_X, STARTING_BOLA_Y);
		currentState = GameState.READY;
		score = 0;
	}
	
	public void addScore(int increment){
		score += increment;
	}
	
	public int getScore(){
		return this.score;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getOddsToGetAnEvilPlatform() {
		return oddsToGetAnEvilPlatform;
	}

	public void setOddsToGetAnEvilPlatform(int oddsToGetAnEvilPlatform) {
		this.oddsToGetAnEvilPlatform = oddsToGetAnEvilPlatform;
	}

}
