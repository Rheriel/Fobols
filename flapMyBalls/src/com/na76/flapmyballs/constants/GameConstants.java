package com.na76.flapmyballs.constants;

import com.badlogic.gdx.Gdx;

public class GameConstants {

	public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final float SCREEN_HEIGHT =  Gdx.graphics.getHeight();
	public static final float GAME_WIDTH = 136;
	public static final float GAME_HEIGHT = SCREEN_HEIGHT  / (SCREEN_WIDTH / GAME_WIDTH);
	public static final int GAME_MID_POINT_Y = (int) (GAME_HEIGHT / 2);
	public static final float GAME_VELOCITY = 20;
	public static final int BOLA_WIDTH = 22;
	public static final int BOLA_HEIGHT = 26;
	public static final int STARTING_BOLA_X = (int) ((GameConstants.GAME_WIDTH/2) - (BOLA_WIDTH/2));
	public static final int STARTING_BOLA_Y = (int) ((GameConstants.GAME_HEIGHT/2) - (BOLA_HEIGHT/2));
	
}
