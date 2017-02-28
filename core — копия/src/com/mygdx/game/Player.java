package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player {
	Sprite spr=new Sprite(new Texture(Gdx.files.internal("char.png")));
	public Vector2 pos=new Vector2();
	
	public float trigger_reload_time;
	
	public GScreen gs;
	
	public float teleport_cooldown;
	
	public List<Phys> Phys_list_local = new ArrayList<Phys>();
	
	public Player()
	{
		GScreen.Phys_list.add(new Phys(new Vector2(pos.x-25,pos.y+1),new Vector2(pos.x+25,pos.y-1),false,this));
		Phys_list_local.add(GScreen.Phys_list.get(GScreen.Phys_list.size()-1));
		
		//System.out.println("YES "+Phys_list_local.get(0).parent);
		
		GScreen.Phys_list.add(new Phys(new Vector2(pos.x-1,pos.y+25),new Vector2(pos.x+1,pos.y-25),false,this));
		Phys_list_local.add(GScreen.Phys_list.get(GScreen.Phys_list.size()-1));
	}
	
	@SuppressWarnings("static-access")
	public void update(float _d)
	{
		trigger_reload_time-=_d;
		
		if ((InputHandler.MB)&&(trigger_reload_time<=0))
		{
			trigger_reload_time=1f;
			
			for (int zz=0; zz<20; zz++)
			{
				gs.Missile_list.add(new Missile(new Vector2(pos.x,pos.y),(float) Math.toRadians(360-spr.getRotation()+gs.rnd(40)-20),(gs.rnd(200)+5.0f)/1f,false));
			}
		}
		
		if ((teleport_cooldown<=0)&(Gdx.input.isKeyPressed(Keys.E)))
		{
			GScreen.pl.pos.x=InputHandler.posx;
			GScreen.pl.pos.y=InputHandler.posy;
			
			teleport_cooldown=3;;
		}
		
		if (teleport_cooldown>0){teleport_cooldown-=_d;}
	}
	
	public void draw()
	{
		Main.batch.begin();
			spr.setPosition(pos.x-25, pos.y-10);
			spr.draw(Main.batch);
		Main.batch.end();
	}
	
}
