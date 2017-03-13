package com.midfag.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.energoshield.EnergoshieldSimple;
import com.midfag.equip.weapon.WeaponLegendaryBlender;
import com.midfag.equip.weapon.WeaponLegendaryChaos;
import com.midfag.equip.weapon.WeaponLegendaryPing;
import com.midfag.equip.weapon.WeaponSimpleFirle;
import com.midfag.equip.weapon.WeaponSimpleLaser;
import com.midfag.equip.weapon.WeaponSimpleMinigun;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.skills.Skill;
import com.midfag.game.skills.SkillShield;
import com.midfag.game.skills.SkillShield_AA_ValueHalfDamage;
import com.midfag.game.skills.SkillShield_AB_ValueHalfDamageTime;
import com.midfag.game.skills.SkillShield_A_MoreValue;
import com.midfag.game.skills.SkillShield_BA_WarmProtect;
import com.midfag.game.skills.SkillShield_BB_RestoreSpeed;
import com.midfag.game.skills.SkillShield_B_MoreRegen;
import com.midfag.game.skills.SkillShield_CA_MoreReflectDouble;
import com.midfag.game.skills.SkillShield_CB_MoreReflectRegen;
import com.midfag.game.skills.SkillShield_C_MoreReflect;

public class EntityPlayer extends Entity {

	//public float dead_time; 04.27.12 20.02.2017
	
	public float teleport_cooldown;
	
	public Sprite leg=new Sprite(new Texture(Gdx.files.internal("leg.png")));
	public Sprite foot=new Sprite(new Texture(Gdx.files.internal("foot.png")));
	public Sprite foot_shadow=new Sprite(new Texture(Gdx.files.internal("foot_shadow.png")));
	
	public float leg1_anim;
	public float leg2_anim;
	
	public int leg_anim_mode=1;
	public int leg2_anim_mode=1;

	
	public EntityPlayer(Vector2 _v, boolean _custom) {
		
		super(_v, _custom);
		
		id="player";
		
		is_AI=false;
		is_player=true;
		
		spr.setTexture(Assets.mech_down);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		spr.setSize(100, 100);
		spr.setOrigin(50, 5);
		
		//foot.setSize(30, 6);
		foot.setOrigin(15, 1);
		
		
		leg.setOrigin(5, 50);
		foot_shadow.setOrigin(17, 12);
		//is_player
		
		speed=250*1.25f;

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
	
	public void draw_leg_horiz(float _x, float _y, int _dim)
	{
		float up=0;
		if (((leg_anim_mode>=0)&&(_dim==1))||((leg_anim_mode<0)&&(_dim==-1))){up=GScreen.sinR((1-leg1_anim)*90)*5;}
		
		leg.setScale(1, 1);
		leg.setRotation((leg1_anim*60*_dim));
		
		
		
		leg.setPosition(pos.x+5, pos.y+5+_y+1.2f);
		leg.draw(Main.batch);
		
		foot.setPosition(pos.x+leg1_anim*24*_dim+_x-7, pos.y+_y+up+10);
		foot.draw(Main.batch);
		
		foot_shadow.setPosition(pos.x+leg1_anim*24*_dim+_x-7, pos.y+_y+10);
		foot_shadow.draw(Main.batch);
		
		
		
		
		
		leg.setPosition(pos.x+5, pos.y+5+_y-1.2f);
		leg.draw(Main.batch);

	}
	
	public void draw_leg_vert(float _x, float _y, int _dim)
	{
		float up=0;
		if (((leg2_anim_mode>=0)&&(_dim==1))||((leg2_anim_mode<0)&&(_dim==-1))){up=GScreen.sinR((1-leg2_anim)*90)*5;}
		
		foot.setPosition(pos.x+_x-7, pos.y+_y+up+leg2_anim*24*_dim);
		foot.draw(Main.batch);
		
		foot_shadow.setPosition(pos.x+_x-7, pos.y+_y+leg2_anim*24*_dim);
		foot_shadow.draw(Main.batch);
		
		
		
		leg.setRotation(0);
		leg.setPosition(pos.x+5+_x, pos.y+5+_y);
		leg.setScale(1, 1-leg2_anim/2*_dim);
		leg.draw(Main.batch);
	}
	
	
	@Override
	public void some_draw()
	{
		
		//leg.setSize(10, 50*GScreen.sinR(leg1_anim)/10+40f);
		
		if (direction==0)
		{
			draw_leg_vert(-30,0,-1);
			draw_leg_vert(15,0,1);
		}
		
		if (direction==1)
		{
			draw_leg_horiz(0,15,-1);
			draw_leg_horiz(0,-15,1);
		}
		
		if (direction==2)
		{
			draw_leg_vert(-30,0,1);
			draw_leg_vert(15,0,-1);
		}
		
		if (direction==3)
		{
			draw_leg_horiz(0,15,1);
			draw_leg_horiz(0,-15,-1);
		}
		

	}
	
	
	@Override
	public void some_update(float _d)
	{
		//if (impulse.x>0)
		{leg1_anim+=_d*impulse.x/24*leg_anim_mode;}
		
		leg2_anim+=_d*impulse.y/24*leg2_anim_mode;
		
		if (impulse.x>0)
		{	
			if (leg1_anim>1){leg1_anim=1; leg_anim_mode=-1;}
			if (leg1_anim<-1){leg1_anim=-1; leg_anim_mode=1;}
		}
		else
		{	
			if (leg1_anim>1){leg1_anim=1; leg_anim_mode=1;}
			if (leg1_anim<-1){leg1_anim=-1; leg_anim_mode=-1;}
		}
		
		
		
		if (impulse.y>0)
		{	
			if (leg2_anim>1){leg2_anim=1; leg2_anim_mode=-1;}
			if (leg2_anim<-1){leg2_anim=-1; leg2_anim_mode=1;}
		}
		else
		{	
			if (leg2_anim>1){leg2_anim=1; leg2_anim_mode=1;}
			if (leg2_anim<-1){leg2_anim=-1; leg2_anim_mode=-1;}
		}
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
