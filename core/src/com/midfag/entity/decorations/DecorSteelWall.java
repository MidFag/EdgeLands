package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;

public class DecorSteelWall extends Entity {

	public DecorSteelWall(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);
		
		is_AI=false;
		is_player=false;
		
		spr.setTexture(new Texture(Gdx.files.internal("decor_steel_wall.png")));
		spr.setSize(155, 44);
		spr.setOrigin(155/2, 44/2f);
		
		id="steel_wall";
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorSteelWall(new Vector2(),true);
	}

}
