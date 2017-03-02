package com.midfag.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;

public class ButtonPutter extends Button {

	public Entity obj;
	
	public ButtonPutter(float _x, float _y, Entity _o)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		obj=_o;
	}
	
	@Override
	public void second_draw()
	{
		obj.spr.setPosition(pos.x,pos.y);
		obj.spr.draw(Main.batch_static);
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
			
			GScreen.id=obj.id;
			GScreen.edit_spr.setTexture(obj.spr.getTexture());
			GScreen.edit_spr.setSize(obj.spr.getWidth(), obj.spr.getHeight());
			GScreen.edit_spr.setAlpha(0.55f);
			
		}
	}
}
