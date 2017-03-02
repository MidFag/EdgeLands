/**
 * www
 */
package com.midfag.entity.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdLaser;

/**
 * @author MidFag
 *
 */


public class MissileLaser extends Missile {

	public float wait_time=1;
	
	public MissileLaser(Vector2 _v,float _a, float _s, boolean _b)
	{
		super(_v, _a, _s, _b);
		
		have_shd=true;
		
		
	}
	
	@Override
	public Shd get_shd(Vector2 _s,Vector2 _e)
	{
		Shd s=new ShdLaser(_s,_e);
		s.lifetime=1;
		
		return s;
	}
	
	
}
