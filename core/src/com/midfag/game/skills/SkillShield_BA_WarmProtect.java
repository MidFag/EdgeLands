package com.midfag.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SkillShield_BA_WarmProtect extends Skill {
	public SkillShield_BA_WarmProtect()
	{
		super();
		
		pos.x=-0;
		pos.y=70;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_regen_protect.png")));
		spr.setSize(50, 50);
		
		name="Гарантия";
		info="Каждые 5 секунд"+"\n"+"регенерация щита не сбивается"+"\n"+"Действует в течении 1й секунды";
	}
}
