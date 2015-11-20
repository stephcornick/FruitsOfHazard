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
    private int health;
    private Texture orangeImage = new Texture(Gdx.files.internal("PlayerOrange.png"));

    PlayerOrange()
    {
        super(orangeImage);
        score = 0;
        health = 3;
    }
    PlayerOrange(int x, int y, int s, int h)
    {
        super(orangeImage, x, y, orangeImage.getWidth(), orangeImage.getHeight());
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

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int newHealth)
    {
        health = newHealth;
    }

}
