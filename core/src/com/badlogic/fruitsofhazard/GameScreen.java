package com.badlogic.fruitsofhazard;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


/**
 * Created by s0915270 on 11/23/15.
 */
public class GameScreen implements Screen {

         final FruitsofHazard game;

        //Implement pause/resume
        public enum State
        {
            PAUSE,
            RUN,

        }
        State state = State.RUN;


        //Prototype The head of the orange.
        private Sound collectSound; //holds the sound to be played on fruit collection
        private Music backroundMusic; //holds the background music
        private OrthographicCamera camera; //the camera
        private SpriteBatch batch; //the sprite batch
        private PlayerOrange orange; //the orange sprite that the player controls
        private Array<Fruit> gameFruits; //an array of fruits in the game
        private DoublyLinkedList<CollectorFruit> fruitTrail; //the trail of collected fruits that will follow the orange
        private long lastSpawnTime; //counter that keeps track of the time between fruit spawning

        //Project 1
        //Reporposed FPS display to display score instead -Mike
        private String msg;
        BitmapFont font;

        //Prototype Sets a constant movement direction vertically, positive is up, negative down.
        private float vertical = 0;

        //Prototype Sets a constant movement direction horizontally, positive is left, negative right.
        private float horizontal = 5;

        //a factor to hold the changing speed
        private float speedFactor = 5;

        //Prototype Sets the spinning speed of the orange. Negative values spin the orange clockwise, Positive counter.
        private float rotationFactor = -5;
        private float rotationFactorSecond = 5;


    public GameScreen(final FruitsofHazard gam)
    {
        this.game = gam;

        //initializing the font
        font = new BitmapFont();

        //Prototype can be utilized to make the orange flash/change momentarily.
        //orangeFlash = new Texture(Gdx.files.internal("orangeFlash.png"));

        //Producing a sprite for the orange.
        orange = new PlayerOrange();

        //Prototype fruit collection sound, smb_coin.wav
        collectSound = Gdx.audio.newSound(Gdx.files.internal("smb_coin.wav"));

        //Prototype backround music, BackgroundMusic.mp3
        backroundMusic = Gdx.audio.newMusic(Gdx.files.internal("BackgroundMusic.mp3"));

        //Sets background music to play at startup and loop constantly.
        backroundMusic.setLooping(true);
        backroundMusic.play();

        //Creates a Camera.
        camera = new OrthographicCamera();

        //Prototype screen size test
        //camera.setToOrtho(false, 800, 480);
        //camera.setToOrtho(false, horizontalScreenSize, verticalScreenSize);

        //Sets the camera size.
        camera.setToOrtho(false, 1200, 660);

        batch = new SpriteBatch();
        batch.begin();

        gameFruits = new Array<Fruit>();
        fruitTrail = new DoublyLinkedList<CollectorFruit>();

        /*Grape g = new Grape();
        CollectorFruit cc = (CollectorFruit) g;
        fruitTrail.addFirst(cc);
        fruitTrail.addFirst(cc);*/

        //UNIMPLEMENTED: Alter to spawn different kinds of fruit.
        spawnFruit();
        batch.end();

    }

    private void spawnFruit() {

        //randomly selects x and y coordinates on intervals of 50 pixels
        int spawnX = MathUtils.random(0, ((1200-50)/50)) * 50;
        int spawnY = MathUtils.random(1, ((660-100)/50)) * 50;

        //random number to determine which fruit to spawn, weighted by rarity
        int whichFruit = MathUtils.random(0, 16);
		/*
		0 = Banana (rarest)
		1-2 = Peach (second rarest)
		3-4 = Durian (roughly as much as Peach)
		5-6 = OrangeSlice (roughly as much as Peach)
		7-10 = Lemon (third rarest)
		11-16 = Grape (least rare)
		* */

        //creates the chosen type of fruit and adds it to the array
        if(whichFruit == 0)
        {
            Banana fruit = new Banana(spawnX, spawnY);
            gameFruits.add(fruit);
        }
        else if(whichFruit >= 1 && whichFruit <= 2)
        {
            Peach fruit = new Peach(spawnX, spawnY);
            gameFruits.add(fruit);
        }
        else if(whichFruit >= 3 && whichFruit <= 4)
        {
            Durian fruit = new Durian(spawnX, spawnY);
            gameFruits.add(fruit);
        }
        else if(whichFruit >= 5 && whichFruit <= 6)
        {
            OrangeSlice fruit = new OrangeSlice(spawnX, spawnY);
            gameFruits.add(fruit);
        }
        else if(whichFruit >= 7 && whichFruit <= 10)
        {
            Lemon fruit = new Lemon(spawnX, spawnY);
            gameFruits.add(fruit);
        }
        else if(whichFruit >= 11 && whichFruit <= 16)
        {
            Grape fruit = new Grape(spawnX, spawnY);
            gameFruits.add(fruit);
        }

       //resets the spawn counter
        lastSpawnTime = TimeUtils.nanoTime();
    }

