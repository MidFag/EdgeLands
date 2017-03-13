package com.midfag.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends Game {

    public static SpriteBatch batch;
    public static SpriteBatch batch_static;
    public static BitmapFont font;
    public static ShapeRenderer shapeRenderer;
    public static ShapeRenderer shapeRenderer_static;
    public static ShaderProgram shader;
    public static FrameBuffer fbo;
	//public static SpriteBatch batch_wheel;
    
    public void create() {
    	
    	fbo = new FrameBuffer(Pixmap.Format.RGB888, 1000/1, 700/1, false);
    	
        ShaderProgram.pedantic = false;
		 shader=new ShaderProgram(Gdx.files.internal("default.vert"), 
				(Gdx.files.internal("default.frag")));
		
		if (!shader.isCompiled()) {
			System.err.println(shader.getLog());
			System.exit(0);
		}
		
        batch = new SpriteBatch();
        //batch.setShader(shader);
        
        batch_static = new SpriteBatch();
        //batch_wheel = new SpriteBatch();
        batch_static.setShader(shader);
        Assets.load_assets();
        
        //Assets.music.play();
        
        shapeRenderer=new ShapeRenderer();
        shapeRenderer_static=new ShapeRenderer();
        
        //FileHandle fontFile = Gdx.files.internal("rus.ttf");
        //Use LibGDX's default Arial font.
        
        
        //font = new BitmapFont(Gdx.files.internal("rus.fnt"));
        //font.getData().setScale(1.0f, 1.0f);
        
        //shader.setUniformi("u_texture2", 1);
       
        
        Texture texture = new Texture(Gdx.files.internal("rus.png"));
        //texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);// true enables mipmaps
        
        font = new BitmapFont(Gdx.files.internal("rus.fnt"), new TextureRegion(texture), false);
        this.setScreen(new GScreen(this));
        

        
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}