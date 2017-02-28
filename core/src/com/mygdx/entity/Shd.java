package com.mygdx.entity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GScreen;

public class Shd {
	
	public Vector2 start=new Vector2();
	public Vector2 end=new Vector2();
	public float lifetime;
	
	public Shd(Vector2 _s, Vector2 _e)
	{
		start=_s;
		end=_e;
	}
	
	public void draw()
	{
		
	}
	
	public void update(float _d)
	{
		lifetime-=_d;
		
		if (lifetime<=0)
		{
			GScreen.Shd_list.remove(this);
		}
	}
	
	
	
}
