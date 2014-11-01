package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
	
	protected Rectangle hitbox;
	
	public abstract void update(float delta);
	public abstract void draw(SpriteBatch batcher);
	
	public void drawBounds(ShapeRenderer shapeRenderer){
		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Line);
		Rectangle bounds = this.getHitbox();
		shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		// End ShapeRenderer
		shapeRenderer.end();
	}
	
	public Rectangle getHitbox(){
		return this.hitbox;
	}

}
