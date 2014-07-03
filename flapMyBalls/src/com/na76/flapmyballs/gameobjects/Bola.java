package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.gameobjects.Bola.State;
import com.na76.flapmyballs.interfaces.Collidable;
import com.na76.flapmyballs.interfaces.GameObject;

public class Bola implements GameObject, Collidable {

	public static final float STARTING_X = 33;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private Vector2 touchedPoint;

	private int width;
	private int height;
	
	private Rectangle hitbox;
	

	

	public enum State {
		IDLE, WALKING, FALLING, DYING
	}
	
	State state = State.IDLE;
	
	boolean facingLeft = true;
	
	float stateTime = 0;

	public Bola (float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		touchedPoint = new Vector2();
		hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
		
	}
	
	public Rectangle getHitbox(){
		return this.hitbox;
	}
	
	@Override
	public void update(float delta) {

		velocity.add(acceleration.cpy().scl(delta));

		if (velocity.y > 20) {
			velocity.y = 20;
		}

		position.add(velocity.cpy().scl(delta));
		
		hitbox.y = position.y;
		hitbox.x = position.x;
		
		stateTime += delta;

	}

	public void onClick() {
		Rectangle leftSide;
		Rectangle rightSide;
		
		leftSide = new Rectangle (0, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		rightSide = new Rectangle ( Gdx.graphics.getWidth()/2, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		velocity.y = -100;
		if (Gdx.input.justTouched()){
			touchedPoint.set(Gdx.input.getX(), Gdx.input.getY());
			if(leftSide.contains(touchedPoint)){position.x = position.x - 3f;}
			else if(rightSide.contains(touchedPoint)){position.x = position.x + 3f;}
		}
		
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
		return facingLeft;
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

	@Override
	public void onCollide() {
		state = State.IDLE;
		velocity.y = 0;
		acceleration.y = 0;
	}
	
}
