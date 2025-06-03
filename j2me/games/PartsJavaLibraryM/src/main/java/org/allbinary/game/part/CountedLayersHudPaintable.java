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
package org.allbinary.game.part;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;

import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.layer.AllBinaryLayer;

/**
 *
 * @author user
 */
public class CountedLayersHudPaintable implements PaintableInterface
{

   private static final String XXString = "XX";
   private static final int XXStringWidth = MyFont.getInstance().stringWidth(XXString);

   private final MyFont myFont = MyFont.getInstance();
   
   private final PartInterface[] partInterfaceArray;
   private final int countedTotalStringColor;
   private final int countedPartsBorder;
   private final int startIndex;
   
   private final int dropSize;
   
   public CountedLayersHudPaintable(PartInterface[] partInterfaceArray, int dropSize,
           int startIndex, int countedTotalStringColor, int countedPartsBorder)
   {
      this.partInterfaceArray = partInterfaceArray;
      this.startIndex = startIndex;
      
      this.countedTotalStringColor = countedTotalStringColor;
      this.countedPartsBorder = countedPartsBorder;
      
      this.dropSize = dropSize;
   }
   
   public void paint(Graphics graphics)
   {
      int height = myFont.DEFAULT_CHAR_HEIGHT;
      if(this.dropSize > myFont.DEFAULT_CHAR_HEIGHT)
      {
          height = this.dropSize;
      }
       
      int lastWidth = DisplayInfoSingleton.getInstance().getLastWidth();
      int count = 0;
      int widthEdge = lastWidth - this.dropSize;
      int y;

      int size = partInterfaceArray.length;
      
      CountedLayerInterfaceFactoryPart countedLayerInterfaceFactory;
      PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface;
      AllBinaryLayer layerInterface;
      char[] charArray;
      
      for (int index = startIndex; index < size; index++)
      {
          countedLayerInterfaceFactory =
                 (CountedLayerInterfaceFactoryPart) partInterfaceArray[index];

         if (countedLayerInterfaceFactory.getTotal() > 0)
         {
             pickedUpLayerInterfaceFactoryInterface =
                    //(PickedUpLayerInterfaceFactoryInterface) 
                    countedLayerInterfaceFactory.getCountedPickedUpLayerInterfaceFactory();

             layerInterface = pickedUpLayerInterfaceFactoryInterface.getIconLayer();

            y = 40 + (count * height);

            layerInterface.setPosition(widthEdge, y, layerInterface.getZ());
            layerInterface.paint(graphics);

            graphics.setColor(countedTotalStringColor);

            //graphics.drawString(countedLayerInterfaceFactory.getTotalString(),
            charArray = countedLayerInterfaceFactory.getTotalString();
            
            graphics.drawChars(charArray, 0, charArray.length,
                    widthEdge - countedLayerInterfaceFactory.getXOffset(), y, 0);

            count++;
         }
      }

      if (count > 0)
      {
         graphics.setColor(countedPartsBorder);

         graphics.drawRect(lastWidth - (XXStringWidth + this.dropSize),
                 40, XXStringWidth + this.dropSize, (count * height) + 3);
      }
   }
   
   public void paintThreed(Graphics graphics)
   {
   }
   
}
