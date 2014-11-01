package com.na76.flapmyballs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen implements Screen {
	// TODO Add Buttons graphics and create skins for them...

	private Stage stage = new Stage();
	private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"));
	private TextButton playButton = new TextButton("Play", skin);
	private TextButton exitButton = new TextButton("Exit", skin);
	private Table table = new Table();

	public MenuScreen() {
		Gdx.app.log("Menu Screen", "Constructor called");
		
	}

	@Override
	public void render(float delta) {
		System.out.println("MenuScreen - rendering");
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("MenuScreen - resizing");

	}

	@Override
	public void show() {
		System.out.println("MenuScreen - showing");
		table.add(playButton).row();
		table.add(exitButton).row();
		
		table.setFillParent(false);
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		System.out.println("MenuScreen - hiding");
		dispose();
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
		stage.dispose();
		skin.dispose();
	}

}
>>>>>>> 0727316 Delete Test message
