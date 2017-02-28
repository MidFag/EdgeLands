package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MissileParticlePiece extends Missile {

	
	public float rotate_speed=(float) (Math.random()*2-1.0f);;
	
	public MissileParticlePiece(Vector2 _v, float _a, float _s, boolean _b) {
		super(_v, _a, _s, _b);
		
		spr.setTexture(Assets.diod);
		
		lifetime=(float) Math.random()*2;
		
		if (Math.random()<0.5)
		{spr.setTexture(new Texture(Gdx.files.internal("particle.png")));}
		else
		{spr.setTexture(new Texture(Gdx.files.internal("particle2.png")));}
	
		spr.setSize((float)(Math.random()*100+5),(float)(Math.random()*100+5));
		//tex.setSize(100, 200);
		// TODO Auto-generated constructor stub
	}
	
	
	public void sub_update(float _d)
	{
		speed*=0.95f;
		speed-=0.001f;
		spr.setScale(spr.getScaleX()*0.95f);
		spr.rotate(speed*5f);
	}
	
	
}
