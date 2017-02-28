package com.mygdx.equip.weapon.attr;

import com.mygdx.entity.Entity;
import com.mygdx.equip.weapon.Weapon;
import com.mygdx.game.Assets;

public class WeaponAttributeChaos extends WeaponAttribute {

	public float chaos_time=3;
	
	public WeaponAttributeChaos()
	{
		super();
		
		max_level=1;
		cost=100;
		
		name="accuracy";
		
		
	}
	
	@Override
	public void calculate(Weapon _w)
	{
		//float bonus=1-level/(level+10);
	
	}
	
	@Override
	public void update(float _d, Entity pl, Weapon _w)
	{
		System.out.println("CHAOS! "+chaos_time);
		if (chaos_time>0)
		{
			chaos_time-=_d;
			
			if (chaos_time<=0)
			{
				chaos_time+=3;
				
				
				_w.Attribute_list.clear();
				_w.Attribute_list.add(new WeaponAttributeChaos());
				
				_w.base_damage=(float)(Math.random()*3)+7;
				_w.base_dispersion=(float)(Math.random()*90);
				_w.base_dispersion_additional=(float)(Math.random()*90);
				
				_w.base_reload_time=(float)(Math.random()*3);
				_w.base_shoot_cooldown=(float)(Math.random()*1)+0.2f;
				
				_w.Available_attribute_list.add(new WeaponAttributeDamage());
				_w.Available_attribute_list.add(new WeaponAttributeAttackSpeed());
				_w.Available_attribute_list.add(new WeaponAttributeAccuracy());
				_w.Available_attribute_list.add(new WeaponAttributeStability());
				_w.Available_attribute_list.add(new WeaponAttributeClipSize());
				_w.Available_attribute_list.add(new WeaponAttributeReloadSpeed());
				
				
				System.out.println("CHAOS!");
				
				Assets.chaos.play();
				_w.generate();
				
				_w.update_attributes_bonus();
				
				_w.total_missile_count=(int) (Math.random()*10)+1;
			}
		}
	}
	
}
