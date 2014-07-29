package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.na76.flapmyballs.interfaces.Collidable;

public class Spikes extends GameObject implements Collidable {

	private float width;
	private float height;

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private TextureRegion texture;
	
	public Spikes(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
	}

	public Spikes(float x, float y, float width, float height, TextureRegion texture) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		hitbox = new Rectangle(this.position.x, this.position.y, this.width, this.height);
		this.texture = texture;
	}

	public void setTexture(TextureRegion texture){
		this.texture = texture;
	}
	
	public TextureRegion getTexture(){
		return texture;
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
	
	public void setX(float x) {
		this.position.x = x;
	}

	public void setY(float y) {
		this.position.y = y;
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

	public void setNewBounds(float x, float y, float width, float height) {
		this.hitbox.x = x;
		this.hitbox.y = y;
		this.hitbox.width = width;
		this.hitbox.height = height;
	}

	public void draw(SpriteBatch batcher) {

		// Begin batcher.
		batcher.begin();
		
		// Draw this object
		batcher.draw(this.texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		// End batcher.
		batcher.end();
	}

	public void drawBounds(ShapeRenderer shapeRenderer){
		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Line);
		Rectangle bounds = this.getHitbox();
		shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		shapeRenderer.end();
		// End ShapeRenderer

	}	

}
