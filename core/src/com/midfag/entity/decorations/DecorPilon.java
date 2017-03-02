package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;

public class DecorPilon extends Entity {

	public DecorPilon(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);
		
		is_AI=false;
		is_player=false;
		
		spr.setTexture(new Texture(Gdx.files.internal("decor_pilon.png")));
		spr.setSize(15, 15);
		
		id="pilon";
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorPilon(new Vector2(),true);
	}

}
