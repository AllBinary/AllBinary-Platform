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
package allbinary.input.automation.module.game;

import java.awt.image.BufferedImage;

public class HitPointGameObject extends MeteredGameObject
      implements HitPointGameObjectInterface
{
   private boolean damaged = false;
   private boolean closeDeath = false;
   
   public HitPointGameObject(BufferedImage[] bufferedImage, Integer minX, Integer maxX, Integer y)
   throws Exception
   {
      super(bufferedImage, minX, maxX, y);
      
      if (this.getRatios()[bufferedImage.length - 1].doubleValue() < 1.0F)
      {
         this.setDamaged(true);
      } else
      {
         this.setDamaged(false);
      }
      
      if (this.getRatios()[bufferedImage.length - 1].doubleValue() > 0.25F)
      {
         this.closeDeath = false;
      } else
      {
         this.closeDeath = true;
      }
   }
      
   public boolean isMoreThan(float aFloat)
   {
      if (this.getRatios()[this.getRatios().length - 1].doubleValue() > aFloat)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean isCloseDeath()
   {
      return closeDeath;
   }
   
   protected void setCloseDeath(boolean closeDeath)
   {
      this.closeDeath = closeDeath;
   }
   
   public boolean isDamaged()
   {
      return damaged;
   }
   
   public void setDamaged(boolean damaged)
   {
      this.damaged = damaged;
   }
      
   public String toString()
   {
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append("HP: ");

      stringBuffer.append(super.toString());
      
      stringBuffer.append("\n");
      stringBuffer.append("isDamaged: ");
      stringBuffer.append(this.isDamaged());

      stringBuffer.append("\n");
      stringBuffer.append("isCloseDeath: ");
      stringBuffer.append(this.isCloseDeath());
      
      return stringBuffer.toString();
   }
   
}