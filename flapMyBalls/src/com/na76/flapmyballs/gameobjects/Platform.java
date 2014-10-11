package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.interfaces.Collidable;
import com.na76.flapmyballs.screens.GameScreen;

public class Platform extends GameObject implements Collidable {

	public static final float PLATFORM_VELOCITY = 2;
	private static final int PLATFORM_SCALE = 3;
	private Vector2 position;
	private Vector2 velocity;
	private AtlasRegion texture;
	private float width;
	private float height;

	float stateTime;

	public Platform(float x, float y){
		position = new Vector2(x, y);
		velocity = new Vector2(0, PLATFORM_VELOCITY);
		this.stateTime = 0;
		this.texture = AssetLoader.platform;
		this.setWidth(texture.getRegionWidth() / PLATFORM_SCALE);
		this.setHeight(texture.getRegionHeight() / PLATFORM_SCALE);
		hitbox = new Rectangle(this.position.x, this.position.y, this.getWidth(), this.getHeight());
	}

	@Override
	public void onCollide() {
	}

	@Override
	public void update(float delta) {
		
		position.add(velocity.x * delta, 0);
		hitbox.x = position.x - this.getWidth() / 2;
		hitbox.y = position.y - this.getHeight() / 2;

		if (position.x < this.getWidth() / 2) {
			velocity.x = -velocity.x;
			position.x = this.getWidth() / 2;
		}
		if (position.x > GameScreen.GAME_WIDTH - this.getWidth() / 2) {
			velocity.x = -velocity.x;
			position.x = GameScreen.GAME_HEIGHT - this.getWidth() / 2;
		}

		stateTime += delta;
		
	}

	@Override
	public void draw(SpriteBatch batcher) {
		batcher.begin();
		batcher.draw(texture, this.position.x, this.position.y, this.getWidth(), this.getHeight());
		batcher.end();
	}
	
	public float getX() {
		return position.x;
	}
	
	public void setX(float newX) {
		this.position.x = newX;
	}

	public float getY() {
		return position.y;
	}
	
	public void setTexture(AtlasRegion texture){
		this.texture = texture;
	}

	public AtlasRegion getTexture() {
		return texture;
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

}
