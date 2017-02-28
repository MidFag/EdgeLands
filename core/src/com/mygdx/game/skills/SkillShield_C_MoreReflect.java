package com.mygdx.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SkillShield_C_MoreReflect extends Skill {
	public SkillShield_C_MoreReflect()
	{
		super();
		
		pos.x=0;
		pos.y=-67;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_reflect.png")));
		spr.setSize(50, 50);
		
		name="Улучшенное отражение";
		info="Дополнителное увеличение"+"\n"+"отражения в размере 20%";
	}
}
