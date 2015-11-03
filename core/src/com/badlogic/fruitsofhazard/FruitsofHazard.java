package com.badlogic.fruitsofhazard;

//Mike's comment line test

// Prototype Fruits of Hazard
//Config Setup Tips:
//Name: Desktop
//Main Class:com.badlogic.fruitsofhazard.desktop.DesktopLauncher
//Working Directory...[Location on harddrive]/desktop/FruitsOfHazard/android/assets
//Use classpath of module: desktop

		import java.util.Iterator;

		import com.badlogic.gdx.ApplicationListener;
		import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.Input.Keys;
		import com.badlogic.gdx.audio.Music;
		import com.badlogic.gdx.audio.Sound;
		import com.badlogic.gdx.graphics.GL20;
		import com.badlogic.gdx.graphics.OrthographicCamera;
		import com.badlogic.gdx.graphics.Texture;
		import com.badlogic.gdx.graphics.g2d.BitmapFont;
		import com.badlogic.gdx.graphics.g2d.Sprite;
		import com.badlogic.gdx.graphics.g2d.SpriteBatch;
		import com.badlogic.gdx.math.MathUtils;
		import com.badlogic.gdx.math.Rectangle;
		import com.badlogic.gdx.utils.Array;
		import com.badlogic.gdx.utils.TimeUtils;


public class FruitsofHazard implements ApplicationListener {


	//Prototype The head of the orange.
	private Texture orangeImage;

	private Texture grapeImage;

	private Sprite orangeSprite;

	private Sound collectSound;
	private Music backroundMusic;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Rectangle orange;

	private Array<Rectangle> fruitDrops;

	private long lastSpawnTime;

	//Project 1
	private String framesPerSecond;
	BitmapFont font;

	//Prototype Sets a constant movement direction vertically, positive is up, negative down.
	private int vertical = 0;

	//Prototype Sets a constant movement direction horizontally, positive is left, negative right.
	private int horizontal = 250;

	//Prototype Sets the spinning speed of the orange. Negative values spin the orange clockwise, Positive counter.
	private int rotationFactor = -5;

	//Prototype Utilizing a linked list to track the tail.
	//LinkedList trail = new LinkedList();


	@Override
	public void create () {

		//Prototype Provides Frames per Second
		framesPerSecond = "FPS: 0";
		font = new BitmapFont();

		// load the images for the droplet and the Orange
		grapeImage = new Texture(Gdx.files.internal("Grape.png"));
		orangeImage = new Texture(Gdx.files.internal("PlayerOrange.png"));

		//Prototype can be utilized to make the orange flash/change momentarily.
		//orangeFlash = new Texture(Gdx.files.internal("orangeFlash.png"));

		//Prototype Producing a sprite for the orange.
		orangeSprite = new Sprite(orangeImage);

		//Prototype fruit collection sound, drop.wave used as a placeholder.
		collectSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

		//Prototype backround music, rain.mp3 used as a placeholder.
		backroundMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		//Sets background music to play at startup and loop constantly.
		backroundMusic.setLooping(true);
		backroundMusic.play();

		//Creates a Camera.
		camera = new OrthographicCamera();

		//Prototype screen size test
		//camera.setToOrtho(false, 800, 480);
		//camera.setToOrtho(false, horizontalScreenSize, verticalScreenSize);

		//Sets the camera size.
		camera.setToOrtho(false, 800, 400);

		batch = new SpriteBatch();


		//Prototype Creates a rectangle to represent a hit box for the orange.
		orange = new Rectangle();

		//Sets the starting position of the orange rectangle.
		orange.x = 400/ 2 - 64 / 2;
		orange.y = 400/ 2 - 64 / 2;

		//Sets the size of the orange rectangle
		orange.width = 64;
		orange.height = 64;

		fruitDrops = new Array<Rectangle>();

		//UNIMPLEMENTED: Alter to spawn different kinds of fruit.
		spawnFruit();

	}

