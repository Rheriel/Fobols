package com.na76.flapmyballs.handlers;

import com.badlogic.gdx.InputProcessor;
import com.na76.flapmyballs.gameobjects.Bola;

public class InputHandler implements InputProcessor {

	private Bola myBola;
	
	public InputHandler(Bola bola) {
		myBola = bola;
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
		myBola.touchDown();
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
