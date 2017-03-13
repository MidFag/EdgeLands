package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorBuildingWall extends DecorBuilding {
	
	
	private float alpha=1;

	public DecorBuildingWall(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);

		
		

		id="building_wall";
		
		spr.setTexture(Assets.building_wall_out);
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(100.0f, 5f);

		
		diagonal=true;
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorBuildingWall(new Vector2(),true);
	}
	
	@Override
	public void some_draw()
	{
	
	}
	

	

}
