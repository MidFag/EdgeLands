package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.game.Assets;


public class DecorTubeCarcas extends DecorTube {

	public DecorTubeCarcas(Vector2 _v,boolean _custom) {
		
		super(_v,_custom);
		
		isCarcas=true;
		armored[0]=null;
		armored[1]=null;
		//armored_shield=null;
		
		armored_shield.value=100;
		armored_shield.total_value=100;
		armored_shield.total_regen_speed=0;
		
		is_decor=true;
		is_AI=false;
		is_player=false;
		
		custom_phys=true;
		
		//is_decor=true;
		
		spr.setTexture(Assets.tube_carcas);
		spr.setSize(40, 64);
		
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Nearest); 
		
		
		
		speed=0;
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	

	
	

}
