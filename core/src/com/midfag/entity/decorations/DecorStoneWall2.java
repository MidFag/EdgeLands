package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorStoneWall2 extends DecorStoneWall {

	public DecorStoneWall2(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);

		
		spr.setTexture(Assets.stone_wall_02);

		id="stone_wall2";
		
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(5.0f, 50f);
		diagonal=true;
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorStoneWall2(new Vector2(),true);
	}
	

}
