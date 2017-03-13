package com.midfag.game.GUI.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.GUI;
import com.midfag.game.GUI.GUIEdit;

public class ButtonPutterTile extends Button {


	public static Sprite edit_spr=new Sprite(new Texture(Gdx.files.internal("decor_pilon.png"))); 
	public int tile_id;
	public GUIEdit gui;

	
	public Vector2 off=new Vector2();
	
	public ButtonPutterTile(float _x, float _y, int _tile, GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
	
		edit_spr.setTexture(Assets.tile[_tile]);
		edit_spr.setAlpha(0.95f);
		tile_id=_tile;

		gui=_gui;
		//System.out.println ("ENTITY="+e);
	}
	
	@Override
	public void second_draw()
	{
		edit_spr.setPosition(pos.x-edit_spr.getWidth()/2,pos.y-edit_spr.getHeight()/2);
		edit_spr.draw(
				Main.batch_static
				);
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
			
			//System.out.println ("ENTITY="+gui.e);
			gui.e=null;
			gui.tile=tile_id;
			//gui.e.spr.setAlpha(0.2f);
			
			
		}
	}
}
