package com.midfag.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;

public class ButtonLoadMap extends Button {

	
	public String[] ss=new String[100];
	
	public ButtonLoadMap(float _x, float _y)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		
	}
	
	@Override
	public void second_draw()
	{
		
	}
	
	@Override
	public void some_update(float _d)
	{
		if (!GScreen.show_edit)
		{
			need_remove=true;
			//GScreen.Button_list.remove(this);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			FileHandle file = Gdx.files.local("z.txt");
			
			String s=file.readString();
			ss=s.split("\n");
			
			    // if file doesnt exists, then create it
			//System.out.println(ss);
			//System.out.println(ss[0]);
			Entity e=null;
			for (int i=0; i<ss.length; i++)
			{
				if (ss[i].equals("###ENTITY"))
				{
					i++;
					
					String id=ss[i];
					
					e=GScreen.get_object_from_id(id);
					
					
				}
				
				if (ss[i].equals("pos.x"))
				{
					i++;
					e.pos.x=Integer.parseInt(ss[i]);
				}
				
				if (ss[i].equals("pos.y"))
				{
					i++;
					e.pos.y=Integer.parseInt(ss[i]);
				}
				
				if (ss[i].equals("PUT"))
				{
					
					GScreen.add_entity_to_map(e);
				}
			}
			
			InputHandler.but=-1;
		}
	}
}
