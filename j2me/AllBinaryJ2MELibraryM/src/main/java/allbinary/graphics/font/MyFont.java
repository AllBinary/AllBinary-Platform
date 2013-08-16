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
package allbinary.graphics.font;

import javax.microedition.lcdui.Font;

public class MyFont
{
   private static final MyFont instance = new MyFont();
	
   public static MyFont getInstance()
   {
      return instance;
   }
   
   //fonts
   public final Font defaultFont = Font.getDefaultFont();
   public final int DEFAULT_CHAR_HEIGHT = defaultFont.getHeight();
   public final int DEFAULT_CHAR_WIDTH = defaultFont.charWidth('C');

   private MyFont()
   {
   }
}
