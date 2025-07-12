package org.allbinary.media.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedImageUtil2 {

    private static final BufferedImageUtil2 instance = new BufferedImageUtil2();

    /**
     * @return the instance
     */
    public static BufferedImageUtil2 getInstance() {
        return instance;
    }
    
    private final ImageUtil imageUtil = ImageUtil.getInstance();
    
   public BufferedImage createSpriteImage(
      final BufferedImage[] bufferedImageArray)
   {
      //GraphicsConfiguration gc = this.imageUtil.getDefaultConfiguration();

      int columns = bufferedImageArray.length;
      int max = columns;
      int rows = 0;

      if (bufferedImageArray.length < columns)
      {
         columns = bufferedImageArray.length;
      }

      rows = (bufferedImageArray.length / columns);

      //Extra row for incomplete but needed row
      if (bufferedImageArray.length % columns != 0)
      {
         rows++;
      }

      BufferedImage bufferedImage = this.imageUtil.create(
         bufferedImageArray[0].getWidth(null) * columns,
         bufferedImageArray[0].getHeight(null) * rows);

      Graphics2D g = bufferedImage.createGraphics();
      //g.translate((neww-w)/2, (newh-h)/2);

      int columnIndex = 0;
      int rowIndex = 0;

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         if (index / max != 0 && index % max == 0)
         {
            rowIndex++;
            columnIndex = 0;
         }

         g.drawImage(bufferedImageArray[index],
            bufferedImageArray[index].getWidth(null) * columnIndex,
            bufferedImageArray[index].getHeight(null) * rowIndex,
            bufferedImageArray[index].getWidth(null),
            bufferedImageArray[index].getHeight(null), null);
         //g.drawRenderedImage(image, null);

         columnIndex++;
      }

      g.dispose();

      return bufferedImage;
   }    
}
