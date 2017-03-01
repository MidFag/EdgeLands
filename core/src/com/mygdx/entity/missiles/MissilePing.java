/**
 * www
 */
package com.mygdx.entity.missiles;


import com.badlogic.gdx.math.Vector2;

/**
 * @author MidFag
 *
 */


public class MissilePing extends Missile {

	public float wait_time=3;
	
	public MissilePing(Vector2 _v,float _a, float _s, boolean _b)
	{

		
		super (_v, _a, _s, _b);
		speed=1;
		lifetime=5;
	}
	

	
	@Override
	public void sub_update(float _d)
	{
		if (wait_time>0)
		{
			wait_time-=_d;
		
		
			if (wait_time<=0)
			{
				speed=600+(int)(Math.random()*150);
			}
		}
	}
	
}
