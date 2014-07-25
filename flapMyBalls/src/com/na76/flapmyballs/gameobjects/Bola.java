package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.interfaces.Collidable;
import com.na76.flapmyballs.interfaces.GameObject;

public class Bola extends GameObject implements Collidable {

	public static final float STARTING_X = 33;

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private Vector2 touchedPoint;

	private int width;
	private int height;

	public enum State {
		IDLE, WALKING, FALLING, DYING
	}

	private State state;

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
		this.state = State.FALLING;
	}

	public void update(float delta) {
		System.out.println(this.getState());
		if (this.getState() == State.FALLING){
			
			velocity.add(acceleration.cpy().scl(delta));
			
			if (velocity.y > 20) {
				velocity.y = 20;
			
			}
			position.add(velocity.cpy().scl(delta));
		}
		

		hitbox.y = position.y;
		hitbox.x = position.x;
		
		stateTime += delta;

	}

	public void touchDown() {
		Rectangle leftSide;
		Rectangle rightSide;

		leftSide = new Rectangle (0, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		rightSide = new Rectangle ( Gdx.graphics.getWidth()/2, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		touchedPoint.set(Gdx.input.getX(), Gdx.input.getY());
		if(leftSide.contains(touchedPoint)){
			this.facingLeft = true;
			// Do walking left animation and movement.
			position.x = position.x - 2f;
		}
		else if(rightSide.contains(touchedPoint)){
			this.facingLeft = false;
			// Do walking right animation and movement.
			position.x = position.x + 2f;
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
		return this.state;
	}

	public float getStateTime() {
		return stateTime;
	}

	@Override
	public void onCollide() {
		System.out.println("COLLIDING!");
		state = State.IDLE;
		velocity.y = 0;
		acceleration.y = 0;
	}

	public void draw(SpriteBatch batcher){

		if (this.state == State.FALLING){
			AssetLoader.currentDudeFrame = this.isFacingLeft() ? AssetLoader.dudeFallingLeft : AssetLoader.dudeFallingRight;
		}
		
		if (Gdx.input.isTouched()) {

			this.touchDown();
			
			if (this.getState() == State.IDLE) {
				this.setState(State.WALKING);
				while (AssetLoader.stateTime > AssetLoader.RUNNING_FRAME_DURATION) {
					AssetLoader.stateTime -= AssetLoader.RUNNING_FRAME_DURATION;
					AssetLoader.currentDudeFrame = this.isFacingLeft() ? AssetLoader.dudeWalkLeftAnimation.getKeyFrame(this.getStateTime(), true) : AssetLoader.dudeWalkRightAnimation.getKeyFrame(this.getStateTime(), true);
				}
			}
		} 
		batcher.begin();
		batcher.draw(AssetLoader.currentDudeFrame, this.getX(),this.getY(),this.getWidth(),this.getHeight());
		batcher.end();

	}

	public void touchUp() {
		if (this.state == State.WALKING){
			this.state = State.IDLE;
		}
	}

}

