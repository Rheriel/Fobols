package com.na76.flapmyballs.constants;

import com.badlogic.gdx.Gdx;

public class GameConstants {

	public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final float SCREEN_HEIGHT =  Gdx.graphics.getHeight();
	public static final float GAME_WIDTH = 136;
	public static final float GAME_HEIGHT = SCREEN_HEIGHT  / (SCREEN_WIDTH / GAME_WIDTH);
	public static final int GAME_MID_POINT_Y = (int) (GAME_HEIGHT / 2);
	public static final float GAME_VELOCITY = 20;
	
}
