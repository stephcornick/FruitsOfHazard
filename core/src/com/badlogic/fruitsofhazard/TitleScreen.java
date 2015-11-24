package com.badlogic.fruitsofhazard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class TitleScreen implements Screen {

    private Texture texture;
    final FruitsofHazard game;
    OrthographicCamera camera;

    public TitleScreen(final FruitsofHazard gam)
    {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        texture = new Texture(Gdx.files.internal("Title screen.png"));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(texture, 0, 0, 800, 480, 0, 0, 1500, 1050, false, false);
        game.font.draw(game.batch, "Fruits of Hazard ", 100, 150);
        game.font.draw(game.batch, "Tap Anywhere", 100, 100);
        game.batch.end();

        if (Gdx.input.justTouched()) {
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

