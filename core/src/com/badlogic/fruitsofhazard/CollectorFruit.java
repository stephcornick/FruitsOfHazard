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
public class CollectorFruit extends Fruit
{
    private int value;

    CollectorFruit()
    {
        super();
        value = 0;
    }

    CollectorFruit(Texture texture, int x, int y, int v)
    {
        super(texture, texture.getWidth(), texture.getHeight());
        float fx = (float) x;
        float fy = (float) y;
        super.setPosition(fx, fy);
        value = v;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int newValue)
    {
        value = newValue;
    }
}
