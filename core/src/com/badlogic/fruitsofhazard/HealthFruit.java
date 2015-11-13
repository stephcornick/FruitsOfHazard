package com.badlogic.fruitsofhazard;

/**
 * Created by s0928018 on 11/6/15.
 */
public class HealthFruit extends Fruit
{
    private float value;

    HealthFruit()
    {
        super();
        value = 0;
        //some kind of default image
    }

    HealthFruit(float x, float y, float v)
    {
        super(x,y);
        value = v;
        //some kind of default image
    }

    public float getValue()
    {
        return value;
    }

    public void setValue(float newValue)
    {
        value = newValue;
    }
}
