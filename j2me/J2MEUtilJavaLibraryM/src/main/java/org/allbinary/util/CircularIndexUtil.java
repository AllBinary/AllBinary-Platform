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
import org.allbinary.string.CommonLabels;

public class CircularIndexUtil
{
    public static final CircularIndexUtil NULL_CIRCULAR_INDEX_UTIL = new CircularIndexUtil(0, 0);

    private int index = 0;
    private int lastIndex = 0;
    private int size = 0;
    
    private CircularIndexUtil(int index, int size)
    {
        this.setSize(size);
        this.setIndex(index);
    }

//    private CircularIndexUtil(int size)
//    {
//        this.setSize(size);
//    }
    
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
    
    public static CircularIndexUtil createInstance(int max)
    {
        return new CircularIndexUtil(0, max);
    }

    public static CircularIndexUtil createInstanceAt(int index, int max)
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
        return this.size;
    }
    
    public String toString() {
        return new StringMaker().append(CommonLabels.getInstance().INDEX_LABEL).appendint(this.index).append("lastIndex: ").appendint(this.lastIndex).append("size: ").appendint(this.size).toString();
    }
}
