package com.midfag.entity.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.Phys;

public class EntityVizjun extends Entity {

	//public float dead_time; 04.27.12 20.02.2017
	
	public float burrow_time;
	public float jump_time;
	public float jump_prepare_time;
	
	Sprite spr_dust=new Sprite(new Texture(Gdx.files.internal("dust.png")));
	
	public EntityVizjun(Vector2 _v, boolean _custom) {
		
		super(_v, _custom);
		
		is_AI=true;
		is_player=false;
		
		burrow=true;
		
		spr.setTexture(new Texture(Gdx.files.internal("vizjun.png")));
		spr.setSize(42, 42);
		
		armored_shield=new Energoshield();
		
		speed=180;
		armored_shield.value=120;
		armored_shield.total_value=120;
		

		
		/*
		trigger_cooldown_value=0.5f;
		missile_count=0;
		base_disp=15;*/
		
		burrow_time=(float) (2+Math.random()*2);
		//is_player
		
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void some_draw()
	{

			if (burrow)
			for (int i=0; i<20; i++)
			{
				spr_dust.setPosition(pos.x-18+GScreen.rnd(60)-30,pos.y-18+GScreen.rnd(60)-30);
				spr_dust.draw(Main.batch);
			}

	}
	
	@Override
	public void some_update(float _d)
	{
		burrow_time-=_d;
		float rn=0;
		
		if (burrow)
		{
			if (pos.dst(GScreen.pl.pos)<30)
			{
				GScreen.pl.slow+=0.1f;
			}
		}
		
		if ((burrow_time<=0)&&(burrow))
		{
			
			burrow=false;
			jump_prepare_time=0.65f;
			
			spr.rotate(GScreen.rnd(45)-22.5f);
			
			speed*=0.333f;
			
			can_rotate=false;
		}
		
		if (jump_prepare_time>0)
		{
			jump_prepare_time-=_d;
			
			
			
			if (jump_prepare_time<=0)
			{
				jump_time=(float) (Math.random()*0.1f+0.9f);
				
				if (pos.dst(GScreen.pl.pos)<400)
				{Assets.saw.play(1-pos.dst(GScreen.pl.pos)/200);}
				
				//rn=GScreen.rnd(10)-5;
				

			}
		}
		
		if (jump_time>0)
		{
			jump_time-=_d;
			
			if (pos.dst(GScreen.pl.pos)<15)
			{
				GScreen.pl.hit_action(null, 100*_d);
				
				//A//ssets.saw.play();
			}
			
			Phys po=null;
			Phys near_object=null;
			float near_dist=99999;
			
			for (int i=0; i<GScreen.Phys_list.size();i++)
		      {
				po=GScreen.Phys_list.get(i).is_contact(pos.x,pos.y,pos.x+GScreen.sinR(360-spr.getRotation())*speed*10*_d,pos.y+GScreen.cosR(360-spr.getRotation())*speed*10*_d,GScreen.sinR(360-spr.getRotation()+rn),GScreen.cosR(360-spr.getRotation()+rn),speed*_d*10);
		        
		       	if (po!=null)
		        if ((po.vector_mul<near_dist)&&(po.parent==null))
		        {
		        	near_dist=po.vector_mul;
		        	near_object=po;
		        }
		      }
			
			if (near_object==null)
			{move(GScreen.sinR(360-spr.getRotation())*speed*10,GScreen.cosR(360-spr.getRotation())*speed*10,_d);}
			else
			{
				move(GScreen.sinR(360-spr.getRotation())*near_object.vector_mul*0.8f,GScreen.cosR(360-spr.getRotation())*near_object.vector_mul*0.8f,_d);
				jump_time=0;
			}
			
			

		        
			//spr.rotate(1);
			
			if (jump_time<=0)
			{
				burrow_time=(float) (2+Math.random()*1);
				burrow=true;
				
				speed/=0.333f;
				
				can_rotate=true;
				
				
			}
		}
	}
	
	

}
