package com.na76.flapmyballs.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.na76.flapmyballs.gameobjects.Spikes;

public class AssetLoader {

	public static final int	DUDE_WALK_ANIMATION_FRAMES = 4;

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
	
	public static AtlasRegion dudeDead;

	public static void load() {

		backGround = new Texture(Gdx.files.internal("bg.png"));
		
		textureAtlas = new TextureAtlas(Gdx.files.internal("images/gameTextures.atlas"));

		spike = textureAtlas.findRegion("spikes");

		flippedSpike = new AtlasRegion(spike);
		
		flippedSpike.flip(true, true);
		
		platform = textureAtlas.findRegion("platform");
		platform.flip(false, true);

		dudeIdleLeft = textureAtlas.findRegion("fobolWalk",1);
		
		dudeFallingLeft = textureAtlas.findRegion("fobol_idle");
		dudeFallingLeft.flip(false, true); // TODO Image is flipped in Atlas, fix it.
		dudeFallingRight = new TextureRegion(dudeFallingLeft);
		dudeFallingLeft.flip(true, false); // TODO Image is flipped in Atlas, fix it.

		
		dudeIdleRight = new TextureRegion(dudeIdleLeft);

		dudeIdleRight.flip(true, false);
		
		dudeWalkLeftFrames = new TextureRegion[DUDE_WALK_ANIMATION_FRAMES];
		dudeWalkRightFrames = new TextureRegion[DUDE_WALK_ANIMATION_FRAMES];

		for (int i = 1; i < 5; i++) {

			dudeWalkRightFrames[i - 1] = textureAtlas.findRegion("fobolWalk",i);
		}

		dudeWalkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, dudeWalkLeftFrames);

		for (int i = 0; i < 4; i++) {
			dudeWalkRightFrames[i].flip(false, true);
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
