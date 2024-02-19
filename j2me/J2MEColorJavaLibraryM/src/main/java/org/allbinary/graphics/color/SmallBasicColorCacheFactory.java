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
package org.allbinary.graphics.color;

import org.allbinary.logic.string.StringUtil;

public class SmallBasicColorCacheFactory
{
    private static final SmallBasicColorCacheFactory instance = new SmallBasicColorCacheFactory();

    public static SmallBasicColorCacheFactory getInstance()
    {
        return instance;
    }

    private final int SIZE = 255;
    private final int[] INDEX_TO_COLOR = new int[SIZE];
    private final BasicColor[] BASIC_COLOR_ARRAY = new BasicColor[SIZE];
    
    private int colorIndex = 0;

    private SmallBasicColorCacheFactory()
    {
    }

    public void add(final BasicColor basicDefaultColor)
    {
        BASIC_COLOR_ARRAY[colorIndex] = basicDefaultColor;
        INDEX_TO_COLOR[colorIndex] = basicDefaultColor.intValue();
        colorIndex++;
    }

    public BasicColor getInstance(final int colorAsInt)
    {
        BasicColor basicColor;
        for(int index = 0; index < SIZE; index++) {
            if(INDEX_TO_COLOR[index] == colorAsInt) {
                return BASIC_COLOR_ARRAY[index];
            }
        }
        
        basicColor = new BasicColor(((colorAsInt >> 24) & 255), colorAsInt, StringUtil.getInstance().EMPTY_STRING);
        this.add(basicColor);

        return basicColor;
    }
}
