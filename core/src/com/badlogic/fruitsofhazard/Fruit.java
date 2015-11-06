package com.badlogic.fruitsofhazard;

public class Fruit
{
    private String direction;
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
    }

}

