package com.na76.flapmyballs.handlers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.na76.flapmyballs.gameobjects.Dude;
import com.na76.flapmyballs.gameobjects.Dude.State;

public class InputHandler implements InputProcessor {

	private Dude myBola;

	private enum EnumKeys {
		LEFT, RIGHT
	}

	static Map<EnumKeys, Boolean> keys = new HashMap<EnumKeys, Boolean>();
	static {
		keys.put(EnumKeys.LEFT, false);
		keys.put(EnumKeys.RIGHT, false);
	};


	public InputHandler(Dude bola) {
		myBola = bola;
	}

	@Override
	public boolean keyDown(int keyCode) {
		switch(keyCode){
		case Keys.A:
		case Keys.LEFT:
			// left is pressed
			myBola.setFacingLeft(true);
			myBola.setState(State.WALKING);
			// Add velocity and movement.
			myBola.addVelocity(-10f);
			break;
		case Keys.D:
		case Keys.RIGHT:
			myBola.setFacingLeft(false);
			myBola.setState(State.WALKING);
			// Add velocity and movement.
			myBola.addVelocity(10f);
			break;
		}
		// need to check if both or none direction are pressed, then myBola is idle
		if ((keyCode == Keys.LEFT) && (keyCode == Keys.RIGHT) ||
				(!(keyCode == Keys.LEFT) && !(keyCode == Keys.RIGHT))) {
			myBola.setState(State.IDLE);
			myBola.setVelocity(0f);
			// acceleration is 0 on the x
		}

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
		myBola.onClick();
		return true; // Return true to say we handled the touch.
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
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
		myBola.update(delta);
	}
}
