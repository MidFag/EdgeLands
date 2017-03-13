package com.midfag.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.decorations.DecorBuilding;
import com.midfag.entity.decorations.DecorBuildingWall;
import com.midfag.entity.decorations.DecorStoneWall;
import com.midfag.entity.decorations.DecorStonePilon;
import com.midfag.entity.decorations.DecorStoneWall2;
import com.midfag.entity.decorations.DecorSteelWall;
import com.midfag.entity.enemies.EntityVizjun;
import com.midfag.game.Assets;
import com.midfag.game.Cluster;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class GUIEdit extends GUI {
	
	public List<Button> Button_list = new ArrayList<Button>();
	//public GScreen G=Main.screen;
	public Entity e=new Entity(new Vector2(),true);
	public static List<Entity> Object_list = new ArrayList<Entity>();
	
	public String id;
	public int tile;
	
	public static Entity selected_object;
	 public static Cluster selected_cluster;

	public GUIEdit()
	{
		e=new Entity(new Vector2(),true);
		//G=GScreen.get_this();
	}
	
	//@Switcher
	public static Entity get_object_from_id(String _id)
    {
		
		if (_id.equals("stone_wall"))
		{return new DecorStoneWall(new Vector2(),true);}
		
		if (_id.equals("stone_pilon"))
		{return new DecorStonePilon(new Vector2(),true);}
		
		if (_id.equals("stone_wall2"))
		{return new DecorStoneWall2(new Vector2(),false);}
		
		if (_id.equals("building"))
		{return new DecorBuilding(new Vector2(),true);}
		
		if (_id.equals("building_wall"))
		{return new DecorBuildingWall(new Vector2(),true);}
		
		if (_id.equals("robo"))
		{return new Entity(new Vector2(),false);}
		
		
		
    	return null;
    }
	
	@Override
	public void sub_update(float _d) 
	{
		
		if(!GScreen.show_edit){GScreen.GUI_list.remove(this);}
		
		int mod=3;
		
		if (Gdx.input.isKeyPressed(112)){selected_object.dead_action(null, true);}
		
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){mod=1;}
		
			if (InputHandler.key==Keys.COMMA){e.spr.rotate(-15);InputHandler.key=-1;}	
			else
			if (InputHandler.key==Keys.PERIOD){e.spr.rotate(15);InputHandler.key=-1;}	
			
		float xx=(int)(InputHandler.posx/mod)*mod;
		float yy=(int)(InputHandler.posy/mod)*mod;
		
		if ((InputHandler.realy>70)&&(InputHandler.realy<700-70))
		{
			if (
					(InputHandler.but==0)
					&&
					(e!=null)
					&&
					(!e.id.equals(""))
				)
			{
			
				InputHandler.but=-1;
				
				Entity en=get_object_from_id(e.id);
				if (en!=null)
				{
					en.pos.x=xx;
					en.pos.y=yy;
					
					en.spr.setRotation(e.spr.getRotation());
					en.init();
					GScreen.add_entity_to_map(en);
				}
			}
			
			if (
					(InputHandler.but==0)
					&&
					(tile>=0)
				)
				
				
				{
					//System.out.println("PUT TILE!");
					GScreen.tile_map_overlay[(int)(InputHandler.posx/30)][(int)(InputHandler.posy/30)]=tile;
				}
			
			if ((InputHandler.but==1))
			{
				if (selected_object!=null){selected_object.spr.setColor(Color.WHITE);}
				
				e=null;
				
				int cx=(int)(xx/300f);
				int cy=(int)(yy/300f);
				
				float near_dist=9999;
				
				GScreen.temp_vectorA.x=xx;
				GScreen.temp_vectorA.y=yy;
				
				selected_object=null;
				selected_cluster=null;
				
				for (int i=cx-1; i<=cx+1; i++)
				for (int j=cy-1; j<=cy+1; j++)
				if ((i>=0)&&(j>=0))
				{
					for (int k=0; k<GScreen.cluster[j][i].Entity_list.size(); k++)
					{
						if (GScreen.temp_vectorA.dst(GScreen.cluster[j][i].Entity_list.get(k).pos)<near_dist)
						{
							near_dist=GScreen.temp_vectorA.dst(GScreen.cluster[j][i].Entity_list.get(k).pos);
							
							selected_object=GScreen.cluster[j][i].Entity_list.get(k);
							selected_cluster=GScreen.cluster[j][i];
						}
					}
				}
				
				if (selected_object!=null){selected_object.spr.setColor(Color.GREEN);}
			}
		}
		
		if ((InputHandler.key==Keys.X)&&(selected_object!=null))
		{
			InputHandler.key=-1;
			
			selected_object.order++;
			if (selected_object.order>2){selected_object.order=0;}
			/*selected_cluster.Entity_list.remove(selected_object);
			selected_cluster.Entity_list.add(selected_object);*/
		}
		
		if ((InputHandler.key==Keys.C)&&(selected_object!=null))
		{
			selected_object.hard_move(xx-selected_object.pos.x, yy-selected_object.pos.y, 1);
			
		}
		
		if (e!=null)
		{Main.batch.begin();
			e.spr.setPosition(xx-e.spr.getOriginX(), yy-e.spr.getOriginY());
			//Main.batch.draw(edit_spr.getTexture(), edit_spr.getVertices(), 10, 20);
			e.spr.draw(Main.batch);
		Main.batch.end();}
		
	}
}
