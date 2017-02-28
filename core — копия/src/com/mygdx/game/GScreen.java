package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;



public class GScreen implements Screen {
    final Main game;
    
    public static float[][] cels=new float[200][200];
    public static int[][] path=new int[300][300];
    public static long[][] path_time=new long[300][300];
    
    public static int[][] tile_map=new int[300][300];
    
    public static List<Phys> Phys_list = new ArrayList<Phys>();
    public static List<Missile> Missile_list = new ArrayList<Missile>();
    public static List<Entity> Entity_list = new ArrayList<Entity>();
    
    public static Entity pl=new EntityPlayer(new Vector2(300,200));
    
    Sound dropSound;
    Music rainMusic;
    static OrthographicCamera camera;
    Rectangle bucket;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    Vector3 vec=new Vector3();
    
    public Phys near_object;
    public float near_dist;
    
    public Vector2 temp_vector=new Vector2(0.0f,0.0f);

    public Vector2 prev_pos=new Vector2();
    
    public float cooldown;
    
    public static float rnd(float _r)
    {
    	return (float)(Math.random()*_r);
    	//return 0;
    }
    
    public static float sinR(float _a)
    {
    	return (float) Math.sin(Math.toRadians(_a));
    }
    
    public static float cosR(float _a)
    {
    	return (float) Math.cos(Math.toRadians(_a));
    }
    
    public static float sin(float _a)
    {
    	return (float) Math.sin((_a));
    }
    
    public static float cos(float _a)
    {
    	return (float) Math.cos((_a));
    }
    
    public GScreen(final Main gam) {
        this.game = gam;
       

        for (int i=0; i<300; i++)
        for (int j=0; j<300; j++)
        {
        	tile_map[j][i]=(int) rnd(3);
        	if (rnd(100)<20)
        	{tile_map[j][i]=(int) rnd(9);}
        	
        }
        
        // load the images for the droplet and the bucket, 64x64 pixels each
        
        Gdx.input.setInputProcessor(new InputHandler());

        for (int i=0; i<1; i++)
        {
        	float randx=rnd(900);
        	float randy=rnd(700);
        	
        	Phys_list.add(new Phys(new Vector2(randx,randy), new Vector2(randx+rnd(200)-100,randy+rnd(200)-100),true,null));
        }
        

        
        Phys_list.add(new Phys(new Vector2(100,100), new Vector2(102.0f,200),true,null));
        Phys_list.add(new Phys(new Vector2(101,200), new Vector2(302,201),true,null));
        Phys_list.add(new Phys(new Vector2(301,200), new Vector2(250,100),true,null));
        Phys_list.add(new Phys(new Vector2(250,100), new Vector2(100,101),true,null));
        
        Phys_list.add(new Phys(new Vector2(55,9000), new Vector2(50,45),true,null));
        Phys_list.add(new Phys(new Vector2(9005,9000), new Vector2(9000,45),true,null));
        
        Phys_list.add(new Phys(new Vector2(45,50), new Vector2(9000,45),true,null));
        Phys_list.add(new Phys(new Vector2(45,9000), new Vector2(9000,9000),true,null));
        
			/*
			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
			 * /=/=/=/			*PHYS* GENERATOR		/=/=/=/
			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
			 * 
			 */
        
        for (int i=0; i<200; i++)
        {
	        Vector2 pc=new Vector2(rnd(10000),rnd(10000));
	        float vecangle=rnd(360);
	        Vector2 p1=new Vector2(pc.x+sinR(vecangle)*rnd(400),pc.y+cosR(vecangle)*rnd(400));
	        Vector2 p2=new Vector2(pc.x+sinR(vecangle+90)*rnd(400),pc.y+cosR(vecangle+90)*rnd(400));
	        Vector2 p3=new Vector2(pc.x+sinR(vecangle+180)*rnd(400),pc.y+cosR(vecangle+180)*rnd(400));
	        Vector2 p4=new Vector2(pc.x+sinR(vecangle+270)*rnd(400),pc.y+cosR(vecangle+270)*rnd(400));
	        
	        Phys_list.add(new Phys(p1, p2,true,null));
	        Phys_list.add(new Phys(p2, p3,true,null));
	        Phys_list.add(new Phys(p3, p4,true,null));
	        Phys_list.add(new Phys(p4, p1,true,null));
	        // create the camera and the SpriteBatch
	        camera = new OrthographicCamera();
			camera.setToOrtho(false, 1000/1, 700/1);
			camera.position.set(new Vector3(500,350,0));
        }

		camera.zoom=1;
		
        pl.spr.setRotation(10);
        pl.spr.setPosition(InputHandler.posx, InputHandler.posy);
        pl.spr.setSize(51,21);
        pl.spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); 
        
		
        //for (int i=0; i<10; i++)
        //{Missile_list.add(new Missile(new Vector2(rnd(10)+500,rnd(10)+350),rnd(360),rnd(1)+5.0f));}
       
