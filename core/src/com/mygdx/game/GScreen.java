package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.entity.Entity;
import com.mygdx.entity.EntityPlayer;
import com.mygdx.entity.Shd;
import com.mygdx.entity.decorations.DecorTube;
import com.mygdx.entity.decorations.DecorTubeCarcas;
import com.mygdx.entity.enemies.EntityVizjun;
import com.mygdx.entity.missiles.Missile;
import com.mygdx.equip.energoshield.EnergoshieldSimple;
import com.mygdx.equip.weapon.WeaponSimpleFirle;
import com.mygdx.equip.weapon.WeaponSimpleMinigun;
import com.mygdx.equip.weapon.WeaponSimpleShotgun;
import com.mygdx.game.GUI.Button;



public class GScreen implements Screen {
    final Main game;
    
    public static float[][] cels=new float[200][200];
    public static float[][] path=new float[300][300];
    public static long[][] path_time=new long[300][300];
    
    public static int[][] tile_map=new int[300][300];
    public static int[][] tile_map_overlay=new int[300][300];
    
    public static Cluster[][] cluster=new Cluster[30][30];
    
    public static List<Phys> Phys_list = new ArrayList<Phys>();
    public static List<Missile> Missile_list = new ArrayList<Missile>();
    public static List<Entity> Entity_list = new ArrayList<Entity>();
    public static List<Shd> Shd_list = new ArrayList<Shd>();
    
    public static Entity pl;
    
    static OrthographicCamera camera;
    static OrthographicCamera skills_camera;


    
    public static Phys near_object;
    public float near_dist;
    
    public Vector2 temp_vectorA=new Vector2(0.0f,0.0f);
    public Vector2 temp_vectorB=new Vector2(0.0f,0.0f);
    
    public Vector2 prev_pos=new Vector2();
    
    public float cooldown;
    public float overlay_cooldown;
    
    public static boolean show_equip=false;
    public static boolean show_skills_wheel=false;
    public static boolean main_control=true;
    
    public static float zoom=1;
    
    public static float curx=1;
    public static float cury=1;
    
    public static List<Button> Button_list = new ArrayList<Button>();
    
    
    public static Entity add_entity(Entity _e)
    {
    	int x=(int)(_e.pos.x/300);
    	int y=(int)(_e.pos.y/300);
    	
    	cluster[x][y].Entity_list.add(_e);
    	
    	return _e;
    }
    
    public static Phys get_contact(float _x,float _y,float _x2,float _y2,float _dx,float _dy,float _d,boolean _break, boolean _global)
    {
    	float near_dist=9999;
    	near_object=null;
    	
    	int x_min=Math.min((int)(_x/300)-1, (int)(_x2/300)-1);
    	int x_max=Math.max((int)(_x/300)+1, (int)(_x2/300)+1);
    	
    	int y_min=Math.min((int)(_y/300)-1, (int)(_y2/300)-1);
    	int y_max=Math.max((int)(_y/300)+1, (int)(_y2/300)+1);
    	
    	x_min=Math.max(0, x_min);
    	y_min=Math.max(0, y_min);
    	
    	for (int x=x_min; x<=x_max; x++)
    	for (int y=y_min; y<=y_max; y++)
    	for (int i=0; i<cluster[x][y].Phys_list.size(); i++)
    	{
    		if (cluster[x][y].Phys_list.size()>0)
    		{
    			Phys po=cluster[x][y].Phys_list.get(i).is_contact(_x,_y,_x2,_y2,_dx,_dy,_d);
    			//cluster[x][y].Phys_list.get(i).draw();
    			
		    	if (po!=null)
		    	if (po.vector_mul<near_dist)
		    	{
		    		near_object=po;
		    		near_dist=po.vector_mul;
		    		
		    		if (_break){ x=999; y=999; break;}
		    	}
    		}
    	}
    	
    	if (_global)
    	{
    		if (Phys_list.size()>0)
	    	for (int i=0; i<Phys_list.size(); i++)
	    	{
	
	    			Phys po=Phys_list.get(i).is_contact(_x,_y,_x2,_y2,_dx,_dy,_d);
	    			//Phys_list.get(i).draw();
	    			
			    	if (po!=null)
			    	if (po.vector_mul<near_dist)
			    	{
			    		near_object=po;
			    		near_dist=po.vector_mul;
			    	}
	    		}
    	}
    	
    	return near_object;
    }
    
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
    	
