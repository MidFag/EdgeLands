package com.mygdx.equip.energoshield.attr;

import com.mygdx.equip.energoshield.Energoshield;

public class ESAttributeRegen extends ESAttribute {
	

	
	public ESAttributeRegen()
	{
		name="regeneration";
		cost=2.5f;
		max_level=10;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_regen_speed=_e.base_regen_speed*(1+level*0.10f)+level*2;
	}
}
