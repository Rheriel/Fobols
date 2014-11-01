package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.constants.GameConstants;
import com.na76.flapmyballs.handlers.AssetLoader;
import com.na76.flapmyballs.interfaces.Collidable;

public class EvilPlatform  extends Platform implements Collidable {

	public EvilPlatform(float x, float y){
		super(x,y);
		position = new Vector2(x, y);
		velocity = new Vector2(0, GameConstants.GAME_VELOCITY);
		this.stateTime = 0;
		this.texture = AssetLoader.evilPlatform;
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

}
