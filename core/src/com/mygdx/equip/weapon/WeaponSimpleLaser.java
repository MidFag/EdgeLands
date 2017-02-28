package com.mygdx.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.entity.missiles.Missile;
import com.mygdx.entity.missiles.MissileLaser;
import com.mygdx.entity.missiles.MissilePing;
import com.mygdx.game.Assets;
import com.mygdx.game.GScreen;

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
