package com.na76.flapmyballs;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Main {
	public static void main(String[] args) {

		Settings settings = new Settings();
		settings.paddingX = 2;
		settings.paddingY = 2;
		settings.minWidth = 32;
		settings.minHeight = 32;
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.stripWhitespaceX = true;
		settings.stripWhitespaceY = true;
		settings.filterMag = TextureFilter.MipMapLinearLinear;
		settings.filterMin = TextureFilter.MipMapLinearLinear;
		settings.flattenPaths = true;

		TexturePacker.process(settings,"../flapMyBalls/images", "../flapMyBalls-android/assets/images", "gameTextures");


		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Fobols";
		cfg.width = 272;
		cfg.height = 408;

		new LwjglApplication(new FlapMyBalls(), cfg);
	}
}
