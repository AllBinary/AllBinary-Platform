/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.util;

import org.allbinary.logic.string.StringMaker;

public class CircularIndexUtil
{
    private int index = 0;
    private int lastIndex = 0;
    private int size = 0;
    
    private CircularIndexUtil(int index, int size)
    {
        this.setSize(size);
        this.setIndex(index);
    }

    private CircularIndexUtil(int size)
    {
        this.setSize(size);
    }
    
    public int next()
    {
        if (this.index >= this.lastIndex)
        {
            this.index = 0;
        }
        else
        {
            this.index++;
        }
        return this.index;
    }

    public int previous()
    {
        if (this.index < 1)
        {
            this.index = this.lastIndex;
        }
        else
        {
            this.index--;
        }
        return this.index;
    }
    
    public void setIndex(int index)
    {
        if (index > this.lastIndex)
        {
            return;
        }

        this.index = index;
    }
    
    public int getIndex()
    {
        return this.index;
    }
    
    public static CircularIndexUtil getInstance(int max)
    {
        return new CircularIndexUtil(max);
    }

    public static CircularIndexUtil getInstance(int index, int max)
    {
        return new CircularIndexUtil(index, max);
    }

    public void setSize(int size)
    {
        this.size = size;
        this.lastIndex = size - 1;
        this.index = 0;
    }

    public int getSize()
    {
        return size;
    }
    
    public String toString() {
        return new StringMaker().append("index: ").append(this.index).append("lastIndex: ").append(this.lastIndex).append("size: ").append(this.size).toString();
    }
}
