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
package org.allbinary.image.gui;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessorInput {

    private BufferedImage bufferedImageArray[];
    private File[] files;

    public ImageProcessorInput(File[] files, BufferedImage bufferedImageArray[]) throws Exception {
        super();

        this.bufferedImageArray = bufferedImageArray;
        this.files = files;
    }

   public BufferedImage[] getBufferedImageArray()
   {
      return bufferedImageArray;
   }

   public File[] getFiles()
   {
      return files;
   }
}
