package com.mygdx.equip.energoshield;



public class EnergoshieldSimple extends Energoshield {
	

	
	public EnergoshieldSimple()
	{
		base_value=140;
		base_regen_speed=10;
		base_reflect=5;
		
		name="Sample shield";
		
		generate();
		update_attributes_bonus();
	}
	

}
