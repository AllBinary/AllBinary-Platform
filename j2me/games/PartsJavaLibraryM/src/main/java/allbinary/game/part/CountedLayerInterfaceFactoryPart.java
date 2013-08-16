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
package allbinary.game.part;

import java.util.Hashtable;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactory;

import allbinary.animation.Animation;
import allbinary.graphics.font.MyFont;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.math.PrimitiveLongUtil;

public class CountedLayerInterfaceFactoryPart implements PartInterface
{
   private Animation animationInterface;
   private int total;
   //private String totalString;
   private char[] totalString;
   private int totalStringWidth;
   private CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory;

   private final PrimitiveLongUtil primitiveLongUtil;
   
   public CountedLayerInterfaceFactoryPart(int total,
           CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory)
   {
      this.primitiveLongUtil = new PrimitiveLongUtil(1000);
       
      this.init(total, countedPickedUpLayerInterfaceFactory);
   }

   private void init(int total,
           CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory)
   {
       this.setCountedPickedUpLayerInterfaceFactory(countedPickedUpLayerInterfaceFactory);
       this.total = total;
   }

   public AllBinaryLayer getInstance(Hashtable hashtable, int x, int y, int z)
           throws Exception
   {
      if (this.total > 0)
      {
         this.setTotal(this.total - 1);
         return this.getCountedPickedUpLayerInterfaceFactory().getInstance(hashtable, x, y, z);
      } else
      {
         throw new Exception(
                 "No more left. Could use a listener to automatically remove");
      }
   }

   public int getTotal()
   {
      return total;
   }

   public void setTotal(int total)
   {
      this.total = total;
      
      //this.totalString = this.primitiveLongUtil.getString(total);
      this.totalString = this.primitiveLongUtil.getCharArray(total);
      //this.setTotalStringWidth(MyFont.MYFONT.defaultFont.stringWidth(totalString));
      this.setTotalStringWidth(MyFont.getInstance().defaultFont.charsWidth(totalString, 0, this.primitiveLongUtil.getCurrentTotalDigits()));
   }

   public void paint(Graphics graphics)
   {
   }

   public void paintThreed(Graphics graphics)
   {
   }
   
   public Animation getAnimationInterface()
   {
      return animationInterface;
   }

   public void setAnimationInterface(Animation animationInterface)
   {
      this.animationInterface = animationInterface;
   }

   /**
    * @return the totalStringWidth
    */
   public int getTotalStringWidth()
   {
      return totalStringWidth;
   }

   /**
    * @param totalStringWidth the totalStringWidth to set
    */
   private void setTotalStringWidth(int totalStringWidth)
   {
      this.totalStringWidth = totalStringWidth;
   }

   /**
    * @return the totalString
    */
   /*
   public String getTotalString()
   {
      return totalString;
   }
   */
   public char[] getTotalString()
   {
      return totalString;
   }

    /**
     * @return the countedPickedUpLayerInterfaceFactory
     */
    public CountedPickedUpLayerInterfaceFactory getCountedPickedUpLayerInterfaceFactory()
    {
        return countedPickedUpLayerInterfaceFactory;
    }

    /**
     * @param countedPickedUpLayerInterfaceFactory the countedPickedUpLayerInterfaceFactory to set
     */
    public void setCountedPickedUpLayerInterfaceFactory(CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory)
    {
        this.countedPickedUpLayerInterfaceFactory = countedPickedUpLayerInterfaceFactory;
    }
}