        for (int i=0; i<200; i++)
        Entity_list.add(new Entity(new Vector2(150+rnd(4000),300+rnd(4000))));
        
        Entity_list.add(new DecorStoneBarak(new Vector2(100,200)));

        for (int i=0; i<100; i++)
        for (int j=0; j<100; j++)
        {
        	cels[i][j]=rnd(1);

        	cels[i][j]=cels[i][j]*cels[i][j]*cels[i][j];
        	
        	if (rnd(100)<=1){cels[i][j]=10;}
        }
        
        // create a Rectangle to logically represent the bucket
        

        // create the raindrops array and spawn the first raindrop


    }



    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
    	
    	//ScreenUtils.getFrameBufferPixmap
    	//final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, 1000, 700);
    	
        Gdx.gl.glClearColor(0.45f, 0.5f, 0.55f, 0.5f);
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        
        /*game.batch.begin();
        game.batch.enableBlending();
        	game.batch.setColor(1, 1, 1, 0.1f);
        	Texture pixmaptex = new Texture( pixmap );
        	game.batch.draw(pixmaptex, 0, 0);
        game.batch.end();*/
        
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        

    	game.shapeRenderer.begin(ShapeType.Filled);
    	
    	
    	
    	cooldown--;
    	
    	InputHandler.update();
    	
    	int plposx=(int)(pl.pos.x/30f);
    	int plposy=(int)(pl.pos.y/30f);
    	
 		for (int i=plposy-40; i<plposy+40; i++)
 		for (int j=plposx-40; j<plposx+40; j++)
 		if ((i>=0)&&(i<300)&&(j>=0)&&(j<300))
 		{
 			if (path[j][i]<0)
 			{
 				game.shapeRenderer.setColor(Color.RED);
 			}
 			else
 			{
 				game.shapeRenderer.setColor(path[j][i]/100f,1-path[j][i]/100f,0.1f,0.15f);
 				if (path[j][i]==0){game.shapeRenderer.setColor(Color.WHITE);}
 			}
 			
 			

 			if (cooldown<=0)
 			{
 				
 				
	 			if (path[(int)(pl.pos.x/30)][(int)(pl.pos.y/30)]>=0)
	 			{path[(int)(pl.pos.x/30)][(int)(pl.pos.y/30)]=0;}
	 			
	 			
	 			
	 			path_time[(int)(pl.pos.x/30)][(int)(pl.pos.y/30)]=TimeUtils.millis();
	 			
	 			//path[(int)(Entity_list.get(0).pos.x/30)][(int)(Entity_list.get(0).pos.y/30)]=1;
	 			//path_time[(int)(Entity_list.get(0).pos.x/30)][(int)(Entity_list.get(0).pos.y/30)]=TimeUtils.millis()-1000;
	 			
	 			if ((j>0)&&(path_time[j-1][i]<path_time[j][i])&&(path[j-1][i]>=0)){path[j-1][i]=path[j][i]+1; path_time[j-1][i]=path_time[j][i];}
	 			if ((j<290)&&(path_time[j+1][i]<path_time[j][i])&&(path[j+1][i]>=0)){path[j+1][i]=path[j][i]+1; path_time[j+1][i]=path_time[j][i];}
	 			
	 			if ((i>0)&&(path_time[j][i-1]<path_time[j][i])&&(path[j][i-1]>=0)){path[j][i-1]=path[j][i]+1; path_time[j][i-1]=path_time[j][i];}
	 			if ((i<290)&&(path_time[j][i+1]<path_time[j][i])&&(path[j][i+1]>=0)){path[j][i+1]=path[j][i]+1; path_time[j][i+1]=path_time[j][i];}
	 			
	 			
	 				
 			}

 			/*
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * /=/=/=/		*PATH* VISUALISATION		/=/=/=/
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * 
 			 */
 			
 			game.shapeRenderer.rect(j*30, i*30, 30, 30);
 			

 		}
 		game.shapeRenderer.end();
 		Main.batch.begin();
 		for (int i=plposy-30; i<plposy+30; i++)
 		for (int j=plposx-30; j<plposx+30; j++)
 		if ((i>=0)&&(i<300)&&(j>=0)&&(j<300))
 		{


 				//Main.batch.setColor(1/(path[j][i]/50f+1f),1/(path[j][i]/50f+1f),1/(path[j][i]/50f+1f),1);
 				//Main.batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
				Main.batch.draw(Assets.tile[tile_map[j][i]], j*30-15, i*30-15);
				//Main.batch.draw(Assets.tile[tile_map[j][i]], j*30-10+25, i*30-10+25);
			
 		}
		Main.batch.end();
 					if (cooldown<=0)
 					{cooldown=2;}
 		
 		

        Gdx.gl.glDisable(GL20.GL_BLEND);
        
 		game.shapeRenderer.setColor(Color.WHITE);
        
    	float a=InputHandler.posx-pl.pos.x;
    	float b=(InputHandler.posy)-pl.pos.y;
    	//float c=(float) Math.sqrt((a*a)+(b*b));
    	float c=(float) Math.toDegrees(Math.atan2(a, b));
    	pl.spr.setRotation(360-c);
        //pl.spr.setPosition(InputHandler.posx-51/2, 700-InputHandler.posy-21/2);
        
        float t=0;
        
        /*
		Main.shapeRenderer.begin(ShapeType.Filled);
		
    	if ((InputHandler.posx/5<200)&&(InputHandler.posx/5>0)&&(InputHandler.posy/5<200)&&(InputHandler.posy/5>0)&&(InputHandler.MB))
    	{
    		for (int z=0; z<50; z++)
    		{
    			if (InputHandler.but==0)
    			{cels[(int) (InputHandler.posx/5+rnd(20)-10)][(int) (99-InputHandler.posy/5+rnd(20)-10)]+=rnd(1);}
    			
    			if (InputHandler.but==1)
    			{cels[(int) (InputHandler.posx/5+rnd(20)-10)][(int) (99-InputHandler.posy/5+rnd(20)-10)]/=1.5f;}
    		}
    	}
		
    	
        for (int i=30; i<150; i++)
        for (int j=30; j<150; j++)
        {
        	for (int k=0; k<16; k++)
        	{	
	        	int x=Math.round(rnd(2)-1);
	        	int y=Math.round(rnd(2)-1);
	        	{cels[i+x][j+y]=(cels[i+x][j+y]+cels[i][j])/2; cels[i][j]=cels[i+x][j+y];}
        	}

        	Main.shapeRenderer.setColor(cels[i][j]*1.1f, cels[i][j]/1.1f, cels[i][j]/3, 1);
        	Main.shapeRenderer.rect(i*5, j*5+200, 5, 5);
        	
        	cels[i][j]*=0.999f;
        }
        Main.shapeRenderer.end();
        */
            near_object=null;
        	near_dist=99999;
        
    		
       	 prev_pos.x=pl.pos.x+0.0001f;
       	 prev_pos.y=pl.pos.y+0.0001f;
       	 
       	 if (Gdx.input.isKeyPressed(Keys.W)){pl.move(0, 1.5f);}
       	 if (Gdx.input.isKeyPressed(Keys.S)){pl.move(0, -1.5f);}
       	 
       	 if (Gdx.input.isKeyPressed(Keys.A)){pl.move(-1.5f, 0);}
       	 if (Gdx.input.isKeyPressed(Keys.D)){pl.move(1.5f, 0);}
       	 
       	 float move_vector_angle=(float) Math.toDegrees(Math.atan2(pl.pos.x-prev_pos.x, pl.pos.y-prev_pos.y));
       	 move_vector_angle=(float) Math.toRadians(move_vector_angle);
       	 float speed=prev_pos.dst(pl.pos);
        	
       	 
        for (int i=0; i<Phys_list.size();i++)
        {
        	Phys_list.get(i).draw();
        	Phys po=Phys_list.get(i).is_contact(prev_pos.x,prev_pos.y,pl.pos.x,pl.pos.y,(float)Math.sin(move_vector_angle),(float)Math.cos(move_vector_angle),speed);
        	if (po!=null)
        	if (po.vector_mul<near_dist)
        	{
        		near_object=po;
        		near_dist=near_object.vector_mul;
        	}
        	//if (near_object!=null)
        	//Phys_list.remove(near_object);
        	//Missile_list.get(i).update(delta);
        }
        
    	if (near_object!=null)
    	{
    		pl.move(sinR(near_object.angle)*-(near_object.vector_mul-(speed+0.2f)), cosR(near_object.angle)*-(near_object.vector_mul-(speed+0.2f)));
    		//pl.pos.add((float)Math.sin(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.2f)), (float)Math.cos(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.2f)));
    	}
        
    	/*
    	if (near_object!=null)
    	{
        game.shapeRenderer.begin(ShapeType.Filled);
        	game.shapeRenderer.circle(near_object.goal_x, near_object.goal_y, 3);
        game.shapeRenderer.end();
    	}*/


    	Missile mis=null;
        for (int i=0; i<Missile_list.size();i++)
        {
        	near_dist=99999;
        	near_object=null;//w
        	Missile_list.get(i).update(delta);
        	
        	for (int j=0; j<Phys_list.size();j++)
            {
        		mis=Missile_list.get(i);
            	Phys po=Phys_list.get(j).is_contact(mis.pos.x,mis.pos.y,mis.dx,mis.dy,mis.dynx,mis.dyny,mis.speed);
            	if (po!=null)
            	if (po.vector_mul<near_dist)
            	{
            		near_object=po;
            		near_dist=near_object.vector_mul;
            	}
            	//if (near_object!=null)
            	//Phys_list.remove(near_object);
            	//Missile_list.get(i).update(delta);
            }
            
        	if (near_object!=null)
        	{
        		if ((near_object.parent==null))
        		{Missile_list.get(i).lifetime=-1;}
        		
        		if
        		(

        				(near_object.parent!=null)
        				&&
        				(((Entity) near_object.parent).is_AI!=mis.is_enemy)
        		)
        			
        		{
        			//System.out.println("YES");
        			Missile_list.get(i).lifetime=-1;
        			((Entity) near_object.parent).hit_action(near_object);
        		}
        		//pl.pos.add((float)Math.sin(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.5f)), (float)Math.cos(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.5f)));
        	}
        	
        	Missile_list.get(i).draw();


        	
        	
        }
        
            for (int i=0; i<Missile_list.size();i++)
            {Missile_list.get(i).check();}
        
        for (int i=0; i<Entity_list.size();i++)
        {
        	Entity_list.get(i).draw();
        	Entity_list.get(i).update(delta);
        	
        	int fx=(int)(Entity_list.get(i).pos.x/30);
        	int fy=(int)(Entity_list.get(i).pos.y/30);
        	
        	
        	if ((path[fx][fy+1]<path[fx][fy])&&(path[fx][fy+1]>=0))
        	{Entity_list.get(i).move(0, 0.8f);}
        	
        	if ((path[fx][fy-1]<path[fx][fy])&&(path[fx][fy-1]>=0))
        	{Entity_list.get(i).move(0, -0.8f);}
        	
        	if ((path[fx+1][fy]<path[fx][fy])&&(path[fx+1][fy]>=0))
        	{Entity_list.get(i).move(0.8f, 0);}
        	
        	if ((path[fx-1][fy]<path[fx][fy])&&(path[fx-1][fy]>=0))
        	{Entity_list.get(i).move(-0.8f, 0);}
        	
        }
        
        pl.update(delta);
        pl.draw();
        
        
        
        game.shapeRenderer.begin(ShapeType.Filled);
        	game.shapeRenderer.circle(InputHandler.posx, InputHandler.posy, 3);
        	game.shapeRenderer.circle(prev_pos.x+(float)Math.sin((move_vector_angle))*speed*10,prev_pos.y+(float)Math.cos((move_vector_angle))*speed*10, 3);
        game.shapeRenderer.end();
        
        
		Main.shapeRenderer.begin(ShapeType.Filled);
			Main.shapeRenderer.setColor(0.5f, 1, 0.6f, 0.5f);
			//Main.shapeRenderer.line(Phys_list.get(0).start.x,Phys_list.get(0).start.y,pl.pos.x,pl.pos.y);
			
			//Main.shapeRenderer.line(Phys_list.get(0).end.x,Phys_list.get(0).end.y,pl.pos.x,pl.pos.y);
			
			Main.shapeRenderer.setColor(1.0f, 0.5f, 0.25f, 1.0f);
			Main.shapeRenderer.line(pl.pos.x,pl.pos.y,InputHandler.posx,InputHandler.posy);
		Main.shapeRenderer.end();
		
		
		Main.shapeRenderer.setColor(0.9f, 1, 0.95f, 1.0f);
        

       
		//game.font.draw(game.batch, ":" + pl.spr.getRotation()+" :" + InputHandler.posy+" :" + delta + InputHandler.posy+" :" + InputHandler.MB, 100, 50);
		game.batch_static.begin();
			game.font.draw(game.batch_static, "shield:" + pl.shield+" rate:"+pl.shield_regen_rate, 100, 50);
			
			game.batch_static.draw(Assets.panel, 400, 17);
			
			for (int i=0; i<50*pl.shield/pl.max_shield; i++)
			{
				game.batch_static.draw(Assets.diod, 400+7*i+5, 17+3);
			}
		game.batch_static.end();
    	 /*a=Entity_list.get(0).pos.x-pl.spr.getX();
    	 b=Entity_list.get(0).pos.y-pl.spr.getY();
    	//float c=(float) Math.sqrt((a*a)+(b*b));
    	 c=(float) Math.toDegrees(Math.atan2(a, b));*/

    	 //if (Input.Keys.W)
    	 
        

        // tell the camera to update its matrices.
		if (InputHandler.but==1)
		{
			float camlen=(float) Math.sqrt((pl.pos.x-InputHandler.posx)*(pl.pos.x-InputHandler.posx)+(pl.pos.y-InputHandler.posy)*(pl.pos.y-InputHandler.posy));
			camlen/=2;
		    camera.position.add(-(camera.position.x-pl.pos.x+sinR(180-pl.spr.getRotation())*camlen)/20, -(camera.position.y-pl.pos.y+cosR(180-pl.spr.getRotation())*camlen)/20, 0.0f);
		    camera.update();
			
		}
		else
		{
			camera.position.add(-(camera.position.x-pl.pos.x)/10, -(camera.position.y-pl.pos.y)/10, 0.0f);
			camera.update();
		}
        

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);
        game.shapeRenderer.setProjectionMatrix(camera.combined);
        // begin a new batch and draw the bucket and
        // all drops


        // process user input


        // make sure the bucket stays within the screen bounds
        

 

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the 
        // value our drops counter and add a sound effect.
        
        

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

        dropSound.dispose();
        rainMusic.dispose();
    }



}