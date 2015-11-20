package com.badlogic.fruitsofhazard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.NumberUtils;
/**
 * Created by s0928018 on 11/6/15.
 */
public class Grape extends CollectorFruit
{
    private static Texture grapeImage = new Texture(Gdx.files.internal("Grape.png"));
    Grape()
    {
        super(grapeImage,0,0,100);
    }

    Grape(int x, int y)
    {
        super(grapeImage, x, y, 100);
    }
}
