package com.mygdx.game.GUI;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.GScreen;
import com.mygdx.game.InputHandler;
import com.mygdx.game.Main;

import com.mygdx.game.skills.Skill;

public class ButtonSkill extends Button {

	private static final int info_x = 0;
	private static final int info_y = -120;
	public Skill skill;
	private int mov;
	
	
	
	GlyphLayout layout = new GlyphLayout();
	//List<skill> skill_list = new ArrayList<Phys>();
	public ButtonSkill(float _x, float _y, Skill _s)
	{
		super (_x, _y);
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_bg.png")));
		spr.setSize(50, 50);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		skill=_s;
	}
	public void draw_info(String _s1)
	{
	draw_info(_s1, "");
	}
	
	public void draw_info(String _s1, String _s2)
	{
		layout.setText(Main.font,_s1);
		Main.font.draw(Main.batch_static, _s1, info_x+pos.x-layout.width/2, info_y-mov+(int)pos.y+45);
		Main.font.draw(Main.batch_static, _s2, info_x+pos.x+100, info_y-mov+(int)pos.y);
		mov+=25;
	}
	
	@Override
	public void some_update(float _d)
	{
		if (!GScreen.show_skills_wheel)
		{
			need_remove=true;
		}
	}
	
	@Override
	public void second_update(float _d)
	{
		if (skill.parent!=null)
		{
			is_active=skill.parent.parent_ready;
			
			//skill.parent_ready=is_active;
		}
		
		if ((InputHandler.but==0)&&(is_overlap())&&(is_active))
		{

			
			if ((InputHandler.subskill_pick)&&((skill.parent==null)||((skill.parent!=null)&&(!skill.parent.child_learned)&&(skill.parent.learned))))
			{
				if (skill.parent!=null)
				{
					
					skill.parent.child_learned=true;
				}
				skill.learned=true;
				
				skill.learn_action();
				
				spr.setColor(Color.GREEN);
				skill.spr.setColor(Color.GREEN);
				
			}
			
			InputHandler.but=-1;
			InputHandler.subskill_pick=true;
		}
		
		if ((InputHandler.but==0)&&(!is_overlap())&&(is_active))
		{
			//InputHandler.subskill_pick=false;
			//skill.parent_ready=false;
		}
		
		if (InputHandler.subskill_pick)
		{
			if (skill.parent!=null)
			{
				is_active=skill.parent.parent_ready;
				
				skill.parent_ready=is_active;
			}
			else
			{is_active=skill.parent_ready;}
		}
	}
	
	@Override
	public void entry()
	{
		skill.parent_ready=true;
	}
	
	@Override
	public void leave()
	{
		if (!InputHandler.subskill_pick)
		{skill.parent_ready=false;}
		
		//InputHandler.subskill_pick=false;
	}
	
	@Override
	public void after_draw()
	{
		if (is_active)
		{skill.spr.setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
		skill.spr.draw(Main.batch_static);}
	}
	
	@Override
	public void second_draw()
	{

		
		if ((is_overlap())&&(is_active))
		{
			mov=0;
			
			
			
			Main.batch_static.end();
			
		 	Gdx.gl.glEnable(GL20.GL_BLEND);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			Main.shapeRenderer_static.begin(ShapeType.Filled);
				Main.shapeRenderer_static.setColor(0.10f,0.10f,0.10f,0.95f);
				Main.shapeRenderer_static.rect(pos.x-300+info_x, pos.y-180+info_y,600,250);
			Main.shapeRenderer_static.end();
			
			Main.batch_static.begin();
			
			Main.font.setColor(Color.GOLDENROD);
			draw_info(skill.name);
			mov+=20;
			
			Main.font.setColor(Color.SLATE);
			draw_info(skill.info);
			mov+=100;
			
			Main.font.setColor(0.1f,0.4f,0.2f,0.9f);
			if (!InputHandler.subskill_pick)
			{
				if (skill.parent==null)
				{draw_info("Нажмите на умение, что бы узнать о путях его развития.");}
				
				
			}
			else
			{
				if ((skill.parent!=null)&&(!skill.parent.learned))
				{
					Main.font.setColor(Color.RED);
					draw_info("Невозможно изучить умение, так как <"+skill.parent.name+"> не изучено");;
				}
				else
				if ((skill.parent!=null)&&(skill.parent.learned)&&(skill.parent.child_learned)&&(!skill.learned))
				{
					Main.font.setColor(Color.RED);
					draw_info("Невозможно изучить умение, так как выучено другое усиливающее умение");;
				}
				else
				{
					if (!skill.learned){draw_info("Нажмите на умение, что бы изучить его.");}}
			}
			Main.font.setColor(Color.WHITE);
		}
		
		/*
		for (int i=0; i<skill.Sub_skill.size(); i++)
		{
			skill.Sub_skill.get(i).spr.setPosition(pos.x-skill.Sub_skill.get(i).spr.getWidth()/2+skill.Sub_skill.get(i).pos.x,pos.y-skill.Sub_skill.get(i).spr.getHeight()/2+skill.Sub_skill.get(i).pos.y);
			skill.Sub_skill.get(i).spr.draw(Main.batch_static);
		}*/
	}
}
