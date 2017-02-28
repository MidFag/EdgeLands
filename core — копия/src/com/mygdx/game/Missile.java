package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;



public class Missile {
	public Vector2 pos=new Vector2();
	public float angle;
	public float speed;
	Sprite spr=new Sprite(new Texture(Gdx.files.internal("missile.png")));
	public float lifetime;
	
	public boolean is_enemy;
	
	public GScreen gs;
	
	public Vector2 vector_len=new Vector2();
	
	public float dx;
	public float dy;
	
	public float dynx;
	public float dyny;
	
	
	
	public Missile(Vector2 _v,float _a, float _s, boolean _b)
	{
		lifetime=(float)(Math.random()*0.2f)+2f;
		pos=_v;
		angle=_a;
		speed=_s;
		
		is_enemy=_b;
		
		dynx=(float)Math.sin(angle);
		dyny=(float)Math.cos(angle);
		
		
	}
	
	public void update(float _d)
	{
		pos.add(dynx*speed,dyny*speed);
		
		dx=pos.x+dynx*speed;
		dy=pos.y+dyny*speed;
		
		lifetime-=_d;
		
		sub_update(_d);

	}
	
	public void sub_update(float _d)
	{
		
	}
	
	public void check()
	{
		if (this.lifetime<0)
		{
			destr();
			lifetime=0;
		}
	}
	
	public void draw()
	{
		
		Main.batch.begin();
			Main.batch.setColor(Color.WHITE);
			//for (float i=0; i<1; i+=0.1f)
			//{
			//spr.setScale(0.1f, 0.1f);
			spr.setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
			spr.setRotation(360-angle/3.14f*180f);
			spr.draw(Main.batch);
			
			//Main.batch.draw(spr, pos.x, pos.y);
			//}
			//Main.batch.draw(tex,pos.x,pos.y);
			//Main.font.draw(Main.batch, ""+lifetime, pos.x, pos.y);
			//Main.batch.setColor(Color.GREEN);
			//Main.batch.draw(tex,pos.x+(float)Math.sin((angle))*10,pos.y+(float)Math.cos((angle))*10);
		Main.batch.end();
	}
	
	public void destr()
	{
		//System.out.println("REMOVED"+lifetime);
		gs.Missile_list.remove(this);
	}
}
