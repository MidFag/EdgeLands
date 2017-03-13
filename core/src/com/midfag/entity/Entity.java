package com.midfag.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.missiles.MissileExplosion;
import com.midfag.entity.missiles.MissileParticlePiece;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.energoshield.EnergoshieldRobo;
import com.midfag.equip.weapon.Weapon;
import com.midfag.equip.weapon.WeaponRobofirle;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.Phys;
import com.midfag.game.skills.Skill;

public class Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	public Sprite spr=new Sprite(new Texture(Gdx.files.internal("eye.png")));
	
	public Vector2 pos=new Vector2();
	public Vector2 offset=new Vector2();
	
	public List<Phys> Phys_list_local = new ArrayList<Phys>();
	public List<Skill> Skills_list = new ArrayList<Skill>();

	public boolean move_vert;
	
	public int direction;
	
	public float stun=0;
	
	public float phys_timer=(float) Math.random();
	
	public boolean is_AI=true;
	
	public boolean is_player=false;

	public float hurt_sound_cooldown;
	
	public float dead_time;
	
	public boolean burrow=false;
	
	public float speed=255f;
	
	public float slow;
	
	public boolean can_rotate=true;
	
	public Weapon[] armored=new Weapon[2];
	public Energoshield armored_shield=new EnergoshieldRobo();
	
	public Vector2 impulse=new Vector2();
	
	public long miso=0;
	
	public Object[] inventory=new Object[100];
	
	public boolean custom_phys=false;
	public boolean is_decor=false;
	
	public boolean have_ability=false;
	
	public String id="robo";
	
	public int order=0;
	
	public boolean diagonal=false;
	
	public void init()
	{
		
		
			if (!custom_phys)
			{
				
				int x=(int)(pos.x/300);
				int y=(int)(pos.y/300);
				
	
					Phys p=new Phys(new Vector2(pos.x-32f/1,pos.y),new Vector2(pos.x+32f/1,pos.y),false,this,true);
					if (is_player){p.move_block=false;}
					GScreen.cluster[x][y].Phys_list.add(p);
					Phys_list_local.add(p);
					
					
					
					p=new Phys(new Vector2(pos.x,pos.y-32f/1),new Vector2(pos.x,pos.y+32f/1),false,this,true);
					if (is_player){p.move_block=false;}
					GScreen.cluster[x][y].Phys_list.add(p);
					Phys_list_local.add(p);
				
			}
			else
			{
				do_custom_phys();
				//System.out.println("create CUSTOM phys ");
			}
		
	}
	
	public Entity(Vector2 _v,boolean _custom)
	{
		pos=_v;
		
		custom_phys=_custom;
		
		armored[0]=new WeaponRobofirle();
		armored[1]=null;
		
		if (armored[0]!=null)
		{
			armored[0].cd=(float) (Math.random()*1);
			armored[0].ammo=(int) armored[0].total_ammo_size;
		}
		
		if (armored[1]!=null)
		{
			armored[1].cd=(float) (Math.random()*1);
			armored[1].ammo=(int) armored[1].total_ammo_size;
		}	
	}
	
	public float iso(float _f)
	{
		//if (!diagonal)
		//{return pos.y;}
		
		//if (!diagonal)
		return (pos.y-pos.x);
		
		//return pos.y+pos.x;
		//return pos.y-pos.x;
	}
	
	
	
	public void do_custom_phys() {
		// TODO Auto-generated method stub
		
	}

	public void take_damage(float _damage)
	{
		
	}
	
	public void hit_action(Phys _o,float _damage)
	{
		
		stun+=1;
		
		if (have_ability)
		{
			for (int i=0; i<Skills_list.size(); i++)
			{
				if (Skills_list.get(i).learned)
				{_damage=Skills_list.get(i).damage_action(this,_damage);}
			}
		}
		
		armored_shield.value-=_damage/2+_damage*1.5f*GScreen.rnd(1);
		
		armored_shield.warm-=_damage/armored_shield.total_value*10f;
		
		armored_shield.warm=Math.max(0, armored_shield.warm);
		
		if (hurt_sound_cooldown<=0)
		{
			if (is_AI)
			{Assets.metal_sound.play(0.05f, (float) (Math.random()*0.2f+1.9f), 0);}
			else
			{Assets.plastic.play(0.25f, (float) (Math.random()*0.1f+0.55f), 0);}
			
			hurt_sound_cooldown=0.25f;
		}
		
		if (armored_shield.value<=0)
		{
			pre_death_action(_o,true);
			dead_action(_o,true);
		}
		
		
	}
	
	public void pre_death_action(Phys _o, boolean need_dead_anim) {
		// TODO Auto-generated method stub
		
	}





	public void some_draw()
	{
		
	}
	public void draw()
	{
		//Main.batch.begin();
		
		some_draw();
		if (!burrow)
		{
		
			
				spr.setPosition(pos.x-spr.getOriginX(),pos.y-spr.getOriginY());
				GScreen.Draw_list.add(this);
				//spr.draw(Main.batch);
				//Main.font.draw(Main.batch, "!"+iso(0), pos.x, pos.y+100);
				//Main.font.draw(Main.batch, ""+(int)(armored_shield.value), pos.x, pos.y);
			//Main.batch.end();
			/*
			Main.shapeRenderer.begin(ShapeType.Filled);
				Main.shapeRenderer.setColor(1, 1, 1, 0.2f);
				
				if (armored_weapon!=null)
				{
					Main.shapeRenderer.rectLine(pos.x, pos.y,pos.x+GScreen.sinR(360-spr.getRotation()-add_dispersion/2f-armored_weapon.total_dispersion/2f)*200,pos.y+GScreen.cosR(360-spr.getRotation()-add_dispersion/2f-armored_weapon.total_dispersion/2f)*200,0.2f);
					Main.shapeRenderer.rectLine(pos.x, pos.y,pos.x+GScreen.sinR(360-spr.getRotation()+add_dispersion/2+armored_weapon.total_dispersion/2)*200,pos.y+GScreen.cosR(360-spr.getRotation()+add_dispersion/2f+armored_weapon.total_dispersion/2f)*200,0.2f);
				}
				
				
			Main.shapeRenderer.end();*/
			
			
		}
		//else
		//{Main.batch.end();}
	}
	
	public void dead_action(Phys _o, boolean need_dead_anim)
	{
		if ((need_dead_anim))
		{
			for (int v=0; v<20; v++)
			{
				GScreen.Missile_list.add(new MissileParticlePiece(new Vector2(pos.x,pos.y),(float) (Math.random()*360),(float)Math.pow(GScreen.rnd(10000),0.5)+500.0f,is_AI));
			}
			
			for (int v=0; v<10; v++)
			{
				GScreen.Missile_list.add(new MissileExplosion(new Vector2(pos.x,pos.y),(float) (Math.random()*360),(float) (100f+Math.random()*400f),is_AI));
			}
			
			need_dead_anim=false;
		}
		
		//if (!is_player)
		//{
			for (int i=0; i<Phys_list_local.size(); i++)
			{
				Phys_list_local.get(i).clear_path();
				GScreen.Phys_list.remove(Phys_list_local.get(i));
				
				GScreen.cluster[(int)(pos.x/300f)][(int)(pos.y/300f)].Phys_list.remove(Phys_list_local.get(i));
			}
			
			GScreen.cluster[(int)(pos.x/300)][(int)(pos.y/300)].Entity_list.remove(this);
			
			
				GScreen.Entity_list.remove(this);
				GScreen.cluster[(int)(pos.x/300f)][(int)(pos.y/300f)].Entity_list.remove(this);
			
		//}
		
		//m
		Assets.metal_destroy.play(0.25f, (float) (Math.random()*0.1f+0.95f), 0);

	}
	
	public void hard_move(float _x, float _y, float _d)
	{
		do_move(_x,_y,_d,false);
	}
	
	public void move (float _x, float _y, float _d)
	{
		do_move(_x,_y,_d,true);
	}
	
	public void do_move (float _x, float _y, float _d,boolean _need)
	{
		
		int cx=(int)(pos.x/300f);
		int cy=(int)(pos.y/300f);
		
		if (stun<=0)
		{
			if (_need)
			{
				_x*=(1-slow)*_d;
				_y*=(1-slow)*_d;

			}
			else
			{
				_x*=_d;
				_y*=_d;

			}
			
				pos.x+=_x;
				pos.y+=_y;
				
				//spr.setColor((float)Math.random()*0.2f+0.8f,(float)Math.random()*0.2f+0.8f, (float)Math.random()*0.2f+0.8f, 1.0f);
				
				int ncx=(int)(pos.x/300f);
				int ncy=(int)(pos.y/300f);
				
				if ((cx!=ncx)||(cy!=ncy))
				{
					if (!is_player)
						{GScreen.cluster[cx][cy].Entity_list.remove(this);
						GScreen.cluster[ncx][ncy].Entity_list.add(this);}
					
					for (int i=0; i<Phys_list_local.size(); i++)
					{
						GScreen.cluster[ncx][ncy].Phys_list.add(Phys_list_local.get(i));
						
						GScreen.cluster[cx][cy].Phys_list.remove(Phys_list_local.get(i));
					}
					
					
				}
				
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
	
	public void shoot(float _d, int _i)
	{
		
		if (armored[_i].reload_timer<=0)
		{
			if ((armored[_i].need_warm>0)&&(armored[_i].warm==0)&&(armored[_i].reload_timer<=0))
			{
				if (miso==0)
				 {
					miso = Assets.minigun.play(0.5f,0.1f,0);
					Assets.minigun.setLooping(miso, true);
				 }
			}
		
			armored[_i].warm+=_d;
			armored[_i].warm=Math.min(armored[_i].warm, armored[_i].need_warm);
		}
		else
		{
			armored[_i].warm-=_d;
			if (armored[_i].warm<0){armored[_i].warm=0;}
		}
		
		
		if (
				(armored[_i]!=null)&&
				(armored[_i].cd<=0)&&
				(pos.dst(GScreen.pl.pos)<600)&&
				(armored[_i].reload_timer<=0)&&
				(
						!(
								(armored[_i].need_warm!=0)
								&&
								(armored[_i].warm/armored[_i].need_warm<0.2f))
						)
						
				)
		{
			//System.out.println("try shoot");
			//assert armored_weapon!=null;
			if (armored[_i].need_warm<=0)
			{armored[_i].cd=armored[_i].total_shoot_cooldown;}
			else
			{
				{armored[_i].cd=armored[_i].total_shoot_cooldown/(armored[_i].warm/armored[_i].need_warm*1.0f);}
			}
			
			
			
			//System.out.println("TotMisCou: "+armored_weapon.total_missile_count);
			for (int zz=0; zz<armored[_i].total_missile_count; zz++)
			{
				GScreen.Missile_list.add
										(
											armored[_i].get_missile(this)
										);
				
				GScreen.Missile_list.get(GScreen.Missile_list.size()-1).damage=armored[_i].total_damage;
			}
			
			armored[_i].add_disp+=armored[_i].total_dispersion_additional;
			
			if ((pos.dst(GScreen.pl.pos)<300)&&(armored[_i]!=null))
			{armored[_i].get_shoot_sound().play((1f-pos.dst(GScreen.pl.pos)/300.0f)*0.15f);}
			
			armored[_i].ammo--;
			if (armored[_i].ammo<=0)
			{armored[_i].reload_timer=armored[_i].total_reload_time;}
			/*
			if (is_player)
			{Assets.shoot01.play(0.75f, (float) (0.5f), 0);}
			
			if (!is_player)
			{
				if (pos.dst(GScreen.pl.pos)<300)
				{Assets.shoot00.play((1f-pos.dst(GScreen.pl.pos)/300.0f)*0.1f);}
				//Assets.shoot00.play(0.05f, (float) (Math.random()*0.2f+1.5f), 0);
			}
			*/
			
			
			
			
		}
	}
	public void some_update(float _d)
	{
		
	}
	public void update(float _d)
	{
		some_update(_d);
		
		for (int j=0; j<2; j++)
		{
			for (int i=0; i<=armored_shield.Attribute_list.size()-1; i++)
			{
				armored_shield.Attribute_list.get(i).update(_d, this);
			}
			
			if (armored[j]!=null)
			for (int i=0; i<=armored[j].Attribute_list.size()-1; i++)
			{
				armored[j].Attribute_list.get(i).update(_d, this,armored[j]);
			}
		}
		
		
		
		for (int i=0; i<2; i++)
		{
			if (armored[i]!=null)
			if (miso>0)
			{Assets.minigun.setPitch(miso, armored[i].warm/armored[i].need_warm);}
			


			
			if (armored[i]!=null)
			if (armored[i].reload_timer>0)
			{
				armored[i].reload_timer-=_d;
				
				if (armored[i].reload_timer<=0)
				{
					armored[i].ammo=(int) armored[i].total_ammo_size;
				}
			}
		}
		
		slow=Math.min(0.5f, slow);
		slow*=0.99f;
		
		if (stun>0){stun--;}
		
		for (int i=0; i<2; i++)
		{
			if (armored[i]!=null)
			{
				armored[i].add_disp*=Math.pow(0.30f,_d);
			
				
				armored[i].cd-=_d;
			}
		}
		if (armored_shield.value>0)
		{
			armored_shield.value+=armored_shield.total_regen_speed*armored_shield.warm*_d/1;
			
			armored_shield.warm+=_d/10;
			armored_shield.warm=Math.min(1, armored_shield.warm);
			armored_shield.value=Math.min(armored_shield.total_value, armored_shield.value);
		}
		//удыу
		else
		{
			dead_time+=_d;
		}
		
		hurt_sound_cooldown-=_d;
		
		
		
		move (impulse.x,impulse.y,_d);
		impulse.scl((float) Math.pow(0.02f, _d));
		
		if (is_AI)
		{
			
			if (can_rotate)
	    	{
				float a=pos.x-GScreen.pl.pos.x;
		    	float b=pos.y-GScreen.pl.pos.y;
		    	//float c=(float) Math.sqrt((a*a)+(b*b));
		    	float c=(float) Math.toDegrees(Math.atan2(a, b));
		    	spr.setRotation(180-c);
	    	}
			
	    	Phys po=null;
	    	
	    	boolean go_shoot=true;
	    	
	    	if (armored==null)
	    	{go_shoot=false;}
	    	/*
	    	Main.shapeRenderer.begin(ShapeType.Filled);
	    		Main.shapeRenderer.line(pos.x,pos.y,pos.x+(float)Math.sin(Math.toRadians(360-spr.getRotation()))*pos.dst(GScreen.pl.pos),pos.y+(float)Math.cos(Math.toRadians(360-spr.getRotation()))*pos.dst(GScreen.pl.pos));
	    	Main.shapeRenderer.end();*/ //dw
	    	
	    	
	    	if (GScreen.pl.armored_shield.value>0)
	    	{
	    		float rx=(float)Math.sin(Math.toRadians(360-spr.getRotation()));
	    		float ry=(float)Math.cos(Math.toRadians(360-spr.getRotation()));
	    		if (phys_timer>0){phys_timer-=_d;}
	    		if (phys_timer<=0)
	    		{
	    			phys_timer+=0.5f;

			    		po=GScreen.get_contact(pos.x,pos.y,GScreen.pl.pos.x,GScreen.pl.pos.y,rx,ry,pos.dst(GScreen.pl.pos),true,false,false);
			    		
			    		if ((po!=null)&&(po.parent==null))
			    		{
			    			go_shoot=false;
			    			
			    		}
			    	
	    		}
		    	
		    	//if (((po==null)||((po!=null)&&(po.parent!=null))))
		    	if (go_shoot)
		    	{
		    		if (armored[0]!=null)
		    		{shoot(_d,0);}
		    		
		    		if (armored[1]!=null)
		    		{shoot(_d,1);}
		    	}
	    	}
	    	
		}
		
		if (is_player)
		{
			if ((InputHandler.MB)&&(armored_shield.value>0)&&(GScreen.main_control))
			{
	    		if (armored[0]!=null)
	    		{shoot(_d,0);}
	    		
	    		if (armored[1]!=null)
	    		{shoot(_d,1);}
			}
			else
			{
				for (int i=0; i<2; i++)
				{
					if (armored[i]!=null)
					{armored[i].warm-=_d;
					if (armored[i].warm<0){armored[i].warm=0;}}
				}
			}
		}
	}

	public void add_impulse(float _x, float _y, float _d) {
		// TODO Auto-generated method stub
		impulse.x+=_x*_d;//\
		impulse.y+=_y*_d;
	}

	public Entity put() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
