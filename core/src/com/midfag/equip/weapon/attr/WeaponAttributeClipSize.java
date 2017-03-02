package com.midfag.equip.weapon.attr;

import com.midfag.equip.weapon.Weapon;

public class WeaponAttributeClipSize extends WeaponAttribute {

	public WeaponAttributeClipSize()
	{
		max_level=5;
		cost=3;
		
		name="clip size";
	}
	
	 //electro engine https://www.freesound.org/home/pending/ 21.02.2017 04^36^13
	
	
	public void calculate(Weapon _w)
	{
		_w.total_ammo_size=_w.base_ammo_size*(1f+0.5f*level);
	}
}
