package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends Game {

    public static SpriteBatch batch;
    public static SpriteBatch batch_static;
    public static BitmapFont font;
    public static ShapeRenderer shapeRenderer;
    
    public void create() {
        batch = new SpriteBatch();
        batch_static = new SpriteBatch();
        
        Assets.load_assets();
        
        //Assets.music.play();
        
        shapeRenderer=new ShapeRenderer();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
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