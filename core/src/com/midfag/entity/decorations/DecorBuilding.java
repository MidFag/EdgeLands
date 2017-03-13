package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorBuilding extends Entity {
	
	
	private float alpha=1;

	public DecorBuilding(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);

		
		is_decor=true;
		is_AI=false;

		id="building";
		
		spr.setTexture(Assets.building_wall_in);
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
		return new DecorBuilding(new Vector2(),true);
	}
	
	@Override
	public void some_draw()
	{
	
	}
	
	@Override
	public void some_update(float _d)
	{
		if ((Math.abs(GScreen.pl.pos.x-pos.x)<100)&&(Math.abs(GScreen.pl.pos.y-pos.y)<70)&&(GScreen.pl.pos.y>pos.y))
		{
			alpha-=_d*5;
		}
		else
		{
			alpha+=_d*5;
		}
		
		if (alpha<0.25f) {alpha=0.25f;}
		if (alpha>1) {alpha=1;}
		spr.setAlpha(alpha);
	}
	

}
