package com.mygdx.equip.energoshield.attr;

import com.mygdx.equip.energoshield.Energoshield;


public class ESAttributeReflect extends ESAttribute {
	

	
	public ESAttributeReflect()
	{
		name="regeneration";
		cost=2;
		max_level=100;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_reflect=_e.base_reflect+(level*1f);
	}
}
