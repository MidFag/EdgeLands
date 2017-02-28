package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


public class InputHandler implements InputProcessor {


	public static int initial_x;

	public static int initial_y;
    
    public static int posx;
    public static int posy;
    
    public static boolean MB=false;
    
    public static int but;
    
    public static int key;
    
    public static float MB_timer;
    
    public static float prevx;
    public static float prevy;
    
    public static float dx;
    public static float dy;
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
    	
    	prevx=Gdx.input.getX();
    	prevy=Gdx.input.getY();
    	
    	
    	posx=(int) ((Gdx.input.getX()+GScreen.camera.position.x/GScreen.camera.zoom-500)*GScreen.camera.zoom);
    	posy=(int)((700-Gdx.input.getY()-350+GScreen.camera.position.y/GScreen.camera.zoom)*GScreen.camera.zoom);
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
    	GScreen.camera.zoom+=amount/10f;
        return false;
    }

}
