package com.mygdx.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SkillShield_CA_MoreReflectDouble extends Skill {
	public SkillShield_CA_MoreReflectDouble()
	{
		super();
		
		pos.x=35;
		pos.y=-67;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_reflect_double.png")));
		spr.setSize(50, 50);
		
		name="Отмщение";
		info="Отраженные снаряды наносят"+"\n"+"противникам удвоенный урон";
	}
}
