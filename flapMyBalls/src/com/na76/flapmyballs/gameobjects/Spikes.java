package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.interfaces.Collidable;
import com.na76.flapmyballs.interfaces.GameObject;
import com.na76.flapmyballs.screens.GameScreen;

public class Spikes implements GameObject, Collidable {
	
	private float width;
	private float height;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	public static final float SCALED_SPIKES_WIDTH =  AssetLoader.spike.getRegionWidth() * .5f;
	public static final float SCALED_SPIKES_HEIGHT =  AssetLoader.spike.getRegionHeight() * .5f;
	
	private Rectangle hitbox; 
	
	public Spikes(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		hitbox = new Rectangle(this.position.x, this.position.y, GameScreen.GAME_WIDTH, SCALED_SPIKES_HEIGHT / 2);
	}

	@Override
	public void onCollide() {
		// If Bola collides with them then end game, else do nothing.
	}

	@Override
	public void update(float delta) {
		// Not implemented yet.
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getVelocityX() {
		return velocity.x;
	}

	public void setVelocityX(float velocity) {
		this.velocity.x = velocity;
	}

	public float getVelocityY() {
		return velocity.y;
	}

	public void setVelocityY(float velocity) {
		this.velocity.y = velocity;
	}
	
	public float getAccelerationX() {
		return acceleration.x;
	}

	public void setAccelerationX(float acceleration) {
		this.acceleration.x = acceleration;
	}

	public float getAccelerationY() {
		return acceleration.y;
	}

	public void setAccelerationY(float acceleration) {
		this.acceleration.y = acceleration;
	}

	public Rectangle getHitbox() {
		return this.hitbox;
	}

	public void setNewBounds(float x, float y, float width, float height) {
		this.hitbox.x = x;
		this.hitbox.y = y;
		this.hitbox.width = width;
		this.hitbox.height = height;
	}


}
