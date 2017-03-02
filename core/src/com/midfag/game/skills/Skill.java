package com.midfag.game.skills;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.Main;
import com.midfag.game.Phys;

@SuppressWarnings("unused")
public class Skill {
	public Sprite spr=new Sprite(new Texture(Gdx.files.internal("button.png")));
	public String name="some skill";
	
	public int level=0;
	
	public boolean learned=false;
	public boolean blocked=false;
	
	public Vector2 pos=new Vector2();
	public String info;
	
	public Skill skill_a;
	
	public boolean parent_ready=false;
	
	//public List<Skill> Sub_skill = new ArrayList<Skill>();
	
	public Skill parent;
	
	public boolean child_learned=false;
	
	public Skill()
	{
		
	}
	
	public Skill add_sub_skillf(Skill _s)
	{
		skill_a=_s;
		return skill_a;
	}
	
	public void draw_sub_skill()
	{
		spr.setPosition(pos.x-20, pos.y-20);
		spr.draw(Main.batch_static);
		
		
	}
	
	public Skill add_subskill(Skill _s, Entity _e)
	{
		_s.parent=this;
		_s.pos.add(pos);
		return _s;
	}
	
	public void shield_gen_action(Energoshield _e)
	{
		
	}
	
	public void learn_action()
	{
		
	}
	
	public float damage_action(Object _o, float _damage)
	{
		return _damage;
	}
}
