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
package org.allbinary.input.automation.module.game;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.allbinary.graphics.color.ColorCacheFactory;
import org.allbinary.graphics.color.ColorCacheable;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;

/**
 *
 * @author Admin
 */
public class GraphicsAnalysisUtil
{
   private static final int MAX = 122;
   
   private GraphicsAnalysisUtil()
   {
   }
   
   public static double getNominator(BufferedImage bufferedImage, 
         Integer min_x, Integer max_x, Integer y) throws Exception
   {
      for (int index = max_x.intValue(); index > min_x.intValue(); index -= 1)
      {
         Integer colorInteger = Integer.valueOf(bufferedImage.getRGB(index, y.intValue()));
         AutomaticCacheInterface automaticCacheInterface = ColorCacheFactory.getInstance();

         ColorCacheable colorCacheable = (ColorCacheable) automaticCacheInterface.get(colorInteger);
         Color color = colorCacheable.getColor();

         //logUtil.put(new StringBuilder().append("Color: ").append(color).append(" at: ").append(index).append(CommonSeps.getInstance().COMMA).append(y).toString(), this, "getNominator");
         //If it is dark
         if (color.getRed() < MAX && color.getGreen() < MAX && color.getBlue() < MAX)
         {
            //Color is black continue
         } else
         {
            return index - min_x.intValue();
         }
      }
      return 0;
   }
   
}
