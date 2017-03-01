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

	public float wait_time=1.5f;
	
	public MissilePing(Vector2 _v,float _a, float _s, boolean _b)
	{

		
		super (_v, _a, _s, _b);
		speed=1;
		lifetime=5;
	}
	

	
	@Override
	public void preupdate(float _d)
	{
		
		if (wait_time>0)
		{
			wait_time-=_d;
			
			speed=1;
		
			if (wait_time<=0)
			{
				speed=2350+(int)(Math.random()*250);
				wait_time=0.10f;
			}
			
			
		}
		
		
	}
	
}