        for (int i=0; i<30; i++)//;
        for (int j=0; j<30; j++)//;
        {
        	cluster[j][i]=new Cluster();
        }
    	
        temp_vectorA=new Vector2();
        temp_vectorB=new Vector2();
        
        this.game = gam;
        Random rn=new Random();
        
        InputHandler.but=-1;
        
        pl=new EntityPlayer(new Vector2(300,200),false);
        
        for (int i=0; i<30; i++)//;
        {
        	switch (rn.nextInt(4))
        	{
        		case 0: pl.inventory[i]=new WeaponSimpleFirle();	break;
        		case 1: pl.inventory[i]=new WeaponSimpleMinigun();	break;
        		case 2: pl.inventory[i]=new WeaponSimpleShotgun();	break;
        		case 3: pl.inventory[i]=new EnergoshieldSimple(); System.out.println("Shield in slot "+i);	break;
        	}
        }
        
        
       // float tubes_count
        DecorTube o=null;
        
        for (int k=0; k<1; k++)
        {
        	o=null;
        	
	        for (int j=0; j<3; j++)
	        {
	        	
	        	Entity tub=add_entity(new DecorTubeCarcas(new Vector2(200+j*440,300+k*55),true));
	        	//Entity_list.add();
	        	
	        	if (o!=null){((DecorTube)tub).left=o;}
	        	if (o!=null){((DecorTube)o).right=((DecorTube)tub);}
	        	
	        	o=(DecorTube) tub;
	        	
	        	
		        for (int i=0; i<10; i++)
		        {
		        	if ((Math.random()<1.9)||(i==0)||(i==9))
		        	{
		        		tub=add_entity(new DecorTube(new Vector2(200+j*440+40+i*40,300+k*55),true));
		        		
		        		((DecorTube)tub).left=o;
		        		((DecorTube)o).right=((DecorTube)tub);
		        		
		        		o=(DecorTube) tub;
		        	}
		        }
	        }
        }

        
        for (int i=0; i<300; i++)
        for (int j=0; j<300; j++)
        {
        	tile_map[j][i]=(int) rnd(3);
        	if (rnd(100)<20)
        	{tile_map[j][i]=(int) rnd(9);}
        	
        	tile_map_overlay[j][i]=-1;
        	
        }
        
        
        Gdx.input.setInputProcessor(new InputHandler());

        for (int i=0; i<1; i++)
        {
        	float randx=rnd(900);
        	float randy=rnd(700);
        	
        	Phys_list.add(new Phys(new Vector2(randx,randy), new Vector2(randx+rnd(200)-100,randy+rnd(200)-100),true,null,true));
        }
        

        /*
        Phys_list.add(new Phys(new Vector2(115,100), new Vector2(115f,200),true,null,true));
        Phys_list.add(new Phys(new Vector2(101,200), new Vector2(302,201),true,null,true));
        Phys_list.add(new Phys(new Vector2(301,200), new Vector2(250,100),true,null,true));
        Phys_list.add(new Phys(new Vector2(250,100), new Vector2(100,101),true,null,true));*/
        
        
        Phys_list.add(new Phys(new Vector2(80,9000), new Vector2(80,80),true,null,true));
        Phys_list.add(new Phys(new Vector2(9005,9000), new Vector2(9000,80),true,null,true));
        
        Phys_list.add(new Phys(new Vector2(80,80), new Vector2(9000,80),true,null,true));
        Phys_list.add(new Phys(new Vector2(80,9000), new Vector2(9000,9000),true,null,true));
        
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
	        
	        Phys_list.add(new Phys(p1, p2,true,null,true));
	        Phys_list.add(new Phys(p2, p3,true,null,true));
	        Phys_list.add(new Phys(p3, p4,true,null,true));
	        Phys_list.add(new Phys(p4, p1,true,null,true));
	        // create the camera and the SpriteBatch
	        camera = new OrthographicCamera();
			camera.setToOrtho(false, 1000/1, 700/1);
			camera.position.set(new Vector3(500,350,0));
			
