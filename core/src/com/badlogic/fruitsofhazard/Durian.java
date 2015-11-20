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
public class Durian extends HealthFruit
{
    private Texture durianImage = new Texture(Gdx.files.internal("Durian.png"));
    Durian()
    {
        super(durianImage, 0, 0, -1);
    }

    Durian(float x, float y)
    {
        super(durianImage, x, y, -1);
    }
}
