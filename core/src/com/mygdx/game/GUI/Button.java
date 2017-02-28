package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputHandler;
import com.mygdx.game.Main;

public class Button {
	public Vector2 pos=new Vector2();
	public Sprite spr=new Sprite(new Texture(Gdx.files.internal("button.png")));
	
	public boolean need_remove=false;
	
	public boolean overlaped=false;
	
	public boolean off_bg=false;
	
	public boolean is_active=true;
	
	public Button(float _x, float _y)
	{
		pos.x=_x;
		pos.y=_y;
	}
	
	public void draw()
	{

		if ((!need_remove)&&(!off_bg)&&(is_active))
		{
			spr.setPosition(pos.x-(int)(spr.getWidth()/2),(int)(pos.y-spr.getHeight()/2));
			spr.draw(Main.batch_static);
		}
		after_draw();

	}
	
	public void after_draw()
	{
		
	}
	
	public void second_draw()
	{
		
	}

	public void update(float delta)
	{
		if (is_active)
		{
			some_update(delta);
			
			if (is_overlap())
			{
				if (!overlaped)
				{
					entry();
				}
				overlaped=true;
				
			}
			else
			{
				if (overlaped)
				{
					leave();
				}
				
				overlaped=false;
			}
			//if (is_overlap())
			//{
			//	System.out.println("zzz");
			//}
		}
		
	}
	
	public void some_update(float _d)
	{
		
	}
	
	public void entry()
	{
		System.out.println("Entry!");
	}
	
	public void leave()
	{
		System.out.println("Leave!");
	}
	public boolean is_overlap()
	{	//		0<10						0+40>10
		
		
		if ((pos.x-20<InputHandler.sposx)&&(pos.x+20>InputHandler.sposx)&&(pos.y-20<InputHandler.sposy)&&(pos.y+20>InputHandler.sposy))
		{
			return true;
		}
		
		
		
		return false;
	}

	public void second_update(float _d) {
		// TODO Auto-generated method stub
		
	}
	
	
}
