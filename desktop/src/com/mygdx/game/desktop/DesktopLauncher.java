package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "EndlessWorld";
		
		cfg.width = 1000;
		cfg.height = 700;
		cfg.samples=16;
		
		new LwjglApplication(new Main(), cfg);
	}
}
