package com.na76.flapmyballs.constants;

import com.badlogic.gdx.Gdx;

public class GameConstants {

	public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final float SCREEN_HEIGHT =  Gdx.graphics.getHeight();
	public static final float GAME_WIDTH = 136;
	public static final float GAME_HEIGHT = SCREEN_HEIGHT  / (SCREEN_WIDTH / GAME_WIDTH);
	public static final int GAME_MID_POINT_Y = (int) (GAME_HEIGHT / 2);
	public static final float GAME_VELOCITY = 20;
	
	
//	Time font size
	public final static float TIME_FONT_HEIGHT 		= 15f;
	public final static float TIME_FONT_WIDTH 		= 15f;
	
//	Score font size
	public final static float SCORE_FONT_HEIGHT 	= 8f;
	public final static float SCORE_FONT_WIDTH 		= 8f;
	public final static float SCORE_FONT_HEIGHT_MOD 	= SCORE_FONT_HEIGHT*1.15f;
	public final static float SCORE_FONT_WIDTH_MOD 		= SCORE_FONT_WIDTH*1.15f;
	
	
	// Time
	public final static float X_VALUE_TEN_MINUTES = 25f;
	public final static float X_VALUE_ONE_MINUTES = 40f;
	public final static float X_VALUE_TIME_SEPARATOR = 55f;
	public final static float X_VALUE_TEN_SECONDS = 70f;
	public final static float X_VALUE_ONE_SECONDS = 85f;
	public final static float Y_VALUE_TIME = 360f;
	
	
	// Score
	public final static float X_VALUE_MILLION = 80f;
	public final static float X_VALUE_HUNDRED_THOUSAND = 88f;
	public final static float X_VALUE_TEN_THOUSAND = 96f;
	public final static float X_VALUE_THOUSAND = 104f;
	public final static float X_VALUE_HUNDRED = 112f;
	public final static float X_VALUE_TEN = 120f;
	public final static float X_VALUE_ONE = 128f;
	public final static float Y_VALUE_SCORE = 0f;
	
	public final static float X_VALUE_MILLION_MOD = X_VALUE_MILLION-0f;
	public final static float X_VALUE_HUNDRED_THOUSAND_MOD = X_VALUE_HUNDRED_THOUSAND-0f;
	public final static float X_VALUE_TEN_THOUSAND_MOD = X_VALUE_TEN_THOUSAND-0f;
	public final static float X_VALUE_THOUSAND_MOD = X_VALUE_THOUSAND-0f;
	public final static float X_VALUE_HUNDRED_MOD = X_VALUE_HUNDRED-0f;
	public final static float X_VALUE_TEN_MOD = X_VALUE_TEN-0f;
	public final static float X_VALUE_ONE_MOD = X_VALUE_ONE-0f;
	public final static float Y_VALUE_SCORE_MOD = Y_VALUE_SCORE-0f;
	
	public static final int SCORE_INCREMENT_PER_FRAME = 3;
	public static final int INITIAL_GAME_DIFFICULTY = 4;
	public static final int INITIAL_GAME_VELOCITY = 1;
	public static final int GAME_ROWS = 5;
}
