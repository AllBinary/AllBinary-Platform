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
import org.allbinary.layer.Layer;

public class PaintableLayerComposite extends Paintable
{
   private Layer[] paintableArray;
   
   public PaintableLayerComposite(Layer[] paintableArray)
   {
      this.paintableArray = paintableArray;
   }
   
   public void paint(Graphics graphics)
   {
      for(int index = paintableArray.length; --index >= 0;)
      {
         this.paintableArray[index].paint(graphics);
      }
   }
}
