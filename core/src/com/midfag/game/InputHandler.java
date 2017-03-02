package com.midfag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.InputProcessor;
import com.midfag.game.GUI.Button;
import com.midfag.game.GUI.ButtonEqWeapon;
import com.midfag.game.GUI.ButtonLoadMap;
import com.midfag.game.GUI.ButtonPutter;
import com.midfag.game.GUI.ButtonSaveMap;
import com.midfag.game.GUI.ButtonSkill;
import com.midfag.game.skills.Skill;


public class InputHandler implements InputProcessor {


	public static int initial_x;

	public static int initial_y;
    
    public static int posx;
    public static int posy;
    
    public static int sposx;
    public static int sposy;
    
    public static int realx;
    public static int realy;
    
    public static boolean MB=false;
    
    public static int but;
    
    public static int key;
    
    public static float MB_timer;
    
    public static float prevx;
    public static float prevy;
    
    public static float dx;
    public static float dy;
    
    public static boolean subskill_pick=false;
    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler() {
        // myBird now represents the gameWorld's bird.

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	MB=true;
        but=button;
        
        return true; // Return true to say we handled the touch.
    }

    @Override
    public boolean keyDown(int keycode) {
    	key=keycode;
    	
    	if (key==Keys.Z)
    	{
    		GScreen.show_edit=!GScreen.show_edit;
    		
    		GScreen.Button_list.add(new ButtonPutter(50,50,GScreen.Object_list.get(0)));
    		
    		GScreen.Button_list.add(new ButtonSaveMap(50,650));
    		GScreen.Button_list.add(new ButtonLoadMap(150,650));
    	}
    	
    	if (key==Keys.O)
    	{
    		
    		GScreen.show_skills_wheel=!GScreen.show_skills_wheel;
    		
    		
    		
    		if (GScreen.show_skills_wheel)
    		{
    			GScreen.show_equip=false;
    			
    			GScreen.skills_camera.zoom=1;

    			GScreen.skills_camera.update();
    			

    			
    			Assets.skill_wheel.setTexture(new Texture(Gdx.files.internal("skills_wheel.png")));
    			Assets.skill_wheel.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); 
    			GScreen.main_control=false;
    			
    			for (int i=0; i<GScreen.pl.Skills_list.size(); i++)
    			{
    				Skill sk=GScreen.pl.Skills_list.get(i);
    				GScreen.Button_list.add(new ButtonSkill(sk.pos.x,sk.pos.y,sk));
    			}
    			
    			
    		}
    		else
    		{
    			Assets.skill_wheel.getTexture().dispose();
    			GScreen.main_control=true;
    			
    			GScreen.skills_camera.zoom=1;
    			GScreen.skills_camera.position.x=500;
    			GScreen.skills_camera.position.y=350;
    			GScreen.skills_camera.update();

    		}
    		

    	}
    	if (key==Keys.I)
    	{
			GScreen.skills_camera.zoom=1;
			GScreen.skills_camera.position.x=500;
			GScreen.skills_camera.position.y=350;
			GScreen.skills_camera.update();
    		
    		GScreen.show_equip=!GScreen.show_equip;
    		
    		if (GScreen.show_equip)
    		{
    			GScreen.show_skills_wheel=false;
    			
    			GScreen.main_control=false;
    			GScreen.Button_list.add(new ButtonEqWeapon(150,250,-1));
    			GScreen.Button_list.add(new ButtonEqWeapon(250,250,-2));
    			
    			GScreen.Button_list.add(new ButtonEqWeapon(350,250,-5));
    			
    			
    			//Assets.shoot00.
    			for (int j=0; j<2; j++)
    			for (int i=0; i<8; i++)
    			{
    				//if (GScreen.pl.inventory[i] instanceof Weapon)
    				{GScreen.Button_list.add(new ButtonEqWeapon(150+i*85,200-j*45,i+j*8));}
    			}
    			
    			GScreen.Button_list.add(new ButtonEqWeapon(200,250,99));
    			GScreen.Button_list.add(new ButtonSkill(30,30,GScreen.pl.Skills_list.get(0)));
    		}
    		else
    		{
    			GScreen.main_control=true;
    		}
    		
    	}
    	
    	System.out.println(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
    	key=-777;
    	
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	MB=false;
    	but=-1;
    	
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    	/*posx=(int) (screenX+GScreen.camera.position.x-500);
    	posy=(int)(700-screenY-350+GScreen.camera.position.y);
    	MB_timer++;*/

        return false;
    }
    
    public static void update()
    {
    	
    	dx=Gdx.input.getX()-prevx;
    	dy=-(Gdx.input.getY()-prevy);
    	

    	
    	
    	posx=(int) ((Gdx.input.getX()+GScreen.camera.position.x/GScreen.camera.zoom-500)*GScreen.camera.zoom);
    	posy=(int)((700-Gdx.input.getY()-350+GScreen.camera.position.y/GScreen.camera.zoom)*GScreen.camera.zoom);
    	
    	sposx=(int) ((Gdx.input.getX()+GScreen.skills_camera.position.x/GScreen.skills_camera.zoom-500)*GScreen.skills_camera.zoom);
    	sposy=(int)((700-Gdx.input.getY()-350+GScreen.skills_camera.position.y/GScreen.skills_camera.zoom)*GScreen.skills_camera.zoom);
    	
    	
    	realx=Gdx.input.getX();
    	realy=700-Gdx.input.getY();
    	
    	if ((GScreen.show_skills_wheel)&&(but==0))
    	{
    		GScreen.skills_camera.position.add((-(realx-GScreen.curx))*GScreen.skills_camera.zoom, -(realy-GScreen.cury)*GScreen.skills_camera.zoom, 0);
    		GScreen.skills_camera.update();
    	}
    	
    	
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
    	/*posx=(int) (screenX+GScreen.camera.position.x-500);
    	posy=(int)(700-screenY-350+GScreen.camera.position.y);
    	*/

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
    	if (GScreen.main_control){GScreen.camera.zoom+=amount/10f;}
    	if (GScreen.show_skills_wheel)
    	{
    		GScreen.skills_camera.zoom+=amount/100.0f;
    		if (GScreen.skills_camera.zoom<0.5f){GScreen.skills_camera.zoom=0.5f;}
    		GScreen.skills_camera.update();
    		//A//ssets.skill_wheel.scale(-amount/50f);
    	}
    	
    	
        return false;
    }

}
