
package com.badlogic.fruitsofhazard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class InstructionsScreen implements Screen {

    private Texture texture;
    final FruitsofHazard game;
    OrthographicCamera camera;

    public InstructionsScreen(final FruitsofHazard gam)
    {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        texture = new Texture(Gdx.files.internal("Instructions Screen.png")); //will make a new background for instructions

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

        //TO-DO: implement a "return to menu" button
        game.font.draw(game.batch, "Press Space to Return to Main Menu", 100, 100);
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
