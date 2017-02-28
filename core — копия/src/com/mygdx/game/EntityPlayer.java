package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EntityPlayer extends Entity {

	

	public EntityPlayer(Vector2 _v) {
		
		super(_v);
		
		is_AI=false;
		is_player=true;
		
		spr.setTexture(new Texture(Gdx.files.internal("char.png")));
		spr.setSize(51, 21);
		
		shield=1000;
		max_shield=1000;
		
		trigger_cooldown_value=0.5f;
		missile_count=10;
		base_disp=15;
		//is_player
		
		// TODO Auto-generated constructor stub
	}
	
	

}
