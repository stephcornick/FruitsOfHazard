package com.badlogic.fruitsofhazard;

import com.badlogic.gdx.graphics.g2d.Sprite;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.NumberUtils;


public class Fruit extends Sprite
{

    public Fruit()
    {
        super();
    }

    public Fruit(Texture texture)
    {
        super(texture);
    }

    public Fruit(Texture texture, int srcWidth, int srcHeight)
    {
        super(texture, srcWidth, srcHeight);
    }

    public Fruit(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight)
    {
        super(texture, srcX, srcY, srcWidth, srcHeight);
    }

    public Fruit(TextureRegion region)
    {
        super(region);
    }

    public Fruit(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight)
    {
        super(region, srcX, srcY, srcWidth, srcHeight);
    }

    public Fruit(Sprite sprite)
    {
        super(sprite);
    }

   /* private String direction;
    private float xChord;
    private float yChord;
    //some kind of image variable should go here

    private Fruit next;

    Fruit()
    {
        xChord = 0;
        yChord = 0;
        next = null;
    }

    Fruit(float x, float y)
    {
        xChord = x;
        yChord = y;
        next = null;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String dir)
    {
        direction = dir;
    }

    public float getX()
    {
        return xChord;
    }

    public float getY()
    {
        return yChord;
    }

    public void setX(float x)
    {
        xChord = x;
    }

    public void setY(float y)
    {
        yChord = y;

    }

    public Fruit getNext()
    {
        return next;
    }

    public void setNext(Fruit f)
    {
        next = f;
    }*/

}

