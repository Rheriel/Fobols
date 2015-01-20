package com.na76.flapmyballs.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.na76.flapmyballs.gameobjects.Spikes;

public class AssetLoader {

	public static final int	DUDE_WALK_ANIMATION_FRAMES = 6;

	public static final float RUNNING_FRAME_DURATION = 0.06f;

	public static TextureAtlas textureAtlas;
	
	
	/* Animation */

	public static Animation	dudeAnimation;

	public static TextureRegion currentDudeFrame;

	public static float	stateTime;

	public static AtlasRegion dudeIdleLeft;

	public static TextureRegion dudeIdleRight;
	
	public static TextureAtlas dudeFallingAtlas;

	public static TextureRegion dudeFallingLeft; 
	
	public static TextureRegion dudeFallingRight; 
	
	public static TextureRegion[] dudeWalkLeftFrames;

	public static TextureRegion[] dudeWalkRightFrames;

	public static Animation dudeWalkLeftAnimation;

	public static Animation dudeWalkRightAnimation;

	/* Animation - End */

	public static Texture backGround;

	public static Sprite spikeSprite;

	public static AtlasRegion spike;
	
	public static AtlasRegion flippedSpike;

	public static AtlasRegion platform;
	
	public static AtlasRegion evilPlatform;
	
	public static AtlasRegion dudeDead;

	public static void load() {

		backGround = new Texture(Gdx.files.internal("background_01.png"));
		
		textureAtlas = new TextureAtlas(Gdx.files.internal("images/gameTextures.atlas"));

		spike = textureAtlas.findRegion("spikes");

		flippedSpike = new AtlasRegion(spike);
		
		flippedSpike.flip(true, true);
		
		platform = textureAtlas.findRegion("platform",1);
		
		evilPlatform = textureAtlas.findRegion("evilPlatform",1);
		evilPlatform.flip(true, false);

		dudeIdleLeft = textureAtlas.findRegion("blueFobolWalk",1);
		
		dudeFallingLeft = textureAtlas.findRegion("blueFobolIdle");
		dudeFallingRight = new TextureRegion(dudeFallingLeft);

		
		dudeIdleRight = new TextureRegion(dudeIdleLeft);

		dudeIdleRight.flip(true, false);
		
		dudeWalkLeftFrames = new TextureRegion[DUDE_WALK_ANIMATION_FRAMES];
		dudeWalkRightFrames = new TextureRegion[DUDE_WALK_ANIMATION_FRAMES];

		for (int i = 1; i < 7; i++) {

			dudeWalkRightFrames[i - 1] = textureAtlas.findRegion("blueFobolWalk",i);
		}

		dudeWalkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, dudeWalkLeftFrames);

		for (int i = 0; i < 6; i++) {
			dudeWalkRightFrames[i].flip(false, false);
			dudeWalkLeftFrames[i] = new TextureRegion(dudeWalkRightFrames[i]);
			dudeWalkLeftFrames[i].flip(true, false);
		}

		dudeWalkRightAnimation = new Animation(RUNNING_FRAME_DURATION, dudeWalkRightFrames);
		
		dudeDead = textureAtlas.findRegion("p1_hurt");

	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		backGround.dispose();
		textureAtlas.dispose();
	}

}
