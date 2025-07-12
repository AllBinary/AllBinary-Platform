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

import java.awt.*;

import org.allbinary.logic.util.cache.CacheableInterface;

public class ColorCacheable
    implements CacheableInterface
{    
    private Object key;
 
    private Color color;

    public ColorCacheable(Object key)
    {
        this.key = key;
        this.color = new Color(((Integer) key).intValue());
    }
    
    public Object getKey()
    {
        return this.key;
    }
    
    public Color getColor()
    {
       return this.color;
    }
    
    public void setColor(Color color)
    {
       this.color = color;
    }
}
