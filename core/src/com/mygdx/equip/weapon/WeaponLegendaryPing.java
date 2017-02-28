package com.mygdx.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.entity.missiles.Missile;
import com.mygdx.entity.missiles.MissilePing;
import com.mygdx.game.Assets;
import com.mygdx.game.GScreen;

public class WeaponLegendaryPing extends Weapon {
	
	
	
		public WeaponLegendaryPing()
		{
			base_damage=10;
			base_missile_count=1;
			base_shoot_cooldown=0.03f;
			base_dispersion=8;
			base_dispersion_additional=3;
			base_ammo_size=120;
			base_reload_time=10;
			
			need_warm=7;
			
			generate();
			update_attributes_bonus();
			
			name="-Ping 454.739.223.565";
			
			spr.setTexture(new Texture(Gdx.files.internal("icon_legendary_ping_minigun.png")));
		}
		

		@Override
		public Missile get_missile(Entity pl)
		{
			return new MissilePing(
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