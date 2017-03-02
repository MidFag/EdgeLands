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

public class ButtonSaveMap extends Button {

	
	
	public ButtonSaveMap(float _x, float _y)
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
			InputHandler.but=-1;
			
			System.out.println("SAVED");
			FileHandle file = Gdx.files.local("z.txt");
			
			String s="";
			    // if file doesnt exists, then create it
			
			for (int i=0; i<30; i++)
			for (int j=0; j<30; j++)
			{
				for (int k=0; k<GScreen.cluster[j][i].Entity_list.size(); k++)
				{	
					s+="###ENTITY"+"\n";
					s+=GScreen.cluster[j][i].Entity_list.get(k).id+"\n";
					
					s+="pos.x"+"\n";
					s+=Math.round(GScreen.cluster[j][i].Entity_list.get(k).pos.x)+"\n";
					
					s+="pos.y"+"\n";
					s+=Math.round(GScreen.cluster[j][i].Entity_list.get(k).pos.y)+"\n";
					
					s+="angle"+"\n";
					s+=Math.round(GScreen.cluster[j][i].Entity_list.get(k).spr.getRotation())+"\n";
					
					s+="PUT"+"\n";
					
					s+="\n";
					
					
				}
			}
			
			file.writeString(s, false);
		
		}
		
	}
}
