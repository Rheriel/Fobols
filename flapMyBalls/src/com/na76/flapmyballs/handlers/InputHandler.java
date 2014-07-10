package com.na76.flapmyballs.handlers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.na76.flapmyballs.gameobjects.Bola;
import com.na76.flapmyballs.gameobjects.Bola.State;

public class InputHandler implements InputProcessor {

	private Bola myBola;
	
	private enum EnumKeys {
		LEFT, RIGHT
	}

	static Map<EnumKeys, Boolean> keys = new HashMap<EnumKeys, Boolean>();
	    static {
	        keys.put(EnumKeys.LEFT, false);
	        keys.put(EnumKeys.RIGHT, false);
	    };

	    
	public InputHandler(Bola bola) {
		myBola = bola;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			leftPressed();
		if (keycode == Keys.RIGHT)
			rightPressed();
		return true;

	}

	public void leftPressed() {
		keys.get(keys.put(EnumKeys.LEFT, true));
	}
	
	public void rightPressed() {
		keys.get(keys.put(EnumKeys.RIGHT, true));
	}

	public void leftReleased() {
		keys.get(keys.put(EnumKeys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(EnumKeys.RIGHT, false));
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
		myBola.setState(Bola.State.WALKING);
		myBola.touchDown();
		return true; // Return true to say we handled the touch.
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		myBola.setState(Bola.State.IDLE);
		return true; // Return true to say we handled the touch.
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
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
		processInput();
		myBola.update(delta);
	}


	private void processInput() {

		if (keys.get(Keys.LEFT)) {
			// left is pressed
			myBola.setFacingLeft(true);
			myBola.setState(State.WALKING);
			myBola.touchDown();
			// Add velocity and movement.
		}
		if (keys.get(Keys.RIGHT)) {
			// left is pressed
			myBola.setFacingLeft(false);
			myBola.setState(State.WALKING);
			myBola.touchDown();
			// Add velocity and movement.
		}
		// need to check if both or none direction are pressed, then myBola is idle
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
				(!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
			myBola.setState(State.IDLE);
			// acceleration is 0 on the x
			// horizontal speed is 0
		}
	}


}
