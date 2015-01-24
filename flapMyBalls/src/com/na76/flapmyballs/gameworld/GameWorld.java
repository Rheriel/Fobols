package com.na76.flapmyballs.gameworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hm.fobols.utils.Score;
import com.hm.fobols.utils.Time;
import com.na76.flapmyballs.constants.GameConstants;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameobjects.EvilPlatform;
import com.na76.flapmyballs.gameobjects.GameObject;
import com.na76.flapmyballs.gameobjects.Platform;
import com.na76.flapmyballs.gameobjects.Spikes;
import com.na76.flapmyballs.handlers.AssetLoader;


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

	public final Random random;
	public static final Vector2 gravity = new Vector2(0, -12);

	private Rectangle worldBounds;

	private List<GameObject> gameObjectsPool;
	private Time time;
	private Score score;
	private int oddsToGetAnEvilPlatform = 1;

	private int difficulty = 4;

	public GameWorld(int midPointY){

		currentState = GameState.READY;
		time = new Time();
		score = new Score();


		bola = new Bola(STARTING_BOLA_X, STARTING_BOLA_Y,BOLA_WIDTH, BOLA_HEIGHT);

		int topSpikesWidth = AssetLoader.flippedSpike.getRegionWidth() / GameConstants.SPIKE_SCALE;
		int topSpikesHeight = AssetLoader.flippedSpike.getRegionHeight() / GameConstants.SPIKE_SCALE;

		int bottomSpikesWidth = AssetLoader.spike.getRegionWidth() / GameConstants.SPIKE_SCALE;
		int bottomSpikesHeight = AssetLoader.spike.getRegionHeight() / GameConstants.SPIKE_SCALE;

		topSpikes = new Spikes(0, GameConstants.GAME_HEIGHT - (bottomSpikesHeight / (GameConstants.TOP_SPIKE_OVERLAP_Y)),topSpikesWidth ,topSpikesHeight , AssetLoader.flippedSpike);
		bottomSpikes = new Spikes(0, 0 - (bottomSpikesHeight / GameConstants.BOTTOM_SPIKE_OVERLAP_Y), bottomSpikesWidth, bottomSpikesHeight,  AssetLoader.spike);

//		topSpikes.setNewBounds(0,GameConstants.GAME_HEIGHT - (bottomSpikesHeight / (GameConstants.TOP_SPIKE_OVERLAP_Y)), topSpikesWidth, topSpikesHeight);
//		bottomSpikes.setNewBounds(0,0 - (bottomSpikesHeight / GameConstants.BOTTOM_SPIKE_OVERLAP_Y), bottomSpikesWidth, bottomSpikesHeight);
		random = new Random();

		worldBounds = new Rectangle(0 + 1, GameConstants.GAME_HEIGHT - 1, GameConstants.GAME_WIDTH - 1, 0 + 1);

		generateLevel(GameConstants.INITIAL_GAME_DIFFICULTY, GameConstants.INITIAL_GAME_VELOCITY);
	}

	private void generateLevel(int difficulty, int velocity){

		gameObjectsPool = new ArrayList<GameObject>();

		gameObjectsPool.add(bola);

		if (GameConstants.TOGGLE_PLATFORMS) {

			platforms = new ArrayList<Platform>();
			int numberOfPlatformsPerScreen = (int) (GameConstants.GAME_HEIGHT / (bola.getHeight() * 1.5));

			for (int i = 0; i < numberOfPlatformsPerScreen; i++) {
				boolean isEvilPlatform = false;
				int evilPlatformGenerator = random.nextInt(getOddsToGetAnEvilPlatform());
				System.out.println("EVIL PLATFORM GENERATOR: " + evilPlatformGenerator);

				if (evilPlatformGenerator > getDifficulty()) {
					isEvilPlatform = true;
				}

				Platform platform = createRandomPlatform(isEvilPlatform);
				platform.setY(-1 - (bola.getHeight()  + (platform.getHeight() * i *2)));
				platforms.add(platform);
			}
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
		
		if (GameConstants.TOGGLE_PLATFORMS)
			updatePlatforms(delta);
	}

	private void updatePlatforms(float delta){

		for (Platform platform : platforms) {
			if(platform.isVisible){
				platform.update(delta);
			} else {
				cleanUpPlatform(platform);
			}

			if (platform.getY() - platform.getHeight() >= GameConstants.GAME_HEIGHT){
				platform.isVisible = false;
			}
		}

	}

	private Platform createRandomPlatform(boolean isEvilPlatform){

		// TODO Define 5 possible columns where the platforms might be.
		float positionX = generatePlatformRandomRow();
		Platform platform = null;
		if ((lastPlatformXPosition - positionX) < (bola.getWidth() + (bola.getWidth() / 2))) {
			positionX += bola.getWidth();
			lastPlatformXPosition = positionX;
		}

		if (isEvilPlatform){
			platform = new EvilPlatform(positionX, 0);
		} else {
			platform = new Platform(positionX, 0);
		}

		return platform;

	}

	private void cleanUpPlatform(Platform platform) {
		platform.setX(generatePlatformRandomRow());
		platform.setY(-platform.getHeight());
		platform.isVisible = true;
	}

	private float generatePlatformRandomRow(){

		int platformRow = random.nextInt(GameConstants.GAME_ROWS + 1);
		float platformX = 0f;

		switch (platformRow){

		case 1:
			platformX = random.nextInt((int)(GameConstants.GAME_WIDTH / 5) + 1);
			break;
		case 2:
			platformX = random.nextInt((int)((GameConstants.GAME_WIDTH / 5) + ((GameConstants.GAME_WIDTH / 5) + 1)));
			break;
		case 3:
			platformX = random.nextInt((int)((GameConstants.GAME_WIDTH / 5) + ((GameConstants.GAME_WIDTH / 5) + 1) * 2));
			break;
		case 4:
			platformX = random.nextInt((int)((GameConstants.GAME_WIDTH / 5) + ((GameConstants.GAME_WIDTH / 5) + 1) * 3));
			break;
		case 5:
			platformX = random.nextInt((int)((GameConstants.GAME_WIDTH / 5) + ((GameConstants.GAME_WIDTH / 5) + 1) * 4));
			break;
		default:
			break;
		}

		return platformX;
	}

	private void checkCollitions() {
		checkSpikesCollitions();
		if (GameConstants.TOGGLE_PLATFORMS)
			checkPlatformCollitions();
	}

	private void checkPlatformCollitions() {

		bola.isCollidingWithPlatform = false;

		for (Platform platform : platforms) {
			if (platform.isVisible && bola.isAlive()) {
				Rectangle bolaHitbox = bola.getHitbox();

				if((Math.abs(bolaHitbox.y - (platform.getY() + platform.getHeight())) < 1 ) &&
						((platform.getX() <= (bolaHitbox.x + bolaHitbox.getWidth())) &&
								((platform.getX() + platform.getWidth()) >= (bolaHitbox.x)))){                                
					System.out.println("Collinde detected");

					if (platform instanceof EvilPlatform){
						currentState = GameState.GAMEOVER;
						bola.collideWithEvilPlatform(platform);
						break;
					} else {
						bola.collideWithPlatform(platform);
					}
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
		Rectangle bottomSpikesHitbox = bottomSpikes.getHitbox();

		float bolaHeadLimit = bolaHitbox.getY() + bolaHitbox.getHeight();
		float topSpikesY = GameConstants.GAME_HEIGHT - topSpikesHitbox.getHeight();

		if(bolaHeadLimit >= topSpikesY) {                                
			System.out.println("COLLIDING WITH TOP SPIKES!");
			currentState = GameState.GAMEOVER;
			bola.collideWithSpikes();
		}

		if(bolaHitbox.y <= bottomSpikesHitbox.getHeight()) {                                
			System.out.println("COLLIDING WITH BOTTOM SPIKES!");
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
		generateLevel(GameConstants.INITIAL_GAME_DIFFICULTY, GameConstants.INITIAL_GAME_VELOCITY);
	}

	public void restart() {
		bola.onRestart(STARTING_BOLA_X, STARTING_BOLA_Y);
		currentState = GameState.READY;
		score.setScore(0);
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

	public Time getTime() {
		return time;		
	}

	public Score getScore() {
		return score;		
	}

}
