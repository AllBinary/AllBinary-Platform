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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.layer.Layer;

public class PaintableLayerComposite extends Paintable
{
   private final Layer[] paintableArray;
   
   public PaintableLayerComposite(final Layer[] paintableArray)
   {
      this.paintableArray = paintableArray;
   }
   
   @Override
   public void paint(final Graphics graphics)
   {
      for(int index = paintableArray.length; --index >= 0;)
      {
         this.paintableArray[index].paint(graphics);
      }
   }

   @Override   
   public void paintThreed(final Graphics graphics)
   {
       PaintableInterface paintableInterface;
      for(int index = paintableArray.length; --index >= 0;)
      {
          paintableInterface = (PaintableInterface) this.paintableArray[index];
          paintableInterface.paintThreed(graphics);
      }
   }

}
