package com.badlogic.fruitsofhazard.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.fruitsofhazard.FruitsofHazard;

//public class DesktopLauncher {
//	public static void main (String[] arg) {
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		new LwjglApplication(new FruitsofHazard(), config);
//	}
//}


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "FruitsofHazard";
		config.width = 1600;
		config.height = 860;
		new LwjglApplication(new FruitsofHazard(), config);
	}
}

