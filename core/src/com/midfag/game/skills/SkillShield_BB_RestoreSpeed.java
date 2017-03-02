package com.midfag.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SkillShield_BB_RestoreSpeed extends Skill {
	public SkillShield_BB_RestoreSpeed()
	{
		super();
		
		pos.x=-70;
		pos.y=0;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_regen_restore_speed.png")));
		spr.setSize(50, 50);
		
		name="Нетерпение";
		info="Увеличивает скорость"+"\n"+"возобновления регенерации на 30%";
	}
}
