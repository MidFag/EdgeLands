package com.midfag.game.GUI.buttons;

import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;

public class ButtonEqWeapon extends Button {

	public Object obj;
	public float mov;
	
	public float info_x=200;
	public float info_y=700-100;
	
	public int inventory_id;
	
	public ButtonEqWeapon(float _x, float _y, int _id)
	{
		super (_x,_y);
		mov=0;
		
		inventory_id=_id;
		
		update_object();
		
		
	}
	
	public void update_object()
	{
		if (inventory_id>=0)
		{
			obj=null;
			if (GScreen.pl.inventory[inventory_id] instanceof Weapon){obj=(Weapon)GScreen.pl.inventory[inventory_id];}
			if (GScreen.pl.inventory[inventory_id] instanceof Energoshield){obj=(Energoshield)GScreen.pl.inventory[inventory_id];}
		}
		
		if (inventory_id==-1)
		{
			obj=(Weapon)GScreen.pl.armored[0];
			
		}
		
		if (inventory_id==-2)
		{
			obj=(Weapon)GScreen.pl.armored[1];
			
		}
				
		if (inventory_id==-5)
		{
			obj=(Energoshield)GScreen.pl.armored_shield;
		}
		
		if (inventory_id==99)
		{
			pos.x=InputHandler.realx+20;
			pos.y=InputHandler.realy+20;
			
			off_bg=true;
			
		}
		
	}
	
	@Override
	public void entry()
	{
		update_object();
	}
	
	public void color_it(float _a, float _b)
	{
		if (_a==_b)
		{Main.font.setColor(0.9f, 0.95f, 1.0f, 1);}
		else
		{Main.font.setColor(0.65f, 1.0f, 0.75f, 1);}
	}
	
	
	@Override
	public void some_update(float _d)
	{
		if (!GScreen.show_equip){need_remove=true;}
		update_object();
		

		
		if (is_overlap())
		{
			mov=0;
			//need_remove=true;
			//float tx=150;
			
			//float ty=700-250;
			/*Main.shapeRenderer_static.setColor(0.1f, 0.12f, 0.15f,0.5f);
			Main.shapeRenderer_static.rect(info_x-10, info_y-10-200, 300, 220);*/
			
			Main.font.setColor(1.0f, 1.0f, 1.0f, 1);
			Main.batch_static.draw(Assets.rect, info_x-10, info_y-10-230);
			
			
			if (obj instanceof Weapon)
			{
				
				Weapon w=((Weapon)obj);
				draw_info(""+((Weapon)obj).get_name(),"");
				//mov+=25;
				//draw_info("Bonuses: ",""+((Weapon)obj).attr_count);
				mov+=25;
				color_it (w.total_damage,w.base_damage); draw_info("Урон: ",""+w.total_damage);
				mov+=5;
				color_it (w.total_shoot_cooldown,w.base_shoot_cooldown); draw_info("Скорострельность: ",""+Math.round(1.0f/w.total_shoot_cooldown*10.0f)/10.0f);
				color_it (w.total_dispersion,w.base_dispersion);draw_info("Dispersion: ",""+Math.round(w.total_dispersion));
				color_it (w.total_dispersion_additional,w.base_dispersion_additional);draw_info("Dispersion add: ",""+Math.round(w.total_dispersion_additional));
				color_it (w.total_ammo_size,w.base_ammo_size);draw_info("Ammo size: ",""+Math.round(w.total_ammo_size));
				color_it (w.total_reload_time,w.base_reload_time);draw_info("Reload time: ",""+Math.round(w.total_reload_time));
				
				
				
				((Weapon)obj).model.setPosition(info_x,info_y-300);
				((Weapon)obj).model.draw(Main.batch_static);
				//model.
				

			}
			
				
			if (obj instanceof Energoshield)
			{

				
				Energoshield e=((Energoshield)obj);
				draw_info(""+((Energoshield)obj).name,"");
				
				mov+=15;
				
				//draw_info("Bonuses: ",""+((Energoshield)obj).attr_count);
				
				
				color_it (e.total_value,e.base_value); draw_info("Value: ",""+((Energoshield)obj).total_value);
				color_it (e.total_regen_speed,e.base_regen_speed); draw_info("Regen speed: ",""+((Energoshield)obj).total_regen_speed);
				color_it (e.total_reflect,e.base_reflect); draw_info("Reflect chance: ",""+((Energoshield)obj).total_reflect);
				
				Main.font.setColor(1.0f, 0.5f, 0.25f, 1);
				
				for (int i=0; i<e.Attribute_list.size(); i++)
				{
					if (!e.Attribute_list.get(i).base){draw_info(""+e.Attribute_list.get(i).name,""+e.Attribute_list.get(i).get_attr_value());}
				}

			}
			
			
			//System.out.println("!!!");
			if (InputHandler.but==0)
			{
				InputHandler.but=-1;
				if (inventory_id>=0)
				{
					
					
					if (GScreen.pl.inventory[inventory_id] instanceof Weapon)
					{
						Object swap=(Weapon)GScreen.pl.inventory[inventory_id];
						GScreen.pl.inventory[inventory_id]=(Weapon)GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
					}
					else
					if (GScreen.pl.inventory[inventory_id] instanceof Energoshield)
					{
							Object swap=GScreen.pl.armored_shield;
							GScreen.pl.armored_shield=(Energoshield)GScreen.pl.inventory[inventory_id];
							GScreen.pl.inventory[inventory_id]=swap;
					}
					else
					{
						System.out.println("!!!");
						Object swap=GScreen.pl.inventory[inventory_id];
						GScreen.pl.inventory[inventory_id]=GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
					}
					
					update_object();
					Main.font.setColor(1.0f, 1.0f, 1.0f, 1);
				}
				else
				{
					
					if (inventory_id==-1)
					{
						Object swap=(Weapon)GScreen.pl.armored[0];
						GScreen.pl.armored[0]=(Weapon)GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
					}
					
					if (inventory_id==-2)
					{
						Object swap=(Weapon)GScreen.pl.armored[1];
						GScreen.pl.armored[1]=(Weapon)GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
					}
					
					update_object();
				}
				
	
				
			}
		}
		
		if (!need_remove)
		{
			if (obj instanceof Energoshield)
			{
				((Energoshield)obj).spr.setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
				((Energoshield)obj).spr.draw(Main.batch_static);
				
				
			}
			
			if (obj instanceof Weapon)
			{
				((Weapon)obj).spr.setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
				((Weapon)obj).spr.draw(Main.batch_static);
				

			}
		}
		
		
		
		
	}
	
	public void draw_info(String _s1, String _s2)
	{
		Main.font.draw(Main.batch_static, _s1, info_x, info_y-mov);
		
		if (!_s2.equals(""))
		{Main.font.draw(Main.batch_static, "- "+_s2+" -", info_x+150, info_y-mov);}
		mov+=20;
	}
	
}
