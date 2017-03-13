package com.midfag.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class GUIInventory extends GUI {
	
	public List<Button> Button_list = new ArrayList<Button>();
	//public GScreen G=Main.screen;

	public GUIInventory()
	{
		//G=GScreen.get_this();
	}
	
	@Override
	public void sub_update(float _d) 
	{
		/*Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);*/
        
        /*
		Main.shapeRenderer_static.begin(ShapeType.Filled);
			Main.shapeRenderer_static.setColor(0.5f, 0.6f, 0.7f, 0.55f);
			Main.shapeRenderer_static.rect(77, 77, 1000-154,700-154);
		Main.shapeRenderer_static.end();*/
		
		if (!GScreen.show_equip){remove_this();}

	}
}
