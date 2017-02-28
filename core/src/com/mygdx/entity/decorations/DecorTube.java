package com.mygdx.entity.decorations;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Entity;
import com.mygdx.game.Assets;
import com.mygdx.game.GScreen;
import com.mygdx.game.Main;
import com.mygdx.game.Phys;

public class DecorTube extends Entity {

	public DecorTube left;
	public DecorTube right;
	public boolean isCarcas=false;
	public float fall_time=-100;
	
	public float fall_speed=-100;
	public boolean is_fallen=false;
	
	public DecorTube(Vector2 _v,boolean _custom) {
		
		super(_v,_custom);
		
		armored[0]=null;
		armored[1]=null;
		//armored_shield=null;
		
		armored_shield.value=100;
		armored_shield.total_value=100;
		armored_shield.total_regen_speed=0;
		
		is_decor=true;
		is_AI=false;
		is_player=false;
		
		custom_phys=true;
		
		//is_decor=true;
		
		spr.setTexture(Assets.tube);
		spr.setSize(40, 64);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		
		
		
		speed=0;
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void some_update(float _d)
	{
		if (fall_time>0)
		{
			fall_time-=_d;
			
			if (fall_time<=0)
			{
				is_fallen=true;
				fall_speed=10.1f;
			}
		}
		
		if (fall_speed>0)
		{
			fall_speed+=10;
			
			hard_move(0,-fall_speed,_d);
			
			if (fall_speed>100.5f){fall_speed=0;}
		}
		
		//Main.font.draw(Main.batch, ""+fall_speed, pos.x-25, pos.y+30);
	}
	
	public DecorTube get_left()
	{
		if (left!=null)
		{return left.get_left();}
		else
		{return this;}
	}
	
	public DecorTube get_right()
	{
		if (right!=null)
		{
			if (right.isCarcas)
			{	
				System.out.println("return carcas");
				return right;
			}
			else
			{System.out.println("return right");
			return right.get_right();}
		}
		else
		{System.out.println("return this");
		return this;}
	}
	
	public void fall_left()
	{
		spr.setColor(Color.LIGHT_GRAY);
		
		if (!is_fallen)
		fall_time=(float) (0.17f);
		
		if (left!=null)
		{left.fall_left();}
	}
	
	public void fall_right()
	{
		spr.setColor(Color.LIGHT_GRAY);
		
		if (!is_fallen)
		fall_time=(float) (0.17f);
		
		if (right!=null)
		{right.fall_right();}
	}
	
	@Override
	public void pre_death_action(Phys _o, boolean need_dead_anim) {
		// TODO Auto-generated method stub
		
		
		if (!get_left().isCarcas)
		{
			fall_left();
		}
		
		if (!get_right().isCarcas)
		{
			fall_right();
			
		}
		
		if (left!=null)
		left.right=null;
		
		if (right!=null)
		right.left=null;
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		Phys p=new Phys(new Vector2(pos.x+20,pos.y-20),new Vector2(pos.x-20,pos.y-20),true,this,true);
		
		//System.out.println("X "+x+"; Y "+y);
		
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-20,pos.y+32),new Vector2(pos.x+20,pos.y+32),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-20,pos.y-20),new Vector2(pos.x-20,pos.y+32),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+20,pos.y+32),new Vector2(pos.x+20,pos.y-20),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
	}
	
	

}