	        skills_camera = new OrthographicCamera();
			skills_camera.setToOrtho(false, 1000/1, 700/1);
			skills_camera.position.set(new Vector3(500,350,0));
			skills_camera.update();
        }

		camera.zoom=1;
		
        pl.spr.setRotation(10);
        pl.spr.setPosition(InputHandler.posx, InputHandler.posy);
       // pl.spr.setSize(51,21);
        pl.spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Nearest); 

        for (int i=0; i<100; i++)
        {
        	if (Math.random()>0.75f)
        	add_entity(new EntityVizjun(new Vector2(350+rnd(3000),300+rnd(3000)),false));
        	else
        	add_entity(new Entity(new Vector2(350+rnd(3000),300+rnd(3000)),false));
        }
        
        //Entity_list.add(new DecorStoneBarak(new Vector2(100,200)));

        for (int i=0; i<100; i++)
        for (int j=0; j<100; j++)
        {
        	cels[i][j]=rnd(1);

        	cels[i][j]=cels[i][j]*cels[i][j]*cels[i][j];
        	
        	if (rnd(100)<=1){cels[i][j]=10;}
        }



    }



    @SuppressWarnings("static-access")
	@Override
    public void render(float delta) {
    	delta/=1f;
    	
    	if (!main_control)
    	{delta/=10f;}
    	
    	if (delta>0.1f){delta=0.1f;}

    	
        Gdx.gl.glClearColor(0.45f, 0.5f, 0.55f, 0.5f);
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        
    	
    	cooldown--;
    	overlay_cooldown--;
    	
    	curx=InputHandler.realx;
    	cury=InputHandler.realy;
    	
    	InputHandler.update();
    	
    	
    	
    	int plposx=(int)(pl.pos.x/30f);
    	int plposy=(int)(pl.pos.y/30f);
    	
    	Main.batch.begin();

		for (int i=plposy-30; i<plposy+30; i++)
		{	
			if (overlay_cooldown<=0)
			tile_map_overlay[Math.max(0, Math.min(299,(int)(plposx+Math.random()*60-30)))][Math.max(0, Math.min(299,i))]=-1;
	 		
			for (int j=plposx-30; j<plposx+30; j++)
	 		if ((i>=0)&&(i<300)&&(j>=0)&&(j<300))
	 		{
	 				if (tile_map_overlay[j][i]<0)
					{Main.batch.draw(Assets.tile[tile_map[j][i]], j*30-15, i*30-15);}
	 				else
	 				{Main.batch.draw(Assets.tile[tile_map_overlay[j][i]], j*30-15, i*30-15);}	
					
	 		}
		}
		
		Main.batch.end();
		
	    plposx=(int)(pl.pos.x/15f);
	    plposy=(int)(pl.pos.y/15f);
		
		 	Gdx.gl.glEnable(GL20.GL_BLEND);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	        game.shapeRenderer.begin(ShapeType.Filled);
		

		
			if (path[plposx][plposy]<900)
			{
				path[plposx][plposy]=0;
				path_time[plposx][plposy]=TimeUtils.millis();
			}
		

	    
 		for (int i=plposy-60; i<plposy+60; i++)
 		for (int j=plposx-60; j<plposx+60; j++)
 		if ((i>=0)&&(i<300)&&(j>=0)&&(j<300))
 		{
 			
 			if (path[j][i]>=999)
 			{
 				game.shapeRenderer.setColor(1,1/2f,1/4f,1/8f);
 			}
 			else
 			{
 				game.shapeRenderer.setColor(path[j][i]/100f,1-path[j][i]/100f,0.1f,0.1f);
 				if (path[j][i]==0){game.shapeRenderer.setColor(Color.WHITE);}
 			}
 			
 			if ((cooldown<=0)&&(path[j][i]<100)&&(path[j][i]>=0))
 			{
	 			

	 			if ((j>0)&&(path_time[j-1][i]<path_time[j][i])&&(path[j-1][i]<900)){path[j-1][i]=path[j][i]+1; path_time[j-1][i]=path_time[j][i];}
	 			if ((j<290)&&(path_time[j+1][i]<path_time[j][i])&&(path[j+1][i]<900)){path[j+1][i]=path[j][i]+1; path_time[j+1][i]=path_time[j][i];}
	 			
	 			if ((i>0)&&(path_time[j][i-1]<path_time[j][i])&&(path[j][i-1]<900)){path[j][i-1]=path[j][i]+1; path_time[j][i-1]=path_time[j][i];}
	 			if ((i<290)&&(path_time[j][i+1]<path_time[j][i])&&(path[j][i+1]<900)){path[j][i+1]=path[j][i]+1; path_time[j][i+1]=path_time[j][i];}	
 			}

 			/*
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * /=/=/=/		*PATH* VISUALISATION		/=/=/=/
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * 
 			 */
 		}
 		game.shapeRenderer.end();
 		
 		game.batch.begin();
 		game.shapeRenderer.begin(ShapeType.Filled);
 			if (Shd_list!=null)
 			for (int k=0; k<Shd_list.size(); k++)
 			{
 				Shd_list.get(k).draw();
 				Shd_list.get(k).update(delta);
 			}
 		game.shapeRenderer.end();
 		game.batch.end();

 		
		
		
		
 		for (int i=plposy-40; i<plposy+40; i++)
 		for (int j=plposx-40; j<plposx+40; j++)
		if ((i>=0)&&(i<500)&&(j>=0)&&(j<300))
 		{
 			if ((cooldown<=0)&&(path[j][i]<100)&&(path[j][i]>=0))
 			{
	 			if ((j>0)&&(path_time[j-1][i]<path_time[j][i])&&(path[j-1][i]<900)){path[j-1][i]=path[j][i]+1; path_time[j-1][i]=path_time[j][i];}
	 			if ((j<290)&&(path_time[j+1][i]<path_time[j][i])&&(path[j+1][i]<900)){path[j+1][i]=path[j][i]+1; path_time[j+1][i]=path_time[j][i];}
	 			
	 			if ((i>0)&&(path_time[j][i-1]<path_time[j][i])&&(path[j][i-1]<900)){path[j][i-1]=path[j][i]+1; path_time[j][i-1]=path_time[j][i];}
	 			if ((i<290)&&(path_time[j][i+1]<path_time[j][i])&&(path[j][i+1]<900)){path[j][i+1]=path[j][i]+1; path_time[j][i+1]=path_time[j][i];}	
 			}

 			/*
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * /=/=/=/		*PATH* VISUALISATION		/=/=/=/
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * 
 			 */
 			
 			
 			

 		}
		
		
 					if (cooldown<=0)
 					{cooldown=10;}
 					
 					if (overlay_cooldown<=0)
 					{overlay_cooldown=30;}
 		
 		

        Gdx.gl.glDisable(GL20.GL_BLEND);
        
 		game.shapeRenderer.setColor(Color.WHITE);
        
    	float a=InputHandler.posx-pl.pos.x;
    	float b=(InputHandler.posy)-pl.pos.y;
    	
    	float c=(float) Math.toDegrees(Math.atan2(a, b));
    	pl.spr.setRotation(360-c);

            near_object=null;
        	near_dist=99999;
        
    		
       	 prev_pos.x=pl.pos.x+0.0001f;
       	 prev_pos.y=pl.pos.y+0.0001f;
       	 
       	 
       	 if (pl.armored_shield.value>0)
       	 {
	       	 if (Gdx.input.isKeyPressed(Keys.W)){pl.add_impulse(0, pl.speed,delta);}
	       	 if (Gdx.input.isKeyPressed(Keys.S)){pl.add_impulse(0, -pl.speed,delta);}
	       	 
	       	 if (Gdx.input.isKeyPressed(Keys.A)){pl.add_impulse(-pl.speed, 0,delta);}
	       	 if (Gdx.input.isKeyPressed(Keys.D)){pl.add_impulse( pl.speed, 0,delta);}
       	 }
       	 
       	pl.update(delta);
       	
       	 
       	 float move_vector_angle=(float) Math.toDegrees(Math.atan2(pl.pos.x-prev_pos.x, pl.pos.y-prev_pos.y));
       	 move_vector_angle=(float) Math.toRadians(move_vector_angle);
       	 float move_vector=prev_pos.dst(pl.pos);
        	
       	 near_object=null;

       	 	
         	near_object=get_contact(prev_pos.x,prev_pos.y,pl.pos.x,pl.pos.y,(float)Math.sin(move_vector_angle),(float)Math.cos(move_vector_angle),move_vector,false,true);

         	//if (near_object!=null)
         	//Phys_list.remove(near_object);
         	//Missile_list.get(i).update(delta);
         
         
     	if (near_object!=null)
     	if (near_object.parent!=pl)
     	{
     		 System.out.println("Wallstuck");
     		pl.hard_move(sinR(near_object.angle)*-(near_object.vector_mul-(move_vector+0.52f)), cosR(near_object.angle)*-(near_object.vector_mul-(move_vector+0.52f)),1);
     		//pl.pos.add((float)Math.sin(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.2f)), (float)Math.cos(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.2f)));
     	}

     	
    	@SuppressWarnings("unused")
		Phys po=null;
    	Missile mis=null;
        for (int i=0; i<Missile_list.size();i++)
        {
        	mis=Missile_list.get(i);
        	
        	temp_vectorA.x=mis.pos.x;
        	temp_vectorA.y=mis.pos.y;
        	{Missile_list.get(i).update(delta);}
        	
        	near_dist=99999;
        	near_object=null;//w
        	
        	


            	 near_object=get_contact(mis.px,mis.py,mis.pos.x,mis.pos.y,mis.sx,mis.sy,mis.speed*delta,false,true);
            
            
        	if (near_object!=null)
        	{
        		
        		
        		if (near_object.parent==null)
        		{
        			mis.pos.x=near_object.goal_x;
        			mis.pos.y=near_object.goal_y;
        			
        			Missile_list.get(i).lifetime=-1;
        			//System.out.println("Wallstuck");
        			}
        		else
        		if
        		(
        				(near_object.parent!=null)
        				&&
        				(
        						(((Entity) near_object.parent).is_AI!=mis.is_enemy)
        						||
        						(((Entity) near_object.parent).burrow)
        						||
        						(((Entity) near_object.parent).is_decor)
        				)
        		)
        		{
        			
        			mis.pos.x=near_object.goal_x;
        			mis.pos.y=near_object.goal_y;
        			
        			if (!Missile_list.get(i).is_decor)
        			{
        				
        				
        				float reflect_value=((Entity) near_object.parent).armored_shield.total_reflect;
        				float reflect_chance=Math.max(0.65f, 1.0f-reflect_value/Missile_list.get(i).damage);
        				
        				reflect_chance*=1.0f-(reflect_value/(reflect_value+100.0f));
        				
	        			if ((Math.random()<reflect_chance))
	        			{
	        				Missile_list.get(i).lifetime=-1;
	        				((Entity) near_object.parent).hit_action(near_object,Missile_list.get(i).damage);
	        			}
	        			else
	        			{
	        				Missile_list.get(i).lifetime=10;
	        				Missile_list.get(i).angle+=Math.toRadians(180);
	        				
	        				Missile_list.get(i).update_vectors_state();
	        				
	        				Missile_list.get(i).is_enemy=((Entity) near_object.parent).is_AI;
	        				Missile_list.get(i).col=Color.GREEN;
	        			}
        			}
        			else
        			{Missile_list.get(i).lifetime=-1;}
        		}        		//pl.pos.add((float)Math.sin(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.5f)), (float)Math.cos(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.5f)));
        	}
        	else
        	{}
        	
        	temp_vectorB.x=mis.pos.x;
        	temp_vectorB.y=mis.pos.y;
        	
        	Missile_list.get(i).draw();
			
			if (Missile_list.get(i).have_shd)
			{Shd_list.add(Missile_list.get(i).get_shd(new Vector2(temp_vectorA.x,temp_vectorA.y), new Vector2(temp_vectorB.x,temp_vectorB.y)));}
        	


        	
        	
        }
        
            for (int i=0; i<Missile_list.size();i++)
            {
            	if (Missile_list.get(i).lifetime<=0)
            	{
            		Missile_list.remove(i);
            		i--;
            	}

            	//Missile_list.get(i).check();
            }
            
        int fx=0;
        int fy=0;
         

        int cluster_x=(int)(pl.pos.x/300);
        int cluster_y=(int)(pl.pos.y/300);
      Main.batch.begin();
      	for (int x=cluster_x-3; x<=cluster_x+3; x++)
      	for (int y=cluster_y-3; y<=cluster_y+3; y++)
      	if ((x>=0)&&(y>=0))
        for (int i=0; i<cluster[x][y].Entity_list.size();i++)
        {

        	
        	Entity e=cluster[x][y].Entity_list.get(i);
        	if (e.is_AI)
        	{	
        		fx=(int)(e.pos.x/15);
	            fy=(int)(e.pos.y/15);
	            
	            
	        	if ((fx>0)&&(fy>0)&&(fx<299)&&(fy<299))
	        	{
	        		if ((path[fx][fy+1]<path[fx][fy-1]-0)&&(path[fx][fy+1]>=5)&&(path[fx][fy+1]<900))
	        	
		        	{e.add_impulse(0, e.speed,delta);}
		        	
		        	if ((path[fx][fy-1]<path[fx][fy+1]-0)&&(path[fx][fy-1]>=5)&&(path[fx][fy-1]<900))
		        	{e.add_impulse(0, -e.speed,delta);}
		        	
		        	if ((path[fx+1][fy]<path[fx-1][fy]-0)&&(path[fx+1][fy]>=5)&&(path[fx+1][fy]<900))
		        	{e.add_impulse(e.speed, 0,delta);}
		        	
		        	if ((path[fx-1][fy]<path[fx+1][fy]-0)&&(path[fx-1][fy]>=5)&&(path[fx-1][fy]<900))
		        	{e.add_impulse(-e.speed, 0,delta);}
		        	
		        	
		        	
		        	if ((path[fx][fy+1]<path[fx][fy-1]-0)&&(path[fx][fy+1]<5)&&(path[fx][fy+1]<130))
		        	{e.add_impulse(0, -e.speed,delta);}
		        	
		        	if ((path[fx][fy-1]<path[fx][fy+1]-0)&&(path[fx][fy-1]<5)&&(path[fx][fy-1]<130))
		        	{e.add_impulse(0, e.speed,delta);}
		        	
		        	if ((path[fx+1][fy]<path[fx-1][fy]-0)&&(path[fx+1][fy]<5)&&(path[fx+1][fy]<130))
		        	{e.add_impulse(-e.speed, 0,delta);}
		        	
		        	if ((path[fx-1][fy]<path[fx+1][fy]-0)&&(path[fx-1][fy]<5)&&(path[fx-1][fy]<130))
		        	{e.add_impulse(e.speed, 0,delta);}
		        	
		        	if (path[(int)(e.pos.x/15)][(int)(e.pos.y/15)]<100)
		        	{path[(int)(e.pos.x/15)][(int)(e.pos.y/15)]=700+path[(int)(e.pos.x/15)][(int)(e.pos.y/15)]*0;}
    			
		        	path_time[(int)(e.pos.x/15)][(int)(e.pos.y/15)]=TimeUtils.millis();
	        	}
        	}
 
        	e.draw();
        	e.update(delta);
        	
    		
    			
    		
        }
      
        
        pl.draw();
        Main.batch.end();
        
        
        
        game.shapeRenderer.begin(ShapeType.Filled);
        
        	for (int i=0; i<Phys_list.size(); i++)
        	{Phys_list.get(i).draw();}
        	
	      	for (int x=cluster_x-2; x<cluster_x+2; x++)
	      	for (int y=cluster_y-2; y<cluster_y+2; y++)
	      	if ((x>=0)&&(y>=0))
	      	{
	      		for (int i=0; i<cluster[x][y].Phys_list.size(); i++)
	      		{cluster[x][y].Phys_list.get(i).draw();}
	      	}
        	
        	if (pl.dead_time>0)
        	{   
        		Gdx.gl.glEnable(GL20.GL_BLEND);
            	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        		game.shapeRenderer.setColor(1, 0.25f, 0.125f, Math.min(1, (pl.dead_time/10f)*0.8f));
        		game.shapeRenderer.rect(-10000, -10000, 20000, 20000);
        	}
        	//game.shapeRenderer.circle(InputHandler.posx, InputHandler.posy, 3);
        	//game.shapeRenderer.circle(prev_pos.x+(float)Math.sin((move_vector_angle))*speed*10,prev_pos.y+(float)Math.cos((move_vector_angle))*speed*10, 3);
        game.shapeRenderer.end();
        
        
		Main.shapeRenderer.begin(ShapeType.Filled);
			Main.shapeRenderer.setColor(0.5f, 1, 0.6f, 0.5f);
			
			Main.shapeRenderer.setColor(1.0f, 0.5f, 0.25f, 1.0f);
			Main.shapeRenderer.line(pl.pos.x,pl.pos.y,InputHandler.posx,InputHandler.posy);
		Main.shapeRenderer.end();
		
		
		Main.shapeRenderer.setColor(0.9f, 1, 0.95f, 1.0f);
		
		if (show_equip)
		{
			Gdx.gl.glEnable(GL20.GL_BLEND);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			game.shapeRenderer_static.begin(ShapeType.Filled);
				game.shapeRenderer_static.setColor(0.5f, 0.6f, 0.7f, 0.55f);
				game.shapeRenderer_static.rect(77, 77, 1000-154,700-154);
			game.shapeRenderer_static.end();
		}
		
		if (show_skills_wheel)
		{
			Gdx.gl.glEnable(GL20.GL_BLEND);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			//game.shapeRenderer_static.begin(ShapeType.Filled);
			//game.shapeRenderer_static.end();
	        game.batch_static.begin();
	        	Assets.skill_wheel.setPosition(-1024, -1024);
	        	Assets.skill_wheel.draw(game.batch_static);
	        game.batch_static.end();
			
		}
		
		game.batch_static.begin();
		//game.shapeRenderer_static.begin(ShapeType.Filled);
			Main.font.draw(Main.batch_static, "FPS: "+Math.round(1.0f/delta), 17, 30);
			Main.font.draw(Main.batch_static, "Shield : "+pl.armored_shield.warm, 17, 60);
			
		game.batch_static.draw(Assets.panel, 400, 17);
		
		for (int i=0; i<50*pl.armored_shield.value/pl.armored_shield.total_value; i++)
		{
			game.batch_static.draw(Assets.diod, 400+7*i+5, 17+3);
		}
		
			for (int i=0; i<Button_list.size(); i++)
			{
				if (!Button_list.get(i).need_remove)
				{Button_list.get(i).draw();}
			}
			
			for (int i=0; i<Button_list.size(); i++)
			{
				if (!Button_list.get(i).need_remove)
				{
					Button_list.get(i).second_draw();
					Button_list.get(i).update(delta);
					Button_list.get(i).second_update(delta);
				}
				else
				{Button_list.remove(i); i--;}
			}
			
		//game.shapeRenderer_static.end();
		game.batch_static.end();
		
		game.shapeRenderer.begin(ShapeType.Line);
		

		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
		game.shapeRenderer.setColor(0.5f, 0.6f, 0.7f, 0.15f);
		for (int i=0; i<30; i++)
		for (int j=0; j<30; j++)
		{		
		game.shapeRenderer.rect(0, 0, j*30*10,i*30*10);
		}
		game.shapeRenderer.end();
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
		
		if (pl.armored_shield.value<=0)
		{
			camera.rotate(1);
			camera.zoom+=0.001f;
			
			 if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			 {
				 pl.armored_shield.value=1000;
				 pl.dead_time=0;
				 
				 camera.direction.set(0, 0, -1);
				 camera.up.set(0, 1, 0);
				 camera.zoom=1;
				 camera.update();
				 
				 for (int i=0; i<Entity_list.size(); i++)
				 {Entity_list.get(i).dead_action(null,false);}
				 
				 Entity_list.clear();
				 
				  for (int i=0; i<200; i++)
			     {        	
					if (Math.random()>0.75f)
			        Entity_list.add(new EntityVizjun(new Vector2(350+rnd(3000),300+rnd(3000)),false));
		        	else
		        	Entity_list.add(new Entity(new Vector2(350+rnd(3000),300+rnd(3000)),false));
				 }
			            
			     //Entity_list.add(new DecorStoneBarak(new Vector2(100,200)));
			     
			     pl=new EntityPlayer(new Vector2(200,100),false);
			     
				
			}
		}
        

        game.batch.setProjectionMatrix(camera.combined);
        
        //game.batch_static.setProjectionMatrix(camera.combined);
      
        
        game.shapeRenderer.setProjectionMatrix(camera.combined);
        
        game.shapeRenderer_static.setProjectionMatrix(skills_camera.combined);
        game.batch_static.setProjectionMatrix(skills_camera.combined);
      
        
        //InputHandler.but=-1;
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

      
    }



}