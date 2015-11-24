package com.badlogic.fruitsofhazard;

//Mike's comment line test
//Brian's comment line test

// Prototype Fruits of Hazard
//Config Setup Tips:
//Name: Desktop
//Main Class:com.badlogic.fruitsofhazard.desktop.DesktopLauncher
//Working Directory...[Location on harddrive]/desktop/FruitsOfHazard/android/assets
//Use classpath of module: desktop

		//import java.util.Iterator;

		//import com.badlogic.gdx.ApplicationListener;
		//import com.badlogic.gdx.Gdx;
       // import com.badlogic.gdx.Input;
       // import com.badlogic.gdx.Input.Keys;
		//import com.badlogic.gdx.Screen;
		//import com.badlogic.gdx.audio.Music;
	//	import com.badlogic.gdx.audio.Sound;
	//	import com.badlogic.gdx.graphics.GL20;
	//	import com.badlogic.gdx.graphics.OrthographicCamera;
	//	import com.badlogic.gdx.graphics.Texture;
	//	import com.badlogic.gdx.graphics.g2d.BitmapFont;
	//	import com.badlogic.gdx.graphics.g2d.Sprite;
	//	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	//	import com.badlogic.gdx.math.MathUtils;
	//	import com.badlogic.gdx.math.Rectangle;
		//import com.badlogic.gdx.utils.Array;
		//import com.badlogic.gdx.utils.TimeUtils;
//


		import com.badlogic.gdx.Game;

//package com.badlogic.fruitsofHazard;
		import com.badlogic.gdx.Screen;
		import com.badlogic.gdx.graphics.g2d.BitmapFont;
		import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FruitsofHazard extends Game {

	SpriteBatch batch;
	BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}



