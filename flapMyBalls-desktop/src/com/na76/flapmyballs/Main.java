package com.na76.flapmyballs;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Main {
	public static void main(String[] args) {
		
        TexturePacker.process("../flapMyBalls/images", "../flapMyBalls-android/assets/images", "gameTextures");

		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Flap My Balls";
        cfg.width = 272;
        cfg.height = 408;
        
        new LwjglApplication(new FlapMyBalls(), cfg);
	}
}