	@Override
	public void render () {

		//Prototype Background Color; RGB? Gets color by dividing the color number by 255f.
		//Guide to change background color: R/255. G/255f, B/255f
		Gdx.gl.glClearColor(255/255f, 190/255f, 125/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(orangeImage, orange.x, orange.y);

		//Prototype continuously redraws the orange sprite; if there is a non-zero rotationFactor, it will spin.
		orangeSprite.setPosition(orange.x,orange.y);
		orangeSprite.draw(batch);

		//Prototype Sets FPS font color
		font.setColor(5.0f,5.0f,5.0f,5.0f);

		//Prototype processes Frames per second
		framesPerSecond = ("FPS: " + Gdx.graphics.getFramesPerSecond());

		//Draws FPS at the specified screen position.
		font.draw(batch, framesPerSecond, 10,475);

		//UNIMPLEMEMTED Alter to draw the chosen fruit image at specified location
		for(Rectangle fruit: fruitDrops)
		{
			batch.draw(grapeImage, fruit.x, fruit.y);
		}
		batch.end();

		//Left key single-press movement.
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			//Sets the orange to move left at a specified rate.
			horizontal =-250;
			vertical = 0;

			//Sets orange rotation to mirror left movement.
			rotationFactor = 5;
		}

		//Right key single-press movement
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			//Sets the orange to move right at a specified rate.
			horizontal = 250;
			vertical = 0;

			//Sets orange rotation to mirror right movements.
			rotationFactor = -5;
		}

		//Up key single-press movement
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			//Sets the orange to move up at a specified rate.
			horizontal = 0;
			vertical = 250;

			//Sets orange rotation to mirror an upward movement
			rotationFactor = -5;
		}

		//Down key single-press movement
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			//Sets the orange to move down at a specified rate.
			horizontal = 0;
			vertical = -250;

			//Sets orange rotation to mirror a downward movement
			rotationFactor = 5;
		}

		//Prototype Moves the orange horizontally and vertically at a constant rate
		orange.x += horizontal * Gdx.graphics.getDeltaTime();
		orange.y += vertical *Gdx.graphics.getDeltaTime();


		//Prototype Right-to-Left boundary; moves orange to left end if it collides with right boundary
		if(orange.x > 800 - 50) orange.x = 0;
		//Solid Boundary; if(orange.x < 0) orange.x = 0;

		//Prototype Left-to-Right boundary; moves orange to right end if it collides with left boundary
		if(orange.x < 0) orange.x = 800 - 50;
		//Solid Boundary; if(orange.x > 800 - 50) orange.x = 800 - 50;


		//Prototype Lower-to-Upper boundary; moves orange to upper end if it collides with lower boundary
		if(orange.y < 0) orange.y = 800- 450;
		//Solid boundary; if(orange.y < 0) orange.y = 0;

        //Prototype Upper-to-Lower boundary; moves orange to Lower end if it collides with upper boundary
        if(orange.y > 800- 450) orange.y = 0;
        //Solid boundary; if(orange.y > 800- 450) orange.y = 800 - 450;

		//Prototype increasing spawn rate by reducing delay
		if(TimeUtils.nanoTime() - lastSpawnTime > 800000000) spawnFruit();

		//Removes fruit that get run over.
		Iterator<Rectangle> iter = fruitDrops.iterator();
		while(iter.hasNext())
		{

			Rectangle fruitSquare= iter.next();

			if (fruitSquare.overlaps(orange)) {
				collectSound.play();

				//Prototype Can be utilized to make the orange flash; orangeFlash is an color-altered PlayerOrange.png
				//batch.begin();
				//batch.draw(orangeFlash, orange.x, orange.y);
				// batch.end();

				//Adds a generic fruit.
				//trail.addFruit();



				iter.remove();
			}
		}
		//Rotates the orange at a speed and direction set by the rotationFactor.
		orangeSprite.rotate(rotationFactor);
	}

	private void spawnFruit() {
		Rectangle fruit = new Rectangle();

		fruit.x = MathUtils.random(0, 800 - 64);
		//Prototype
		fruit.y = MathUtils.random(0, 800-64);;

		fruit.width = 64;
		fruit.height = 64;
		fruitDrops.add(fruit);
		lastSpawnTime = TimeUtils.nanoTime();
	}

	@Override
	public void dispose() {
		grapeImage.dispose();
		orangeImage.dispose();
		collectSound.dispose();
		backroundMusic.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}


