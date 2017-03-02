package com.midfag.equip.energoshield;



public class EnergoshieldSimple extends Energoshield {
	

	
	public EnergoshieldSimple()
	{
		base_value=1000;
		base_regen_speed=100;
		base_reflect=5;
		
		name="Sample shield";
		
		generate();
		update_attributes_bonus();
	}
	

}
