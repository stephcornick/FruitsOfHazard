
package com.badlogic.fruitsofhazard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class EndScreen implements Screen {

    private Texture texture;
    final FruitsofHazard game;
    private int score;
    OrthographicCamera camera;

    public EndScreen(final FruitsofHazard gam, int myScore)
    {
        game = gam;
        score = myScore;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        if (score < 1000)
        {
          texture = new Texture(Gdx.files.internal("End Screen level 0.png"));
        }
        else if (score >= 1000 && score < 3000)
        {
          texture = new Texture(Gdx.files.internal("End Screen level 1.png")); 
        }
        else if (score >= 3000 && score < 7000)
        {
          texture = new Texture(Gdx.files.internal("End Screen level 2.png")); 
        }
        else if (score >= 7000 && score < 10000)
        {
          texture = new Texture(Gdx.files.internal("End Screen level 3.png")); 
        }
        else if (score >= 10000 && score < 15000)
        {
          texture = new Texture(Gdx.files.internal("End Screen level 4.png")); 
        }
        else if (score >= 15000)
        {
          texture = new Texture(Gdx.files.internal("End Screen level 5.png")); 
        }


    }

    @Override
    //TO-DO: implement selection for menu items
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(texture, 0, 0, 800, 480, 0, 0, 1500, 1050, false, false);

        //prints the score and a return to menu message
        game.font.draw(game.batch, "" + score, 105, 200);
        game.font.draw(game.batch, "Press Space to Return to Main Menu", 550, 100);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
    }
}
