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
package org.allbinary.media.image.analysis;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.graphics.color.ColorCacheFactory;
import org.allbinary.graphics.color.ColorCacheable;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;

public class ImageAnalysis
{

   private ImageAnalysis()
   {
   }

   public static ImageAnalysisResults[] process(BufferedImage[] bufferedImageArray, ColorRangeInterface colorRangeInterface) throws Exception
   {
      LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + colorRangeInterface.toString(), "ImageAnalysis", CommonStrings.getInstance().PROCESS));

      ImageAnalysisResults[] imageAnalysisResultsArray = new ImageAnalysisResults[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         imageAnalysisResultsArray[index] = ImageAnalysis.process(bufferedImageArray[index], colorRangeInterface);
      }
      return imageAnalysisResultsArray;
   }

   public static ImageAnalysisResults process(BufferedImage bufferedImage, ColorRangeInterface colorRangeInterface) throws Exception
   {
      ImageAnalysisResults imageAnalysisResults = new ImageAnalysisResults();
      long redTotal = 0;
      long greenTotal = 0;
      long blueTotal = 0;

      for (int indexY = 0; indexY < bufferedImage.getHeight(); indexY++)
      {
         for (int indexX = 0; indexX < bufferedImage.getWidth(); indexX++)
         {

            Integer keyInteger = Integer.valueOf(bufferedImage.getRGB(indexX, indexY));

            ColorCacheable colorCacheable = (ColorCacheable) ColorCacheFactory.getInstance().get(keyInteger);

            Color color = colorCacheable.getColor();

            processColorRangeResults(imageAnalysisResults, colorRangeInterface, color);

            processImageColorResults(imageAnalysisResults.getImageColorResults(), colorRangeInterface, color);

            redTotal += color.getRed();
            greenTotal += color.getGreen();
            blueTotal += color.getBlue();
         }
      }

      long totalPixels = imageAnalysisResults.getImageColorRangeResults().getTotalPixelsChecked();

      imageAnalysisResults.getImageColorResults().getColorAverage().setAvgRed((float) redTotal / totalPixels);
      imageAnalysisResults.getImageColorResults().getColorAverage().setAvgGreen((float) greenTotal / totalPixels);
      imageAnalysisResults.getImageColorResults().getColorAverage().setAvgBlue((float) blueTotal / totalPixels);

      return imageAnalysisResults;
   }

   private static void processColorRangeResults(ImageAnalysisResults imageAnalysisResults, ColorRangeInterface colorRangeInterface, Color color)
   {
      if (colorRangeInterface.isInRange(color))
      {
         imageAnalysisResults.getImageColorRangeResults().addMatchingPixelsChecked();
      } else
      {
         // LogUtil.put(LogFactory.getInstance("Invalid Color: " + color, "ImageAnalysis", "process"));
      }
      imageAnalysisResults.getImageColorRangeResults().addTotalPixelsChecked();
   }

   private static void processImageColorResults(ImageColorResults imageColorResults, ColorRangeInterface colorRangeInterface, Color color)
   {
      if (color.getRed() < imageColorResults.getColorRange().getMinRed())
      {
         imageColorResults.getColorRange().setMinRed(color.getRed());
      }

      if (color.getGreen() < imageColorResults.getColorRange().getMinGreen())
      {
         imageColorResults.getColorRange().setMinGreen(color.getGreen());
      }

      if (color.getBlue() < imageColorResults.getColorRange().getMinBlue())
      {
         imageColorResults.getColorRange().setMinBlue(color.getBlue());
      }

      if (color.getRed() > imageColorResults.getColorRange().getMaxRed())
      {
         imageColorResults.getColorRange().setMaxRed(color.getRed());
      }

      if (color.getGreen() > imageColorResults.getColorRange().getMaxGreen())
      {
         imageColorResults.getColorRange().setMaxGreen(color.getGreen());
      }

      if (color.getBlue() > imageColorResults.getColorRange().getMaxBlue())
      {
         imageColorResults.getColorRange().setMaxBlue(color.getBlue());
      }
   }
}
