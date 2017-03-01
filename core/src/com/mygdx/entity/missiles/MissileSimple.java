/**
 * www
 */
package com.mygdx.entity.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Shd;
import com.mygdx.entity.ShdLaser;
import com.mygdx.entity.ShdSmoke;

/**
 * @author MidFag
 *
 */


public class MissileSimple extends Missile {

	public float wait_time=1;
	
	public MissileSimple(Vector2 _v,float _a, float _s, boolean _b)
	{
		super(_v, _a, _s, _b);
		
		have_shd=true;
		
		
	}
	

	
	
}
