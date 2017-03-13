package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorStonePilon extends DecorStoneWall {

	public DecorStonePilon(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);

		
		spr.setTexture(Assets.stone_pilon_01);

		id="stone_pilon";
		
		diagonal=false;
		
		spr.setSize(23, 70);
		spr.setOrigin(11.5f, 5);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorStonePilon(new Vector2(),true);
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		
		Phys p=new Phys(new Vector2(pos.x-15,pos.y),new Vector2(pos.x+15,pos.y-0),true,this,true);
		
		//System.out.println("X "+x+"; Y "+y);
		
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		
		
		
	}
	

}
