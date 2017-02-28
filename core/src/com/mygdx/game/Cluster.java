package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.entity.Entity;

public class Cluster {
	public List<Phys> Phys_list = new ArrayList<Phys>();
	public List<Entity> Entity_list = new ArrayList<Entity>();
	public Color col;
	
	public Cluster()
	{
		System.out.println("PCluster created");
		
		
	}
}
