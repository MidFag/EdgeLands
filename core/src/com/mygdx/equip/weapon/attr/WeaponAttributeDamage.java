package com.mygdx.equip.weapon.attr;

import com.mygdx.equip.weapon.Weapon;

public class WeaponAttributeDamage extends WeaponAttribute {

	public WeaponAttributeDamage()
	{
		max_level=100;
		cost=6;
		
		name="damage";
	}
	
	public void calculate(Weapon _w)
	{
		_w.total_damage=_w.base_damage*(1f+0.2f*level);
		//_w.total_damage=_w.base_damage;
	}
}
