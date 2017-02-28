package com.mygdx.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SkillShield_CB_MoreReflectRegen extends Skill {
	public SkillShield_CB_MoreReflectRegen()
	{
		super();
		
		pos.x=-35;
		pos.y=-67;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_reflect_regen.png")));
		spr.setSize(50, 50);
		
		name="Возмещение";
		info="10% от отраженного урона"+"\n"+"восстанавливает энергощит";
		
		
	}
}
