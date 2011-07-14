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
package allbinary.media.image.search;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.media.image.ImageUtil;
import allbinary.media.image.comparison.pixel.PixelDelta;
import java.awt.image.BufferedImage;

import java.util.Vector;

public class ImageComparisonSearch
{
   public boolean isSameHeight;
   public boolean isSameWidth;

   public int pixelsThatMatch;
   public int pixelsIgnored;
   
   public int imageHeight;
   public int imageWidth;
   
   private int tolerance;
   
   private Vector nonMatchingPixelVector;
   
   private BufferedImage bufferedImages[];

   private float matchingPercent = -1;
   
   public ImageComparisonSearch(
      BufferedImage bufferedImage,
      BufferedImage bufferedImage2,
      int tolerance)
   {
      this.nonMatchingPixelVector = new Vector();
      
      this.setBufferedImages(new BufferedImage[2]);
      this.getBufferedImages()[0] = bufferedImage;
      this.getBufferedImages()[1] = bufferedImage2;

      this.imageHeight = bufferedImage.getHeight();
      this.imageWidth = bufferedImage.getWidth();
      
      if(bufferedImage.getHeight() != bufferedImage2.getHeight())
      {
          isSameHeight = false;
          
          if(imageHeight > bufferedImage2.getHeight())
          {
              this.imageHeight = bufferedImage2.getHeight();
          }
      }
      else
      {
          isSameHeight = true;
      }

      if(bufferedImage.getWidth() != bufferedImage2.getWidth())
      {
          isSameWidth = false;

          if(imageWidth > bufferedImage2.getWidth())
          {
              this.imageWidth = bufferedImage2.getWidth();
          }
      }
      else
      {
          isSameWidth = true;
      }
      
      if(!isSameWidth || !isSameHeight)
      {
          LogUtil.put(new Log("Images were not the same size? Most likely a resolution change.", this, "Constructor"));
          LogUtil.put(new Log("1: " + ImageUtil.toString(bufferedImage), this, "Constructor"));
          LogUtil.put(new Log("2: " + ImageUtil.toString(bufferedImage2), this, "Constructor"));
      }      
   }
   
   private float getMatchingPercent()
   {
      if(this.matchingPercent == -1)
      {
         this.matchingPercent = (float)pixelsThatMatch / (this.imageWidth * this.imageHeight);
      }

      return this.matchingPercent;
   }
   
   public void add(PixelDelta pixel)
   {
      this.nonMatchingPixelVector.add(pixel);
   }
   
   public Vector getNonMatchingPixelVector()
   {
      return this.nonMatchingPixelVector;
   }
   
   public String toString()
   {
      return "ImageComparisonInfo: " + 
         " Number Of Matching Pixels: " + this.pixelsThatMatch + 
         "\nNumber Of Non-Matching Pixels: " + this.getNonMatchingPixelVector().size() + 
          "\nNumber Of Pixels Ignored: " + this.pixelsIgnored + 
         "\nMatching Percentage: " + this.getMatchingPercent();
   }

   public BufferedImage[] getBufferedImages()
   {
      return bufferedImages;
   }

   private void setBufferedImages(BufferedImage[] bufferedImages)
   {
      this.bufferedImages = bufferedImages;
   }

    public int getTolerance()
    {
        return tolerance;
    }

    public void setTolerance(int tolerance)
    {
        this.tolerance = tolerance;
    }
}
