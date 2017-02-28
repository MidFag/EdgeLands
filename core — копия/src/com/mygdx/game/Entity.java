package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	
	public Vector2 pos=new Vector2();
	
	public List<Phys> Phys_list_local = new ArrayList<Phys>();
	
	public float shield=200;
	public float max_shield=200;
	
	public float shield_regen=50;
	public float shield_regen_rate=1;
	
	public float stun=0;
	
	public boolean is_AI=true;
	public boolean is_player=false;
	
	private float trigger_reload_time;
	
	public float add_dispersion;
	
	public float trigger_cooldown_value=0.5f;
	
	public float hurt_sound_cooldown;
	
	public int missile_count=1;
	
	public int base_disp=5;
	
	public Entity(Vector2 _v)
	{
		pos=_v;
		
		trigger_reload_time=(float) (Math.random()*1);
		
		GScreen.Phys_list.add(new Phys(new Vector2(pos.x-25,pos.y+1),new Vector2(pos.x+25,pos.y-1),false,this));
		Phys_list_local.add(GScreen.Phys_list.get(GScreen.Phys_list.size()-1));
		
		//System.out.println("YES "+Phys_list_local.get(0).parent);
		
		GScreen.Phys_list.add(new Phys(new Vector2(pos.x-1,pos.y+25),new Vector2(pos.x+1,pos.y-25),false,this));
		Phys_list_local.add(GScreen.Phys_list.get(GScreen.Phys_list.size()-1));
	}
	
	public void hit_action(Phys _o)
	{
		
		stun+=3;
		
		shield-=20;
		
		shield_regen_rate-=20/max_shield*10f;
		
		shield_regen_rate=Math.max(0, shield_regen_rate);
		
		if (hurt_sound_cooldown<=0)
		{
			if (is_AI)
			{Assets.metal_sound.play(0.15f, (float) (Math.random()*0.2f+1.9f), 0);}
			else
			{Assets.plastic.play(0.5f, (float) (Math.random()*0.1f+0.95f), 0);}
			
			hurt_sound_cooldown=0.25f;
		}
		
		if (shield<=0)
		{
			for (int v=0; v<20; v++)
			{
				GScreen.Missile_list.add(new MissileParticlePiece(new Vector2(pos.x,pos.y),(float) (Math.random()*360),(float)Math.pow(GScreen.rnd(100),0.5)+5.0f,is_AI));
			}
			
			for (int v=0; v<10; v++)
			{
				GScreen.Missile_list.add(new MissileExplosion(new Vector2(pos.x,pos.y),(float) (Math.random()*360),2f,is_AI));
			}
			
			GScreen.Phys_list.remove(((Entity) _o.parent).Phys_list_local.get(0));
			GScreen.Phys_list.remove(((Entity) _o.parent).Phys_list_local.get(1));
		
			GScreen.Entity_list.remove(_o.parent);
			
			//m
			Assets.metal_destroy.play(0.5f, (float) (Math.random()*0.1f+0.95f), 0);
			
			try {
				this.finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void draw()
	{
		Main.batch.begin();
			spr.setPosition(pos.x-20,pos.y-20);
			spr.draw(Main.batch);
		Main.batch.end();
		
		Main.shapeRenderer.begin(ShapeType.Filled);
			Main.shapeRenderer.setColor(1, 1, 1, 0.2f);
			Main.shapeRenderer.rectLine(pos.x, pos.y,pos.x+GScreen.sinR(360-spr.getRotation()-add_dispersion-2.5f)*200,pos.y+GScreen.cosR(360-spr.getRotation()-add_dispersion-2.5f)*200,0.2f);
			Main.shapeRenderer.rectLine(pos.x, pos.y,pos.x+GScreen.sinR(360-spr.getRotation()+add_dispersion+2.5f)*200,pos.y+GScreen.cosR(360-spr.getRotation()+add_dispersion+2.5f)*200,0.2f);
		Main.shapeRenderer.end();
	}
	
	public void move (float _x, float _y)
	{
		if (stun<=0)
		{
			pos.x+=_x;
			pos.y+=_y;
			
			for (int z=0; z<Phys_list_local.size(); z++)
			{
				Phys_list_local.get(z).start.x+=_x;
				Phys_list_local.get(z).end.x+=_x;
				
				Phys_list_local.get(z).start.y+=_y;
				Phys_list_local.get(z).end.y+=_y;
				
				Phys_list_local.get(z).normal.x+=_x;
				Phys_list_local.get(z).normal.y+=_y;
				
			}
		}
	}
	
	public void shoot(float _d)
	{
		
		if ((trigger_reload_time<=0))
		{
			trigger_reload_time=trigger_cooldown_value;
			
			
			for (int zz=0; zz<missile_count; zz++)
			{
				GScreen.Missile_list.add(new Missile(new Vector2(pos.x,pos.y),(float) Math.toRadians(360-spr.getRotation()+GScreen.rnd(base_disp+add_dispersion)-base_disp/2-add_dispersion/2.0f),(GScreen.rnd(15)+20.0f)/3f,is_AI));
				add_dispersion+=2;
			}
			
			if (is_player)
			{Assets.shoot02.play(0.5f, (float) (1), 0);}
			
			if (!is_player)
			Assets.shoot00.play(0.05f, (float) (Math.random()*0.2f+1.5f), 0);
		}
	}
	
	public void update(float _d)
	{
		add_dispersion*=0.99f;
		if (stun>0){stun--;}
		trigger_reload_time-=_d;
		
		shield+=shield_regen*shield_regen_rate*_d/1;
		shield_regen_rate+=_d/10.0f;
		
		shield_regen_rate=Math.min(1, shield_regen_rate);
		shield=Math.min(max_shield, shield);
		
		hurt_sound_cooldown-=_d;
		
		
		/*Phys_list_local.get(0).start.x=pos.x-30;
		Phys_list_local.get(0).end.x=pos.x+30;
		
		Phys_list_local.get(0).start.y=pos.y-1;
		Phys_list_local.get(0).end.y=pos.y+1;
		
		Phys_list_local.get(1).start.x=pos.x-1;
		Phys_list_local.get(1).end.x=pos.x+1;
		
		Phys_list_local.get(1).start.y=pos.y-30;
		Phys_list_local.get(1).end.y=pos.y+30;*/
		
		if (is_AI)
		{
			
	    	float a=pos.x-GScreen.pl.pos.x;
	    	float b=pos.y-GScreen.pl.pos.y;
	    	//float c=(float) Math.sqrt((a*a)+(b*b));
	    	float c=(float) Math.toDegrees(Math.atan2(a, b));
	    	spr.setRotation(180-c);
	    	Phys po=null;
	    	
	    	boolean go_shoot=true;
	    	/*
	    	Main.shapeRenderer.begin(ShapeType.Filled);
	    		Main.shapeRenderer.line(pos.x,pos.y,pos.x+(float)Math.sin(Math.toRadians(360-spr.getRotation()))*pos.dst(GScreen.pl.pos),pos.y+(float)Math.cos(Math.toRadians(360-spr.getRotation()))*pos.dst(GScreen.pl.pos));
	    	Main.shapeRenderer.end();*/ //dw
	    	
	    	for (int i=0; i<GScreen.Phys_list.size(); i++)
	    	{
	    		po=GScreen.Phys_list.get(i).is_contact(pos.x,pos.y,GScreen.pl.pos.x,GScreen.pl.pos.y,(float)Math.sin(Math.toRadians(360-spr.getRotation())),(float)Math.cos(Math.toRadians(360-spr.getRotation())),pos.dst(GScreen.pl.pos));
	    		
	    		if ((po!=null)&&(po.parent==null))
	    		{
	    			go_shoot=false;
	    			break;
	    		}
	    	}
	    	
	    	//if (((po==null)||((po!=null)&&(po.parent!=null))))
	    	if (go_shoot)
	    	{shoot(_d);}
	    	
		}
		
		if (is_player)
		{
			if (InputHandler.MB)
			{shoot(_d);}
		}
	}
	
}
