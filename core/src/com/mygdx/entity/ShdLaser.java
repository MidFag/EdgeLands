package com.mygdx.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GScreen;
import com.mygdx.game.Main;

public class ShdLaser extends Shd {

	
	public ShdLaser(Vector2 _s, Vector2 _e)
	{
		super (_s,_e);
		
		start=_s;
		end=_e;
		
		
		
	}
	
	@Override
	public void draw()
	{
		Main.shapeRenderer.setColor(0.2f,0.8f,0.6f,lifetime);
		Main.shapeRenderer.rectLine(start, end, 7);
	}
	
	
	
}
