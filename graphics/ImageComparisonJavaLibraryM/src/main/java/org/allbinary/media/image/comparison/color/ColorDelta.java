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
package org.allbinary.media.image.comparison.color;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.cache.CacheableInterface;

public class ColorDelta
    implements CacheableInterface
{
   private int rgb1;
   private int rgb2;

   private Object key;
   
   public ColorDelta(int rgb1, int rgb2)
   {
      this.setRgb1(rgb1);
      this.setRgb2(rgb2);
      this.key = ColorDelta.getKey(rgb1, rgb2);
   }

    public Object getKey()
    {
        return key;
    }    

    public static Object getKey(int rgb1, int rgb2)
    {
        return new StringMaker().append(Integer.toString(rgb1)).append(CommonSeps.getInstance().UNDERSCORE).append(Integer.toString(rgb2)).toString();
    }
    
   public int getRgb1()
   {
      return rgb1;
   }

   protected void setRgb1(int rgb1)
   {
      this.rgb1 = rgb1;
   }

   public int getRgb2()
   {
      return rgb2;
   }

   protected void setRgb2(int rgb2)
   {
      this.rgb2 = rgb2;
   }
   
   public String toString()
   {
       return new StringMaker().append("ColorDelta: ").append(this.getKey()).append(" RGB1: ").append(this.getRgb1()).append(" RGB2: ").append(this.getRgb2()).toString();
   }
}
