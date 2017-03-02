package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.Missile;
import com.midfag.entity.missiles.MissileLaser;
import com.midfag.entity.missiles.MissilePing;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class WeaponSimpleLaser extends Weapon {
	
	
	
		public WeaponSimpleLaser()
		{
			base_damage=20;
			base_missile_count=1;
			base_shoot_cooldown=0.50f;
			base_dispersion=10;
			base_dispersion_additional=10;
			base_ammo_size=170;
			base_reload_time=1;
			
			generate();
			update_attributes_bonus();
			
			name="Лазер";
			
			 spr.setTexture(new Texture(Gdx.files.internal("icon_laser.png")));//()=)
		}
		
		@Override
		public Missile get_missile(Entity pl)
		{
			return new MissileLaser(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.spr.getRotation()+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(100)+5000.0f),
					pl.is_AI);
		}
		
		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot_laser;
		}
		
		//public void
}
