package com.na76.flapmyballs.handlers;

import com.badlogic.gdx.InputProcessor;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

	private Bola myBola;
	private GameWorld myWorld;

	public InputHandler(GameWorld myWorld) {
		this.myWorld = myWorld;
		this.myBola = myWorld.getBola();
	}

	@Override
	public boolean keyDown(int keycode) {
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (myWorld.isReady()) {
			myWorld.start();
		} else if (myWorld.isRunning()) {
			myBola.touchDown();
		} else if (myWorld.isGameOver()) {
			myWorld.restart();
		} 

		return true; // Return true to say we handled the touch.
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		myBola.touchUp();
		return true; // Return true to say we handled the touch.
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	/** The main update method **/
	public void update(float delta) {
		myBola.update(delta);
	}

}
