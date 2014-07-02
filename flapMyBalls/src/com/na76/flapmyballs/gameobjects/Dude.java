package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.gameobjects.Dude.State;

public class Dude {

	private static final float MAX_VELOCITY = 100;
	
	public static final int DUDE_HEIGHT = 27;
	public static final int DUDE_WIDTH = 22;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width;
	private int height;
	
	private static float DAMPING = 0.9f;
	
	public enum State {
		IDLE, WALKING, FALLING, DYING
	}

	State state = State.IDLE;

	private boolean facingLeft = true;

	private float stateTime = 0;

	public Dude (float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
	}

	public void update(float delta) {

		if(delta==0)
			return;
		
		stateTime += delta;
		
		// Right walking
		if(Math.abs(velocity.x) > MAX_VELOCITY) {
			velocity.x = Math.signum(velocity.x) * MAX_VELOCITY;
			state = State.WALKING;
		}

		if(Math.abs(velocity.x) < 1) {
			velocity.x = 0;
			if(state!=State.WALKING)state = State.IDLE;
		}

		if(Math.abs(-velocity.x)>MAX_VELOCITY)
		{
			velocity.x = Math.signum(-velocity.x) * MAX_VELOCITY;
			state = State.WALKING;
		}
		if(Math.abs(-velocity.x)<1)
		{
			velocity.x = 0;
			if(state!=State.WALKING)state = State.IDLE;
		}

		if(Math.abs(-velocity.y)>MAX_VELOCITY){
			velocity.y = Math.signum(-velocity.y) * MAX_VELOCITY;
			state = State.WALKING;
		}
		if(Math.abs(-velocity.y)<1)
		{
			velocity.y = 0;
			if(state!=State.WALKING)state = State.IDLE;
		}

		velocity.scl(delta);

		position.add(velocity);
		velocity.scl(1/delta);

		// Apply damping to the velocity on the x-axis so we don't
		// walk infinitely once a key was pressed
		velocity.x *= DAMPING;
		velocity.y *= DAMPING;

	}

	public void onClick() {
		velocity.y = -140;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public boolean isFacingLeft() {
		// if not then it is facing right.
		return this.facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void addVelocity(float speed) {
		this.velocity.x += speed;
	}

	public void setVelocity(float velocity) {
		this.velocity.x = velocity;
	}

}
