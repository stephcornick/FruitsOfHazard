package com.badlogic.fruitsofhazard;

public class LinkedList {

    private Fruit head;
    private Fruit tail;

    LinkedList()
    {
        head = null;
        tail = null;
    }

    public void addFruit(Fruit f)
    {
        if(head==null)
        {
            head = f;
            tail= f;
        }

        else
        {
            tail.setNext(f);
            tail=f;
        }
    }

    public int count()
    {
        Fruit temp = head;
        int nodeList=0;

        while(temp!=null)
        {
            temp = temp.getNext();
            nodeList ++;
        }
        return nodeList;
    }

    public void removeLast()
    {
        Fruit temp = head;
        Fruit previous = temp;

        if(head==null)
        {
            return;
        }

        if(head.getNext()==null)
        {
            //head=null;
            //previous.setNext(null);
            return;
        }

        while(temp.getNext() != null)
        {
            previous = temp;
            temp = temp.getNext();
        }

        previous.setNext(null);

    }

}
