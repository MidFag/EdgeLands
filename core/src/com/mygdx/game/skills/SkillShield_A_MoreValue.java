package com.mygdx.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.equip.energoshield.Energoshield;
import com.mygdx.game.GScreen;

public class SkillShield_A_MoreValue extends Skill {
	public SkillShield_A_MoreValue()
	{
		super();
		
		pos.x=50;
		pos.y=50;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_additional_value.png")));
		spr.setSize(50, 50);
		
		name="Емкий акумулятор";
		info="Дополнительно увеличивает"+"\n"+"ёмкость ёмкость щита 25%";
	}
	
	@Override
	public void shield_gen_action(Energoshield _e)
	{
		_e.total_value+=_e.base_value*0.25f;
	}
	
	@Override
	public void learn_action()
	{
		
		System.out.println("!@#$%^&");
		GScreen.pl.armored_shield.update_attributes_bonus(GScreen.pl);
		
		for (int i=0; i<GScreen.pl.inventory.length; i++)
		{
			if (GScreen.pl.inventory[i] instanceof Energoshield)
			{
				((Energoshield)GScreen.pl.inventory[i]).update_attributes_bonus();
			}
		}
	}
}
