package com.badlogic.fruitsofhazard;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.math.MathUtils;
        import com.badlogic.gdx.utils.TimeUtils;

public class MainMenuScreen implements Screen {

            private Texture texture;
            final FruitsofHazard game;
            private int select;
            private Sprite startIcon;
            private Sprite instructionIcon;
            private Sprite quitIcon;
            OrthographicCamera camera;

            public MainMenuScreen(final FruitsofHazard gam)
               {
                   game = gam;
                   camera = new OrthographicCamera();
                   camera.setToOrtho(false, 800, 480);
                   texture = new Texture(Gdx.files.internal("Menu.png"));

                   Texture orangeIcon = new Texture(Gdx.files.internal("PlayerOrange.png"));
                   startIcon = new Sprite(orangeIcon, orangeIcon.getWidth(), orangeIcon.getHeight());
                   startIcon.setPosition(230, 160);

                   Texture peachIcon = new Texture(Gdx.files.internal("Peach.png"));
                   instructionIcon = new Sprite(peachIcon, peachIcon.getWidth(), peachIcon.getHeight());
                   instructionIcon.setPosition(230, 95);

                   Texture bananaIcon = new Texture(Gdx.files.internal("Banana.png"));
                   quitIcon = new Sprite(bananaIcon, bananaIcon.getWidth(), bananaIcon.getHeight());
                   quitIcon.setPosition(230, 30);

                   select = 1;
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

                    startIcon.draw(game.batch);

                    instructionIcon.draw(game.batch);

                    quitIcon.draw(game.batch);

                    game.batch.end();

                    if(select == 1)
                    {
                        startIcon.setPosition(230, 160);
                        int num = MathUtils.random(-1, 1);
                        startIcon.setPosition(startIcon.getX(), startIcon.getY() + (num * 5));

                        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                        {
                            game.setScreen(new GameScreen(game));
                            dispose();
                        }
                        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
                        {
                            moveSelectionUp();
                            startIcon.setPosition(230, 160);
                        }
                        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                        {
                            moveSelectionDown();
                            startIcon.setPosition(230, 160);
                        }
                    }
                    else if(select == 2)
                    {
                        instructionIcon.setPosition(230, 95);
                        int num = MathUtils.random(-1, 1);
                        instructionIcon.setPosition(instructionIcon.getX(), instructionIcon.getY() + (num * 5));

                        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                        {
                            game.setScreen(new InstructionsScreen(game));
                            dispose();
                        }
                        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
                        {
                            moveSelectionUp();
                            instructionIcon.setPosition(230, 95);
                        }
                        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                        {
                            moveSelectionDown();
                            instructionIcon.setPosition(230, 95);
                        }
                    }
                    else if(select == 3)
                    {
                        quitIcon.setPosition(230, 30);
                        int num = MathUtils.random(-1, 1);
                        quitIcon.setPosition(quitIcon.getX(), quitIcon.getY() + (num * 5));

                        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                        {
                            System.exit(0);
                        }
                        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
                        {
                            moveSelectionUp();
                            quitIcon.setPosition(230, 30);
                        }
                        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                        {
                            moveSelectionDown();
                            quitIcon.setPosition(230, 30);
                        }
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
    //A method to handle moving up through the menu options
        public void moveSelectionUp()
        {
            if(select == 1)
            {
                select = 3;
            }
            else if(select == 2)
            {
                select = 1;
            }
            else if(select == 3)
            {
                select = 2;
            }
        }
    //A method to handle moving down through the menu options
        public void moveSelectionDown()
        {
            if(select == 1)
            {
                select = 2;
            }
            else if(select == 2)
            {
                select = 3;
            }
            else if(select == 3)
            {
                select = 1;
            }
        }
    }
