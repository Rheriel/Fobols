package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.interfaces.Collidable;
import com.na76.flapmyballs.screens.GameScreen;

public class Platform extends GameObject implements Collidable {

	public static final float PLATFORM_WIDTH = 2;
	public static final float PLATFORM_HEIGHT = 0.5f;
	public static final float PLATFORM_VELOCITY = 2;
	private Vector2 position;
	private Vector2 velocity;
	private AtlasRegion texture;


	float stateTime;

	public Platform(float x, float y){
		position = new Vector2(x, y);
		velocity = new Vector2(0, PLATFORM_VELOCITY);
		this.stateTime = 0;
		hitbox = new Rectangle(this.position.x, this.position.y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		this.texture = AssetLoader.platform;
	}

	@Override
	public void onCollide() {
	}

	@Override
	public void update(float delta) {
		
		position.add(velocity.x * delta, 0);
		hitbox.x = position.x - PLATFORM_WIDTH / 2;
		hitbox.y = position.y - PLATFORM_HEIGHT / 2;

		if (position.x < PLATFORM_WIDTH / 2) {
			velocity.x = -velocity.x;
			position.x = PLATFORM_WIDTH / 2;
		}
		if (position.x > GameScreen.GAME_WIDTH - PLATFORM_WIDTH / 2) {
			velocity.x = -velocity.x;
			position.x = GameScreen.GAME_HEIGHT - PLATFORM_WIDTH / 2;
		}

		stateTime += delta;
		
	}

	@Override
	public void draw(SpriteBatch batcher) {
		batcher.begin();
		batcher.draw(texture, this.position.x, this.position.y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
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

}