   /* public void addNewTurn(String newTurn)
    {
        for(int i=0; i<turns.size; i++)
        {
            String temp = turns.get
        }
    }*/

        //@Override
        public void render (float delta) {
            //Prototype Background Color; RGB? Gets color by dividing the color number by 255f.
            //Guide to change background color: R/255. G/255f, B/255f
            Gdx.gl.glClearColor(255/255f, 190/255f, 125/255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();

            batch.setProjectionMatrix(camera.combined);

            if(Gdx.input.isKeyJustPressed(Keys.SPACE))
            {
                if(state == State.RUN)
                {
                    pause();
                }
                else
                {
                    resume();
                }
            }

            batch.begin();

            //Continuously redraws the orange sprite; if there is a non-zero rotationFactor, it will spin.

            orange.setPosition(orange.getX(), orange.getY());
            orange.draw(batch);

            //redraw the fruit trail to follow the orange
            if(fruitTrail.isEmpty() == false)
            {
                Iterator<CollectorFruit> iterC = fruitTrail.iterator();
                int xx = 1200 - (fruitTrail.size() * 50);
                int yy = 660 - 50;
                while(iterC.hasNext())
                {
                    CollectorFruit c = iterC.next();
                    c.draw(batch);
                    c.setPosition(xx, yy);
                    xx = xx + 50;
                }
            }
            //Sets FPS font color
            font.setColor(5.0f,5.0f,5.0f,5.0f);

            //redraw each fruit in the array every frame
            for(Fruit fruit: gameFruits)
            {
                fruit.draw(batch);
            }


            batch.end();

            switch(state)
            {
                case RUN:

                    batch.begin();

                    //draws the score, health, and pause
                    msg = "Score: " + orange.getScore() + "\nHealth: " + orange.getHealth() + "\nPress [space] to pause";
                    font.draw(batch, msg, 20, 660);
                    batch.end();

                    //Left key single-press movement.
                    if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    {
                        //Sets the orange to move left at a specified rate.
                        horizontal =-1 * speedFactor;
                        vertical = 0;

                        //Sets orange rotation to mirror left movement.
                        rotationFactor = rotationFactorSecond;
                    }

                    //Right key single-press movement
                    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    {
                        //Sets the orange to move right at a specified rate.
                        horizontal = speedFactor;
                        vertical = 0;

                        //Sets orange rotation to mirror right movements.
                        rotationFactor = -1 * rotationFactorSecond;
                    }

                    //Up key single-press movement
                    if(Gdx.input.isKeyPressed(Input.Keys.UP))
                    {
                        //Sets the orange to move up at a specified rate.
                        horizontal = 0;
                        vertical = speedFactor;

                        //Sets orange rotation to mirror an upward movement
                        rotationFactor = -1 * rotationFactorSecond;
                    }

                    //Down key single-press movement
                    if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                    {
                        //Sets the orange to move down at a specified rate.
                        horizontal = 0;
                        vertical = -1 * speedFactor;

                        //Sets orange rotation to mirror a downward movement
                        rotationFactor = rotationFactorSecond;
                    }

                    //Moves the orange horizontally and vertically at a constant rate
                    orange.setPosition(orange.getX() + horizontal, orange.getY() + vertical);

                    //Prototype Right-to-Left boundary; moves orange to left end if it collides with right boundary
                    if(orange.getX() > 1200 - 50)
                    {
                        game.setScreen(new EndScreen(game, orange.getScore()));
                        dispose();
                    }
                    //Solid Boundary; if(orange.x < 0) orange.x = 0;

                    //Prototype Left-to-Right boundary; moves orange to right end if it collides with left boundary
                    if(orange.getX() < 0)
                    {
                        game.setScreen(new EndScreen(game, orange.getScore()));
                        dispose();
                    }
                    //Solid Boundary; if(orange.x > 800 - 50) orange.x = 800 - 50;


                    //Prototype Lower-to-Upper boundary; moves orange to upper end if it collides with lower boundary
                    if(orange.getY() < 0)
                    {
                        game.setScreen(new EndScreen(game, orange.getScore()));
                        dispose();
                    }
                    //Solid boundary; if(orange.y < 0) orange.y = 0;

                    //Prototype Upper-to-Lower boundary; moves orange to Lower end if it collides with upper boundary
                    if(orange.getY() > 660 - 50)
                    {
                        game.setScreen(new EndScreen(game, orange.getScore()));
                        dispose();
                    }
                    //Solid boundary; if(orange.y > 800- 450) orange.y = 800 - 450;

                    //Prototype increasing spawn rate by reducing delay
                    if(TimeUtils.nanoTime() - lastSpawnTime > 1600000000) spawnFruit();

                    //Removes fruit that get run over and handles what to do in different situations
                    Iterator<Fruit> iter = gameFruits.iterator();
                    while(iter.hasNext())
                    {

                        Fruit nextFruit = iter.next();
                        Rectangle fruitSquare= nextFruit.getBoundingRectangle();

                        if (fruitSquare.overlaps(orange.getBoundingRectangle())) {
                            collectSound.play();
                            if (nextFruit instanceof CollectorFruit)
                            {
                                CollectorFruit cf = (CollectorFruit) nextFruit;

                                if(cf.getIsInList() == true)
                                {
                                    game.setScreen(new EndScreen(game, orange.getScore()));
                                    dispose();
                                }
                                else
                                {
                                    fruitTrail.addLast(cf);
                                    cf.setIsInList(true);

                                    if(fruitTrail.size() >= 30)
                                    {
                                        game.setScreen(new EndScreen(game, orange.getScore()));
                                        dispose();
                                    }

                                    CollectorFruit one = fruitTrail.last();
                                    CollectorFruit two = fruitTrail.secondLast();
                                    CollectorFruit three = fruitTrail.thirdLast();
                                    if(one == null || two == null || three == null)
                                    {
                                        orange.setScore(orange.getScore() + cf.getValue());
                                    }
                                    else
                                    {
                                        if (one.getValue() == two.getValue() && one.getValue() == three.getValue())
                                        {
                                            orange.setScore(orange.getScore() + (cf.getValue() * 3));
                                        }
                                        else
                                        {
                                            orange.setScore(orange.getScore() + cf.getValue());
                                        }
                                    }

                                }

                                speedFactor = speedFactor + (float) 0.5;
                                rotationFactorSecond = rotationFactorSecond + (float) 0.5;

                            }
                            else if (nextFruit instanceof HealthFruit)
                            {//if the orange runs into a health fruit, the value is added to health
                                HealthFruit hf = (HealthFruit) nextFruit;
                                orange.setHealth(orange.getHealth() + hf.getValue());
                                if(orange.getHealth() <= 0)
                                {//if health hits zero, game over
                                    game.setScreen(new EndScreen(game, orange.getScore()));
                                    dispose();
                                }
                                else if (orange.getHealth() >= 7)
                                {//health is limited to 7
                                    orange.setHealth(7);
                                }
                            }

                            iter.remove();
                        }
                    }
                    //Rotates the orange at a speed and direction set by the rotationFactor.
                    orange.rotate(rotationFactor);
                    break;
                case PAUSE:
                    //shows score, health, unpause, and return to menu
                    msg = "Score: " + orange.getScore() + "\nHealth: " + orange.getHealth() + "\nPress [space] to unpause\nPress Q to return to main menu";
                    batch.begin();
                    font.draw(batch, msg, 20,660);
                    batch.end();

                    if(Gdx.input.isKeyPressed(Keys.Q))
                    {//return to the main menu if Q is pressed
                        game.setScreen(new MainMenuScreen(game));
                        dispose();
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public void dispose() {
            collectSound.dispose();
            backroundMusic.dispose();
            batch.dispose();
        }

        @Override
        public void resize(int width, int height) {
        }

        //TO-DO: pause on spacebar
        @Override
        public void pause()
        {
            this.state = State.PAUSE;
        }

        //TO-DO: unpause on spacebar
        @Override
        public void resume()
        {
            this.state = State.RUN;
        }

        //implement pause/resume
        public void setGameState(State s)
        {
            this.state = s;
        }


       public void show()
       {

       }

     public void hide()
     {

     }
}


