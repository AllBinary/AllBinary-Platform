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
package allbinary.media.image;

import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class BufferedImageUtil {

   public static BufferedImage create(int width, int height)
   {
      GraphicsConfiguration gc = ImageUtil.getDefaultConfiguration();
      return gc.createCompatibleImage(
         width, height, 
         Transparency.TRANSLUCENT);
   }
}
