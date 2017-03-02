package com.midfag.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SkillShield_B_MoreRegen extends Skill {
	public SkillShield_B_MoreRegen()
	{
		super();
		
		pos.x=-50;
		pos.y=50;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_regen.png")));
		spr.setSize(50, 50);
		
		name="Подпитка";
		info="Дополнительно увеличивает"+"\n"+"скорость регенерации щита 25%";
	}
}
