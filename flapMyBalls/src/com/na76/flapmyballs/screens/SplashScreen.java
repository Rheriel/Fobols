package com.na76.flapmyballs.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.na76.flapmyballs.constants.GameConstants;

public class SplashScreen implements Screen {

	private Texture splash = new Texture(Gdx.files.internal("HM_2.png"));
	private Image splashImage = new Image(splash);
	private Stage stage = new Stage();

	public SplashScreen(){
		Gdx.app.log("Splash Screen", "Constructor called");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor( 1f, 1f, 1f, 1f );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
		splashImage.setScale(.4f, .4f);
        splashImage.setX((GameConstants.GAME_WIDTH / 2f) - (splashImage.getImageWidth()/2f) *.2f);
        splashImage.setY((GameConstants.GAME_HEIGHT * 2f) - (splashImage.getImageHeight()/2));

        stage.act();
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("Splash screen - resizing!");
	}

	@Override
	public void show() {
		System.out.println("Splash screen - showing!");
		splashImage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(0.5f),Actions.delay(0.5f),Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        })));
		stage.addActor(splashImage);
	}

	@Override
	public void hide() {
		System.out.println("Splash screen - hiding!");
		dispose();
	}

	@Override
	public void pause() {
		System.out.println("Splash screen - pausing!");

	}

	@Override
	public void resume() {
		System.out.println("Splash screen - resuming!");
	}

	@Override
	public void dispose() {
		splash.dispose();
        stage.dispose();
	}

}
