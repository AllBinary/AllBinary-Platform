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
package org.allbinary.media.image;

import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import org.allbinary.media.image.ImageUtil;

public class BufferedImageUtil {

   public static BufferedImage create(int width, int height)
   {
      final GraphicsConfiguration graphicsConfiguration = ImageUtil.getDefaultConfiguration();
      return graphicsConfiguration.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
   }
}
