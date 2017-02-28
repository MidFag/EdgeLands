package com.mygdx.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;

public class DecorStoneBarak extends Entity {

	public DecorStoneBarak(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);
		
		is_AI=false;
		is_player=false;
		
		spr.setTexture(new Texture(Gdx.files.internal("object00.png")));
		spr.setSize(147*2, 53*2);
		
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	

}
