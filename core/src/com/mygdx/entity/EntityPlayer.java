package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.equip.energoshield.Energoshield;
import com.mygdx.equip.energoshield.EnergoshieldSimple;
import com.mygdx.equip.weapon.WeaponLegendaryBlender;
import com.mygdx.equip.weapon.WeaponLegendaryChaos;
import com.mygdx.equip.weapon.WeaponLegendaryPing;
import com.mygdx.equip.weapon.WeaponSimpleFirle;
import com.mygdx.equip.weapon.WeaponSimpleLaser;
import com.mygdx.equip.weapon.WeaponSimpleMinigun;
import com.mygdx.equip.weapon.WeaponSimpleShotgun;
import com.mygdx.game.Assets;
import com.mygdx.game.GScreen;
import com.mygdx.game.InputHandler;
import com.mygdx.game.skills.Skill;
import com.mygdx.game.skills.SkillShield;
import com.mygdx.game.skills.SkillShield_AA_ValueHalfDamage;
import com.mygdx.game.skills.SkillShield_AB_ValueHalfDamageTime;
import com.mygdx.game.skills.SkillShield_A_MoreValue;
import com.mygdx.game.skills.SkillShield_BA_WarmProtect;
import com.mygdx.game.skills.SkillShield_BB_RestoreSpeed;
import com.mygdx.game.skills.SkillShield_B_MoreRegen;
import com.mygdx.game.skills.SkillShield_CA_MoreReflectDouble;
import com.mygdx.game.skills.SkillShield_CB_MoreReflectRegen;
import com.mygdx.game.skills.SkillShield_C_MoreReflect;

public class EntityPlayer extends Entity {

	//public float dead_time; 04.27.12 20.02.2017
	
	public float teleport_cooldown;

	public EntityPlayer(Vector2 _v, boolean _custom) {
		
		super(_v, _custom);
		
		is_AI=false;
		is_player=true;
		
		spr.setTexture(new Texture(Gdx.files.internal("char.png")));
		
		//is_player
		
		speed=500;

		have_ability=true;
		
		//ammo_count=(int) armored_weapon.total_ammo_size;
		
		Skill skl=new SkillShield();
		Skills_list.add(skl);
		
			Skill subskl=new SkillShield_A_MoreValue();
			Skills_list.add(skl.add_subskill(subskl, GScreen.pl));
			
				Skills_list.add(subskl.add_subskill(new SkillShield_AA_ValueHalfDamage(), GScreen.pl));
				Skills_list.add(subskl.add_subskill(new SkillShield_AB_ValueHalfDamageTime(), GScreen.pl));
				//---
				//---
				
			subskl=new SkillShield_B_MoreRegen();
			Skills_list.add(skl.add_subskill(subskl, GScreen.pl));
				Skills_list.add(subskl.add_subskill(new SkillShield_BA_WarmProtect(), GScreen.pl));
				Skills_list.add(subskl.add_subskill(new SkillShield_BB_RestoreSpeed(), GScreen.pl));
		
			subskl=new SkillShield_C_MoreReflect();
			Skills_list.add(skl.add_subskill(subskl, GScreen.pl));
				Skills_list.add(subskl.add_subskill(new SkillShield_CA_MoreReflectDouble(), GScreen.pl));
				Skills_list.add(subskl.add_subskill(new SkillShield_CB_MoreReflectRegen(), GScreen.pl));
		
				armored[0]=new WeaponSimpleMinigun();
				armored[1]=new WeaponSimpleShotgun();
				armored_shield=new EnergoshieldSimple();

				
				
				
		// TODO Auto-generated constructor stub
	}
	
	public void some_update(float _d)
	{
		//if (Gdx.input.isKeyPressed(106))
		//{
		//	System.out.println("Scroll lock is live!");
		//}
		
		if (Gdx.input.isKeyPressed(Keys.G))
		{
			/*
			if (Math.random()>0.33)
			{armored_weapon=new WeaponSimpleFirle();}
			else
			if (Math.random()>0.33)
			{armored_weapon=new WeaponSimpleShotgun();}
			else
			{armored_weapon=new WeaponSimpleMinigun();}*/
			
			Assets.shoot03.stop(miso);
			
	        for (int i=0; i<30; i++)//;
	        {
	        	switch ((int)GScreen.rnd(8))
	        	{
	        		case 0: inventory[i]=new WeaponSimpleFirle();	break;
	        		case 1: inventory[i]=new WeaponSimpleMinigun();	break;
	        		case 2: inventory[i]=new WeaponSimpleShotgun();	break;
	        		case 3: inventory[i]=new EnergoshieldSimple(); System.out.println("Shield in slot "+i);	break;
	        		case 4: inventory[i]=new WeaponLegendaryPing(); break;
	        		case 5: inventory[i]=new WeaponLegendaryChaos(); break;
	        		case 6: inventory[i]=new WeaponLegendaryBlender(); break;
	        		case 7: inventory[i]=new WeaponSimpleLaser(); break;
	        	}
	        	
	        	//if (inventory[i] instanceof Energoshield)
	        	//{
	        		//for (int i=0; i<Skill)
	        	//}
	        	
				for (int j=0; j<GScreen.pl.Skills_list.size();j++)
				{
					if (GScreen.pl.Skills_list.get(j).learned)
					if (inventory[i] instanceof Energoshield)
					{GScreen.pl.Skills_list.get(j).shield_gen_action((Energoshield) inventory[i]);}
				}
	        }
		}
		if ((teleport_cooldown<=0)&(Gdx.input.isKeyPressed(Keys.E)))
		{
			hard_move(GScreen.sinR(360-spr.getRotation())*200,GScreen.cosR(360-spr.getRotation())*200,1);
			
			teleport_cooldown=3;
			
			Assets.flash.play();
		}
		
		if (teleport_cooldown>0){teleport_cooldown-=_d;}
	}
	
	

}
