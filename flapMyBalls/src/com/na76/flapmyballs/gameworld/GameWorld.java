package com.na76.flapmyballs.gameworld;

import com.na76.flapmyballs.gameobjects.Dude;


public class GameWorld {

	private Dude dude;
	
	public GameWorld(float midPointX, int midPointY){
		dude = new Dude(midPointX, midPointY - Dude.DUDE_HEIGHT, Dude.DUDE_WIDTH, Dude.DUDE_HEIGHT);
	}

    public void update(float delta) {
    	dude.update(delta);
    }
    
    public Dude getDude(){
    	return dude;
    }
    
}
