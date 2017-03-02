/**
 * www
 */
package com.midfag.entity.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * @author MidFag
 *
 */


public class MissileCharge extends Missile {

	public float wait_time=1;
	
	public MissileCharge(Vector2 _v,float _a, float _s, boolean _b)
	{
		super(_v, _a, _s, _b);
		
		for (int i=0; i<3; i++)
		{spr_anim[i]=new Sprite(new Texture(Gdx.files.internal("missile_electro"+(i+1)+".png")));}
		
		random_anim=3;
	}
	
	
}
