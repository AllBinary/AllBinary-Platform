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
            /*
             *Copyright (c) 2007 All Binary
             *All Rights Reserved.
             *Don't Duplicate or Distributed.
             *Trade Secret Information
             *For Internal Use Only
             *Confidential
             *Unpublished
             *
             *@author Travis Berthelot
             *Date: April 21, 2007, 2:16 AM
             *
             *Modified By         When       ?
             *
             */

package allbinary.graphics.color;

import com.abcs.logic.util.cache.CacheableInterface;
import java.awt.Color;

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
