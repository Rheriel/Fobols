package com.na76.flapmyballs.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static final int	DUDE_WALK_ANIMATION_FRAMES = 11;

	public static final float RUNNING_FRAME_DURATION = 0.06f;

	public static TextureAtlas itemAtlas;
	
	
	/* Animation */

	public static TextureAtlas dudeAtlas;
	

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

	

	public static void load() {

		backGround = new Texture(Gdx.files.internal("data/bg.png"));

		itemAtlas = new TextureAtlas(Gdx.files.internal("data/Items/Atlas/items.txt"));
		
		spike = itemAtlas.findRegion("spikes_top");
		
		spikeSprite = new Sprite(spike);
		
		flippedSpike = itemAtlas.findRegion("spikes_bottom");
		
		flippedSpike.flip(false, true);

		dudeAtlas = new TextureAtlas(Gdx.files.internal("data/Player/p1_walk/Pack/dudeWalk.atlas"), true);

		dudeIdleLeft = dudeAtlas.findRegion("p1_walk01");
		
		// TODO Refactor to put every char texture on a single TextureAtlas.
		dudeFallingAtlas = new TextureAtlas(Gdx.files.internal("data/Player/p1.txt"));
		dudeFallingLeft = dudeFallingAtlas.findRegion("p1_jump");
		dudeFallingLeft.flip(false, true); // TODO Image is flipped in Atlas, fix it.
		dudeFallingRight = new TextureRegion(dudeFallingLeft);
		dudeFallingLeft.flip(true, false); // TODO Image is flipped in Atlas, fix it.

		
		dudeIdleRight = new TextureRegion(dudeIdleLeft);

		dudeIdleRight.flip(true, false);
		
		dudeWalkLeftFrames = new TextureRegion[DUDE_WALK_ANIMATION_FRAMES];
		dudeWalkRightFrames = new TextureRegion[DUDE_WALK_ANIMATION_FRAMES];

		for (int i = 0; i < 11; i++) {

			String region = "";

			if (i < 9){
				region = "p1_walk0" + (i+1);
			} else {
				region = "p1_walk" + (i+1);
			}
			System.out.println(region);
			
			dudeWalkRightFrames[i] = dudeAtlas.findRegion(region);
		}

		dudeWalkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, dudeWalkLeftFrames);

		for (int i = 0; i < 11; i++) {
			dudeWalkLeftFrames[i] = new TextureRegion(dudeWalkRightFrames[i]);
			dudeWalkLeftFrames[i].flip(true, false);
		}

		dudeWalkRightAnimation = new Animation(RUNNING_FRAME_DURATION, dudeWalkRightFrames);

	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		backGround.dispose();
		itemAtlas.dispose();
		dudeAtlas.dispose();
	}

}
