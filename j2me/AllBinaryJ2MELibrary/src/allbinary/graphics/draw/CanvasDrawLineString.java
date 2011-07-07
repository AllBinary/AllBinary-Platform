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
package allbinary.graphics.draw;

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.font.MyFont;

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
   
   private final int HEIGHT = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
   
   public void paint(Graphics graphics, String string, int line)
   {

      //Font font = graphics.getFont();
      //graphics.setFont(Font.getFont(font.getFace(), font.getStyle(), Font.SIZE_LARGE));
       drawStringUtil.drawCenterString(graphics, string, 0, string.length(), x, 
               y + (line * HEIGHT));
      //graphics.setFont(font);
   }
}
