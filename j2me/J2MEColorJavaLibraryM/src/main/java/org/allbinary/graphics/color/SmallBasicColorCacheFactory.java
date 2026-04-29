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
        return SmallBasicColorCacheFactory.instance;
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
        this.BASIC_COLOR_ARRAY[this.colorIndex] = basicDefaultColor;
        this.INDEX_TO_COLOR[this.colorIndex] = basicDefaultColor.intValue();
        colorIndex++;
    }

    public BasicColor getAndOrCreate(final int colorAsInt)
    {
        BasicColor basicColor;
        for(int index = 0; index < this.SIZE; index++) {
            if(this.INDEX_TO_COLOR[index] == colorAsInt) {
                return this.BASIC_COLOR_ARRAY[index];
            }
        }
        
        final int ALPHA_MASK = (int) 0xFF000000;
        
        basicColor = BasicColorFactory.getInstance().createInstanceAN(colorAsInt & ALPHA_MASK, colorAsInt & 0x00FFFFFF, StringUtil.getInstance().EMPTY_STRING);
        this.add(basicColor);

        return basicColor;
    }
}
