package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Phys {
	public Vector2 start=new Vector2();
	public Vector2 end=new Vector2();
	
	public float len;
	
	public float dx;
	public float dy;
	
	public float subline_A;
	public float subline_B;
	
	public float summline;
	public float vector_mul;
	
	public float goal_x;
	public float goal_y;
	
	public Vector2 normal=new Vector2();
	public float angle;
	
	public Object parent;
	
	
	
	public Phys(Vector2 _s, Vector2 _e,boolean _path, Object _parent)
	{
		
		parent=_parent;
		
		start=_s;
		end=_e;
		
		len=(float) Math.sqrt((start.x-end.x)*(start.x-end.x)+(start.y-end.y)*(start.y-end.y));
		
		dx=(start.x-end.x)/(start.y-end.y);
		dy=(start.y-end.y)/(start.x-end.x);
		
    	float a=start.x-end.x;
    	float b=start.y-end.y;;
    	//float c=(float) Math.sqrt((a*a)+(b*b));
    	 angle=(float) (Math.toDegrees(Math.atan2(a, b))+90);
    	 
    	normal=new Vector2(start.x-(start.x-end.x)/2+(float)Math.sin(Math.toRadians(angle))*5,start.y-(start.y-end.y)/2+(float)Math.cos(Math.toRadians(angle))*5);
    	
    	if (_path)
    	for (float i=0; i<=1; i+=30/len)
    	{
    		if (( (int)((start.x+(end.x-start.x)*i)/30)>=0 )&&( (int)((start.x+(end.x-start.x)*i)/30)<300 ))
    		if (( (int)((start.y+(end.y-start.y)*i)/30)>=0 )&&( (int)((start.y+(end.y-start.y)*i)/30)<300 ))
    		{
    		GScreen.path[(int)((start.x+(end.x-start.x)*i)/30)][(int)((start.y+(end.y-start.y)*i)/30)]=-1;
    		}
    	}
		
		
	}
	
	public Phys is_contact(float _x1, float _y1, float _x2, float _y2,float _dx, float _dy, float _l)
	{

		subline_A=(_y1-start.y)*dx;
		subline_B=(_y2-start.y)*dx;
		
		subline_A=_x1-start.x-subline_A;
		subline_B=_x2-start.x-subline_B;
		
		if ((subline_A<=0)^(subline_B<=0))
		{
	
			summline=Math.abs(subline_A)+Math.abs(subline_B);
			//vector_len=_l;//(float) Math.sqrt((_x1-_x2)*(_x1-_x2)+(_y1-_y2)*(_y1-_y2));
			
			vector_mul=_l*(Math.abs(subline_A)/summline);
			
			/*
			goal_x=_x1+(float)Math.sin((_r))*vector_mul;
			goal_y=_y1+(float)Math.cos((_r))*vector_mul;
			 */
			
			goal_x=_x1+_dx*vector_mul;
			goal_y=_y1+_dy*vector_mul;
			
			if ((goal_x>Math.min(start.x,end.x))&&(goal_x<Math.max(start.x,end.x))&&(goal_y>Math.min(start.y,end.y))&&(goal_y<Math.max(start.y,end.y)))
			{
				Main.shapeRenderer.begin(ShapeType.Filled);
					Main.shapeRenderer.circle(goal_x,goal_y,10);
				Main.shapeRenderer.end();
			
	
			return this;
			}
		}
			return null;
		}
	
	public void draw()
	{	
			//Main.batch.begin();
				//Main.font.draw(Main.batch, ""+angle, start.x, start.y);
			//Main.batch.end();
			Main.shapeRenderer.begin(ShapeType.Filled);
			
			Main.shapeRenderer.rectLine(start,end,2);
			
			
			//Main.shapeRenderer.line(start.x-(start.x-end.x)/2,start.y-(start.y-end.y)/2,normal.x,normal.y);
		
			//Main.shapeRenderer.line(end,normal);
			
			/*
			if (is_contact(_x1,_y1,_x2,_y2,_r)>=0)
			{
				Main.shapeRenderer.circle(goal_x,goal_y,vector_mul/50+1);
			}
			*/
			
			Main.shapeRenderer.end();
	}
}
