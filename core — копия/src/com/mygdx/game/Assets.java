package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	
	public static Texture[] tile=new Texture[100];
	
	public static Texture panel;
	public static Texture diod;
	public static Texture particle;
	
	public static Texture explosion;
	
	public static Sound shoot00;
	public static Sound shoot01;
	public static Sound shoot02;
	
	public static Sound metal_sound;

	public static Sound plastic;
	
	public static Sound metal_destroy;

	//public static Sound music;
	
	public static long music_id;
	
	static Music music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
	
	public Assets()
	{
		
	}
	
	public static void load_assets()
	{
		for (int i=0; i<9; i++)
		{
			tile[i]=new Texture(Gdx.files.internal("tile0"+i+".png"));
		}
		
		panel=new Texture(Gdx.files.internal("panel.png"));
		diod=new Texture(Gdx.files.internal("diod.png"));
		particle=new Texture(Gdx.files.internal("particle.png"));
		explosion=new Texture(Gdx.files.internal("explosion.png"));
		
		shoot00 = Gdx.audio.newSound(Gdx.files.internal("shoot00.wav"));
		shoot01 = Gdx.audio.newSound(Gdx.files.internal("shoot01.wav"));
		shoot02 = Gdx.audio.newSound(Gdx.files.internal("shoot02.wav"));
		
		metal_sound = Gdx.audio.newSound(Gdx.files.internal("metal_sound.wav"));
		plastic = Gdx.audio.newSound(Gdx.files.internal("plastic.wav"));
		
		metal_destroy = Gdx.audio.newSound(Gdx.files.internal("metal_destroy.wav"));
		
		//music = Gdx.audio.newSound(Gdx.files.internal("music.wav"));

		music.setLooping(true);
		music.setVolume(0.25f);
		music.play();
		
	}
}
