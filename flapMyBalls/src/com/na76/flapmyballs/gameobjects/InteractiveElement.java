package com.na76.flapmyballs.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class InteractiveElement {
	private AtlasRegion texture;
	private float width;
	private float height;
	private float downPadding;
	private float upPadding;
	private float leftPadding;
	private float rightPadding;
	private float xStartPosition;
	private float yStartPosition;

	public void setTexture(AtlasRegion texture){
		this.texture = texture;
	}
	
	public AtlasRegion getTexture(){
		return texture;
	}
	
 	public void setWidth(float width){
		this.width=width;
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setHeight(float height){
		this.height = height;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void setDownBorder(float downborder){
		downPadding = downborder;
	}
	
	public float getDownBorder(){
		return downPadding;
	}
	
	public void setUpBorder(float upborder){
		upPadding = upborder;
	}
	
	public float getUpBorder(){
		return upPadding;
	}
	
	public void setLeftBorder(float leftborder){
		leftPadding = leftborder;
	}
	
	public float getLeftBorder(){
		return leftPadding;
	}
	
	public void setRightBorder(float rightborder){
		rightPadding = rightborder;
	}
	
	public float getRightBorder(){
		return rightPadding;
	}
	
	public void setXPosition (float xstartposition){
		xStartPosition = xstartposition;
	}
	
	public float getXStartPosition(){
		return xStartPosition;
	}
	
	public void setYPosition (float ystartposition){
		yStartPosition = ystartposition;
	}
	
	public float getYStartPosition(){
		return yStartPosition;
	}
	
	public void setBorders(float left, float right, float down, float up){
		leftPadding = left;
		rightPadding = right;
		downPadding = down;
		upPadding = up;
	}
	
	public void setProperties(float width, float height, float xposition, float yposition){
		width = width;
		height = height;
		xStartPosition = xposition;
		yStartPosition = yposition;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(texture, xStartPosition, yStartPosition, width, height);
	}
	
	public BoundingBox getBounds(){
		BoundingBox Bounds;
		float BoundXStartPosition;
		float BoundYStartPosition;
		float BoundXEndPosition;
		float BoundYEndPosition;
		Vector3 StartBound;
		Vector3 EndBound;
		BoundXStartPosition = xStartPosition + leftPadding;
		BoundYStartPosition = yStartPosition + downPadding;
		BoundXEndPosition = xStartPosition + width - rightPadding;
		BoundYEndPosition = yStartPosition + height - upPadding;
		StartBound = new Vector3(BoundXStartPosition, BoundYStartPosition,0);
		EndBound = new Vector3(BoundXEndPosition,BoundYEndPosition,0);
		Bounds = new BoundingBox (StartBound,EndBound);
		return Bounds;
	}
	
	
	
	
}
