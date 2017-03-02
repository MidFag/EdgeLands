package com.midfag.equip.energoshield.attr;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.MissileCharge;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.GScreen;


public class ESAttributeCharge extends ESAttribute {
	
	public float charge_time;
	
	public ESAttributeCharge()
	{
		name="charge";
		cost=2;
		max_level=100;
		
		base=false;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_value=_e.base_value*(1+level*0.1f);
	}
	
	@Override
	public void update(float _d, Entity _e)
	{
		charge_time-=_d;
		
		if (charge_time<=0)
		{
			charge_time+=5;
			
			for (int i=0; i<32; i++)
			{
				GScreen.Missile_list.add(new MissileCharge(new Vector2(_e.pos.x,_e.pos.y), (float)(Math.random()*360.0f),(float) (500.0f+Math.random()*277),_e.is_AI));
				GScreen.Missile_list.get(GScreen.Missile_list.size()-1).damage=level*5;
				GScreen.Missile_list.get(GScreen.Missile_list.size()-1).lifetime=(float) (Math.random()*0.5f)+0.5f;
			}
		}
	}
	
	@Override
	public String get_attr_value()
	{
		return ""+level*5.0f;
	}
	
	@Override
	public void get_attr_descr()
	{
		
	}
	
}
