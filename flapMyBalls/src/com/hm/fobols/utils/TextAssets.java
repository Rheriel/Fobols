package com.hm.fobols.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class TextAssets {
	public AtlasRegion Zero;
	public AtlasRegion One;
	public AtlasRegion Two;
	public AtlasRegion Three;
	public AtlasRegion Four;
	public AtlasRegion Five;
	public AtlasRegion Six;
	public AtlasRegion Seven;
	public AtlasRegion Eight;
	public AtlasRegion Nine;
	public AtlasRegion Colon;
	
	private TextureAtlas atlas; 
	
	TextAssets(){
		atlas = new TextureAtlas(Gdx.files.internal("images/gamePackText.atlas"));
		Zero = atlas.findRegion("Zero");
		One = atlas.findRegion("One");
		Two = atlas.findRegion("Two");
		Three = atlas.findRegion("Three");
		Four = atlas.findRegion("Four");
		Five = atlas.findRegion("Five");
		Six = atlas.findRegion("Six");
		Seven = atlas.findRegion("Seven");
		Eight = atlas.findRegion("Eight");
		Nine = atlas.findRegion("Nine");
		Colon = atlas.findRegion("Colon");
			
	}
	
	public void dispose(){
		atlas.dispose();
	}
}
