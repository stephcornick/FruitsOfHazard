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
public class PlayerOrange extends Fruit
{
    private int score;
    private float health;
    private static Texture orangeImage = new Texture(Gdx.files.internal("PlayerOrange.png"));

    PlayerOrange()
    {
        super(orangeImage);
        float fx = 400/ 2 - 64 / 2;
        float fy = 400/ 2 - 64 / 2;
        super.setPosition(fx, fy);
        score = 0;
        health = 3;
    }
    PlayerOrange(int x, int y, int s, int h)
    {
        super(orangeImage, orangeImage.getWidth(), orangeImage.getHeight());
        float fx = (float) x;
        float fy = (float) y;
        super.setPosition(fx, fy);
        score = s;
        health = h;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int newScore)
    {
        score = newScore;
    }

    public float getHealth()
    {
        return health;
    }

    public void setHealth(float newHealth)
    {
        health = newHealth;
    }

}
