package com.mygdx.equip.energoshield.attr;

import com.mygdx.equip.energoshield.Energoshield;

public class ESAttributeValue extends ESAttribute {
	

	
	public ESAttributeValue()
	{
		name="shield";
		cost=6;
		max_level=20;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_value=_e.base_value*(1+level*0.1f);
	}
}
