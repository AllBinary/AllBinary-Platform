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
package org.allbinary.graphics.draw;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.font.MyFont;

public class CanvasDrawLineString
{
   private int x;
   private int y;

   public CanvasDrawLineString(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   private final DrawStringUtil drawStringUtil = DrawStringUtil.getInstance();
   
   public void paint(Graphics graphics, String string, int line)
   {

       final MyFont myFont = MyFont.getInstance();
       
      //Font font = graphics.getFont();
      //graphics.setFont(Font.getFont(font.getFace(), font.getStyle(), Font.SIZE_LARGE));
       drawStringUtil.drawCenterString(graphics, string, 0, string.length(), x, 
               y + (line * myFont.DEFAULT_CHAR_HEIGHT));
      //graphics.setFont(font);
   }
}
