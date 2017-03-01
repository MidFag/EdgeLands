package com.mygdx.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.entity.missiles.Missile;
import com.mygdx.entity.missiles.MissileChaos;

import com.mygdx.equip.weapon.attr.WeaponAttributeChaos;
import com.mygdx.game.Assets;
import com.mygdx.game.GScreen;

public class WeaponLegendaryChaos extends Weapon {
	
	
	public float chaos_time=0.2f;
	
		public WeaponLegendaryChaos()
		{
			super();
			
			base_damage=10;
			base_missile_count=2;
			base_shoot_cooldown=0.3f;
			base_dispersion=15;
			base_dispersion_additional=15;
			base_ammo_size=20;
			base_reload_time=3;
			
			need_warm=0;
			
			name="c h a o s";
			
			//gennable=false;
			
			spr.setTexture(new Texture(Gdx.files.internal("icon_legendary_chaos_shotgun.png")));
			spr.setSize(75, 40);
			
			model.setTexture(new Texture(Gdx.files.internal("model_shotgun_chaos.png")));
			model.setSize(200, 90);
			
			Attribute_list.add(new WeaponAttributeChaos());
			
			generate();
			update_attributes_bonus();
		}
		

		@Override
		public Missile get_missile(Entity pl)
		{
			return new MissileChaos(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.spr.getRotation()+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(200)+400.0f),
					pl.is_AI);
		}
		
		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot75523235;
		}
		
		@Override
		public String get_name()
		{

			
			if (chaos_time>0)
			{
				chaos_time-=0.017f;
				
				if (chaos_time<=0)
				{
					chaos_time+=0.1f;
					

					String s="";
					//char ch;
					for (int i=0; i<10; i++)
					{
						
						s+=(char)((int)(Math.random()*256));
					}
					
					name=s;
					
					if (Math.random()<0.015d)
					{
						chaos_time=0.37f;
						name="-Õ À Î Ñ-";
						return name;
					}
					//return name;
					
				}
			}
			

			
			{return name;}
		}

		
		//public void
}
