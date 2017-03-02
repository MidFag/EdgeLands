package com.midfag.equip.energoshield;



public class EnergoshieldRobo extends Energoshield {
	

	
	public EnergoshieldRobo()
	{
		super();
		base_value=100;
		value=100;
		base_regen_speed=25;
		base_reflect=5;
		
		name="Roboshield";
		gennable=false;
		
		update_attributes_bonus();

	}
	

}
