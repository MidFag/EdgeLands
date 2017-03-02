package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.Assets;

public class WeaponSimpleFirle extends Weapon {
	
	
	
		public WeaponSimpleFirle()
		{
			base_damage=20;
			base_missile_count=1;
			base_shoot_cooldown=0.20f;
			base_dispersion=10;
			base_dispersion_additional=10;
			base_ammo_size=17;
			base_reload_time=3;
			
			generate();
			update_attributes_bonus();
			
			name="Винтовка";
			
			 spr.setTexture(new Texture(Gdx.files.internal("icon_firle.png")));//()=)
		}
		

		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot01;
		}
		
		//public void
}
