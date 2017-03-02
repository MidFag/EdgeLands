package com.midfag.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.GScreen;

public class SkillShield extends Skill {
	
	

	public SkillShield()
	{
		super();
		
		pos.x=110;
		pos.y=120;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield.png")));
		spr.setSize(50, 50);
		
		name="Апгрейд щитов";
		info=	"+50 ёмкость "+"\n"+
				"+10 регенерация"+"\n"+
				"+10 отражение";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void shield_gen_action(Energoshield _e)
	{
		_e.total_value+=50;
		_e.total_regen_speed+=10;
		_e.total_reflect+=5;
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
