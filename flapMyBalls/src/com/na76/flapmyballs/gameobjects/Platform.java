package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.constants.GameConstants;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.interfaces.Collidable;

public class Platform extends GameObject implements Collidable {

	private static final int PLATFORM_SCALE = 3;
	private Vector2 position;
	private Vector2 velocity;
	private AtlasRegion texture;
	private float width;
	private float height;
	public boolean isVisible = true;

	float stateTime;

	public Platform(float x, float y){
		position = new Vector2(x, y);
		velocity = new Vector2(0, GameConstants.GAME_VELOCITY);
		this.stateTime = 0;
		this.texture = AssetLoader.platform;
		this.width = texture.getRegionWidth() / PLATFORM_SCALE;
		this.height = texture.getRegionHeight() / PLATFORM_SCALE;
		
		hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
	}

	@Override
	public void onCollide() {
	}

	@Override
	public void update(float delta) {
		
		position.sub(0, velocity.y * delta);
		hitbox.x = position.x;
		hitbox.y = position.y;
		stateTime += delta;
		
	}

	@Override
	public void draw(SpriteBatch batcher) {
		batcher.begin();
		batcher.draw(texture, this.position.x, this.position.y, this.getWidth(), this.getHeight());
		batcher.end();
	}
	
	public float getX() {
		return this.position.x;
	}
	
	public void setX(float newX) {
		this.position.x = newX;
		this.hitbox.x = newX;
	}

	public float getY() {
		return this.position.y;
	}
	
	public void setY(float newY) {
		this.position.y = newY;
		this.hitbox.y = newY;
	}
	
	public void setTexture(AtlasRegion texture){
		this.texture = texture;
	}

	public AtlasRegion getTexture() {
		return this.texture;
	}

	public float getWidth() {
		return this.width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.hitbox.width = width;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float height) {
		this.height = height;
		this.hitbox.height = height;
	}

}
