package com.mygdx.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Assets;

public class WeaponSimpleMinigun extends Weapon {
	
	
	
		public WeaponSimpleMinigun()
		{
			base_damage=8;
			base_missile_count=1;
			base_shoot_cooldown=0.05f;
			base_dispersion=10;
			base_dispersion_additional=4;
			base_ammo_size=100;
			base_reload_time=7;
			
			need_warm=3;
			
			generate();
			update_attributes_bonus();
			
			name="����������� �������";
			spr.setTexture(new Texture(Gdx.files.internal("icon_minigun.png")));
		}
		

		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot03;
		}
		
		//public void
}