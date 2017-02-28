package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class DecorStoneBarak extends Entity {

	public DecorStoneBarak(Vector2 _v) {
		
		super(_v);
		
		is_AI=false;
		is_player=false;
		
		spr.setTexture(new Texture(Gdx.files.internal("object00.png")));
		spr.setSize(147*2, 53*2);
		
		shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	

}
