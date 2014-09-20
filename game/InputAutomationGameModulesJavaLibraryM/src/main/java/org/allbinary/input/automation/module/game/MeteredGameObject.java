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

import java.awt.image.BufferedImage;

public class MeteredGameObject
      implements MeteredGameObjectInterface
{
   private Double[] ratios;
   
   private boolean good = false;
   private boolean dropping = false;
   
   private Integer minX;
   private Integer maxX;
   private Integer y;
   private Integer size;
   
   public MeteredGameObject(BufferedImage[] bufferedImage, Integer minX, Integer maxX, Integer y)
   throws Exception
   {
      this.setRatios(new Double[bufferedImage.length]);
      
      this.setMinX(minX);
      this.setMaxX(maxX);
      this.setY(y);
      this.setSize(maxX-minX);
      
      for (int index = 0; index < bufferedImage.length; index++)
      {
         double nominator = GraphicsAnalysisUtil.getNominator(
            bufferedImage[index], this.getMinX(), this.getMaxX(), this.getY());

         this.getRatios()[index] = new Double(nominator / this.getSize());
         //LogUtil.put(new Log("hitPointRatios[index]" + hitPointRatios[index], this, "Constructor"));
      }
      
      if (this.getRatios()[bufferedImage.length - 1].doubleValue() > 0.93F)
      {
         this.setGood(true);
      }
      else
      {
         this.setGood(false);
      }
      
      if (this.getRatios()[bufferedImage.length - 1].doubleValue() < this.getRatios()[0].doubleValue())
      {
         this.setDropping(true);
      }
      else
      {
         this.setDropping(false);
      }
   }
   
   public boolean isGood()
   {
      return good;
   }
   
   protected void setGood(boolean good)
   {
      this.good = good;
   }
   
   public boolean isDropping()
   {
      return dropping;
   }
   
   protected void setDropping(boolean dropping)
   {
      this.dropping = dropping;
   }
   
   public Double[] getRatios()
   {
      return this.ratios;
   }

   public String toString()
   {
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append("\nRatios: ");
      for (int index = 0; index < this.getRatios().length; index++)
      {
         stringBuffer.append(this.getRatios()[index]);
         if (index < this.getRatios().length - 1)
         {
            stringBuffer.append(", ");
         }
      }
      
      stringBuffer.append("\n");
      stringBuffer.append("isGood: ");
      stringBuffer.append(this.isGood());
      
      stringBuffer.append("\n");
      stringBuffer.append("isDropping: ");
      stringBuffer.append(this.isDropping());

      return stringBuffer.toString();
   }

   public void setRatios(Double[] ratios)
   {
      this.ratios = ratios;
   }

   public Integer getMinX()
   {
      return minX;
   }

   public void setMinX(Integer minX)
   {
      this.minX = minX;
   }

   public Integer getMaxX()
   {
      return maxX;
   }

   public void setMaxX(Integer maxX)
   {
      this.maxX = maxX;
   }

   public Integer getY()
   {
      return y;
   }

   public void setY(Integer y)
   {
      this.y = y;
   }

   public Integer getSize()
   {
      return size;
   }

   public void setSize(Integer size)
   {
      this.size = size;
   }
   
}
