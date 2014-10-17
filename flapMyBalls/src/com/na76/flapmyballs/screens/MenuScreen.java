package com.na76.flapmyballs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MenuScreen implements Screen {
	
	public MenuScreen() {
		Gdx.app.log("Menu Screen", "Constructor called");

	}

	@Override
	public void render(float delta) {
		System.out.println("MenuScreen - rendering");
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("MenuScreen - resizing");

	}

	@Override
	public void show() {
		System.out.println("MenuScreen - showing");

	}

	@Override
	public void hide() {
		System.out.println("MenuScreen - hiding");

	}

	@Override
	public void pause() {
		System.out.println("MenuScreen - pausing");

	}

	@Override
	public void resume() {
		System.out.println("MenuScreen - disposing");

	}

	@Override
	public void dispose() {
		System.out.println("MenuScreen - ");

	}

}
