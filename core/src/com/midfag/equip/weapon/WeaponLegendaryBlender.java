package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.Missile;
import com.midfag.entity.missiles.MissilePing;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class WeaponLegendaryBlender extends Weapon {
	
	
	
		public WeaponLegendaryBlender()
		{
			base_damage=10;
			base_missile_count=15;
			base_shoot_cooldown=0.025f;
			base_dispersion=8;
			base_dispersion_additional=3;
			base_ammo_size=120;
			base_reload_time=1;
			
			need_warm=3;
			
			generate();
			update_attributes_bonus();
			
			name="Αλενδεπ";
			
			spr.setTexture(new Texture(Gdx.files.internal("icon_legendary_ping_minigun.png")));
		}
		

		@Override
		public Missile get_missile(Entity pl)
		{
			return new Missile(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.spr.getRotation()+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(200)+400.0f),
					pl.is_AI);
		}
		
		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot02;
		}
		
		//public void
}
