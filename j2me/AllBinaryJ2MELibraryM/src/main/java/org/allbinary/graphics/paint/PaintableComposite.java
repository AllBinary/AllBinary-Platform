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
package org.allbinary.graphics.paint;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author user
 */
public class PaintableComposite extends Paintable
{
   private PaintableInterface[] paintableArray;
   
   public PaintableComposite(final PaintableInterface[] paintableArray)
   {
      this.paintableArray = paintableArray;
   }
   
   @Override
   public void paint(final Graphics graphics)
   {
      for(int index = paintableArray.length - 1; index >= 0; index--)
      {
         this.paintableArray[index].paint(graphics);
      }
   }

   @Override
   public void paintThreed(Graphics graphics)
   {
      for(int index = paintableArray.length - 1; index >= 0; index--)
      {
         this.paintableArray[index].paintThreed(graphics);
      }
   }
}
