package com.mygdx.equip.weapon;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.entity.missiles.Missile;
import com.mygdx.entity.missiles.MissileSimple;
import com.mygdx.equip.weapon.attr.WeaponAttribute;
import com.mygdx.equip.weapon.attr.WeaponAttributeAccuracy;
import com.mygdx.equip.weapon.attr.WeaponAttributeAttackSpeed;
import com.mygdx.equip.weapon.attr.WeaponAttributeClipSize;
import com.mygdx.equip.weapon.attr.WeaponAttributeDamage;
import com.mygdx.equip.weapon.attr.WeaponAttributeReloadSpeed;
import com.mygdx.equip.weapon.attr.WeaponAttributeStability;
import com.mygdx.game.GScreen;

public class Weapon {

	public float base_damage=777;
	public float base_missile_count;
	public float base_shoot_cooldown;
	public float base_dispersion;
	public float base_dispersion_additional;
	public float base_ammo_size;
	public float base_reload_time;
	
	public float total_damage;
	public float total_missile_count;
	public float total_shoot_cooldown;
	public float total_dispersion;
	public float total_dispersion_additional;
	public float total_ammo_size;
	public float total_reload_time;
	
	public float level=1.0f;
	
	public float attr_point;
	public float attr_count;
	
	public float need_warm;
	public float warm;
	
	public int ammo;
	public float cd;
	public float disp;
	public float add_disp;
	public float reload_timer;
	
	public Sprite spr=new Sprite(new Texture(Gdx.files.internal("icon_firle.png")));
	
	public String name;
	
	public boolean gennable=true;
	
	public List<WeaponAttribute> Available_attribute_list = new ArrayList<WeaponAttribute>();
	
	//public List<WeaponAttribute> Attribute_list_heap = new ArrayList<WeaponAttribute>();
	
	//public Missile missile=new Missile();
	
	public List<WeaponAttribute> Attribute_list = new ArrayList<WeaponAttribute>();
	
	
	
		public Weapon()
		{
			super();
			Available_attribute_list.add(new WeaponAttributeDamage());
			Available_attribute_list.add(new WeaponAttributeAttackSpeed());
			Available_attribute_list.add(new WeaponAttributeAccuracy());
			Available_attribute_list.add(new WeaponAttributeStability());
			Available_attribute_list.add(new WeaponAttributeClipSize());
			Available_attribute_list.add(new WeaponAttributeReloadSpeed());
			
			
			//update_stats();
			//generate();
			//update_attributes_bonus();
			
		
		}
		

		
		public void update_attributes_bonus()
		{
			
			total_damage=base_damage;
			total_missile_count=base_missile_count;
			total_shoot_cooldown=base_shoot_cooldown;
			total_dispersion=base_dispersion;
			total_dispersion_additional=base_dispersion_additional;
			total_ammo_size=base_ammo_size;
			total_reload_time=base_reload_time;
			
			//System.out.println("base_damage="+base_damage);
			
			for (int i=0; i<Attribute_list.size(); i++)
			{
				Attribute_list.get(i).calculate(this);
			}
			
			//update_attributes_bonus();
			//System.out.println("damage^ "+total_damage);
			//System.out.println("damage^ "+total_damage);
			
		}
		
		public Missile get_missile(Entity pl)
		{
			return new MissileSimple(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.spr.getRotation()+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(200)+600.0f),
					pl.is_AI);
		}
		
		public Sound get_shoot_sound()
		{
			return null;
		}
		
		public void reload_start()
		{
			
		}
		
		public void generate()
		{
			total_damage=base_damage*level;
					
			
			attr_point=level*10;
			
			
			
			attr_count=(int) (GScreen.rnd(3)+1);
			
			
			
			for (int i=0; i<-(attr_count-Available_attribute_list.size()); i++)
			{
				Available_attribute_list.remove((int)(Math.random()*Available_attribute_list.size()));
				i--;
			}
			
			for (int i=0; i<500; i++)
			{
				if (!Available_attribute_list.isEmpty())
				{
					
					for (int j=0; j<Available_attribute_list.size(); j++)
					{
						WeaponAttribute aal=Available_attribute_list.get(j);
						
						if 	(
								(aal.cost>attr_point)//если очков на новый атрибут не хватает
								||
								(aal.level>=aal.max_level)//или бонус достиг максимального уровня
							)
								
						{
								Attribute_list.add(aal);
								
								Available_attribute_list.remove(j);
								j--;
						}
					}
					
					
					if (!Available_attribute_list.isEmpty())
					{
						WeaponAttribute wa=Available_attribute_list.get((int)(Math.random()*Available_attribute_list.size()));
						
						wa.level++;
						attr_point-=wa.cost;
					}
					
					
					
					
				}
				else
				{break;}
				
				//список бонусов, на которые нам хватает очков, подготовлен
				
				
				

			}
			 //
			
			update_attributes_bonus();
			
			//System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
			/*for (int i=0; i<Attribute_list.size(); i++)
			{
				//Available_attribute_list.add(new WeaponAttributeDamage());
				
				System.out.println("attr: ["+Attribute_list.get(i).name+"] (v"+Attribute_list.get(i).level/10f+")");
			}*/
			
			//System.out.println("damage^ "+total_damage);
			
			
			
			
		}
		
		public String get_name()
		{
			return name;
		}
		
		public float get_dispersion()
		{
			return GScreen.rnd(total_dispersion)-(total_dispersion)/2f;
		}
		
		//public void
}
