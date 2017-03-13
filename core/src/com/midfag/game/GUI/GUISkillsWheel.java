package com.midfag.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class GUISkillsWheel extends GUI {
	
	public List<Button> Button_list = new ArrayList<Button>();
	//public GScreen G=Main.screen;

	public GUISkillsWheel()
	{
		//G=GScreen.get_this();
	}
	
	@Override
	public void sub_update(float _d) 
	{
		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		//game.shapeRenderer_static.begin(ShapeType.Filled);
		//game.shapeRenderer_static.end();

        	Assets.skill_wheel.setPosition(-1024, -1024);
        	Assets.skill_wheel.draw(Main.batch_static);
        
		
		if (!GScreen.show_skills_wheel){remove_this();}

	}
}
