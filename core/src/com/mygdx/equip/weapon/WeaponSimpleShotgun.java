package com.mygdx.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.entity.missiles.Missile;
import com.mygdx.entity.missiles.MissileLaser;
import com.mygdx.entity.missiles.MissileSimple;
import com.mygdx.game.Assets;
import com.mygdx.game.GScreen;

public class WeaponSimpleShotgun extends Weapon {
	
	
	
		public WeaponSimpleShotgun()
		{
			base_damage=7;
			base_missile_count=10;
			base_shoot_cooldown=1;
			base_dispersion=20;
			base_dispersion_additional=50;
			base_ammo_size=7;
			base_reload_time=3;
			
			generate();
			update_attributes_bonus();
			
			name="Стандартный дробовик";
			
			spr.setTexture(new Texture(Gdx.files.internal("icon_shotgun.png")));
		}
		

		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot02;
		}
		
		@Override
		public Missile get_missile(Entity pl)
		{
			return new MissileSimple(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.spr.getRotation()+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(100)+500.0f),
					pl.is_AI);
		}
		
		//public void
}
