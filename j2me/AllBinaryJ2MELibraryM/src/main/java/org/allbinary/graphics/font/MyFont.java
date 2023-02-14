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
package org.allbinary.graphics.font;

import javax.microedition.lcdui.Font;

public class MyFont
{
   private static final MyFont instance = new MyFont();
	
   public static MyFont getInstance()
   {
      return instance;
   }
   
   //fonts
   private final Font defaultFont = Font.getDefaultFont();
   public final int DEFAULT_CHAR_HEIGHT = defaultFont.getHeight();
   private final int DEFAULT_CHAR_WIDTH = defaultFont.charWidth('C');

   private MyFont()
   {
   }

   public int charWidth() {
       return DEFAULT_CHAR_WIDTH;
   }
   
   public int stringWidth(final int size) {
       return DEFAULT_CHAR_WIDTH * size;
   }

   public int stringWidth(final String string) {
       return DEFAULT_CHAR_WIDTH * string.length();
       //return defaultFont.stringWidth(string);
   }
   
   public int stringWidth2(final String string) {
       return defaultFont.stringWidth(string);
   }
   
   public int getSize() {
       return defaultFont.getSize();
   }

   public int charsWidth(final char[] charArray, final int offset, final int length) {
       return defaultFont.charsWidth(charArray, offset, length);
   }
   
}
