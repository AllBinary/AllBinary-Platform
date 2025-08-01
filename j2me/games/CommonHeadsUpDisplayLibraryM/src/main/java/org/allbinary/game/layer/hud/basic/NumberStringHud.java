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
package org.allbinary.game.layer.hud.basic;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.logic.math.PrimitiveLongSingleton;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class NumberStringHud extends BasicHud
   implements PaintableInterface
{   
   //private final String PREPEND_STRING;
    private final char[] PREPEND_STRING;

   private int value;
   private int max;
   private int offset;
   //private int halfOffset;
   //private String valueString;
   private char[] valueString;
   private int valueTotalDigits;

   private final PrimitiveLongUtil primitiveLongUtil;

   public NumberStringHud(String prependString, int max, int location, int direction,
           int maxHeight, int maxWidth, int bufferZone, BasicColor basicColor)
           throws Exception
   {
      super(location, direction, maxHeight, maxWidth, bufferZone, basicColor);

      this.PREPEND_STRING = prependString.toCharArray();
      //this.PREPEND_STRING = prependString;
      final MyFont myFont = MyFont.getInstance();
      this.offset = myFont.stringWidth(prependString) + myFont.charWidth();
      
      this.valueString = PrimitiveLongSingleton.getInstance().NUMBER_CHAR_ARRAYS[0];
      //this.valueString = PrimitiveLongUtil.NUMBER_STRING_ARRAY[0];

      //Note score must be (10 X 10^n) - 1
      this.primitiveLongUtil = new PrimitiveLongUtil(max + 1);
      
      this.max = max;
      this.value = 0;
      
      if(direction == 0)
      {
          throw new Exception(BasicHudFactory.getInstance().DIRECTION_EXCEPTION);
      }
   }
   
   public int get()
   {
      return this.value;
   }
      
   public void add(int value)
   {
      this.set(this.value + value);
   }

   public void set(int value)
   {
      this.value = value;
      if(this.value > this.max)
      {
         this.value = 0;
      }
      this.valueString = this.primitiveLongUtil.getCharArray(this.value);
      //this.valueString = this.primitiveLongUtil.getString(this.value);
      this.valueTotalDigits = this.primitiveLongUtil.getCurrentTotalDigits();
   }
   
   public void reduce(int value)
   {
      this.set(this.value - value);
   }

   public void paint(Graphics graphics)
   {
       super.paint(graphics, 
               PREPEND_STRING, 0, PREPEND_STRING.length, 
               this.valueString, 0, this.valueTotalDigits, 
               offset);
      //super.paint(graphics, PREPEND_STRING, valueString, offset);
   }
   
   public void paint(Graphics graphics, int x , int y)
   {
       char[] charArray = PREPEND_STRING;
       //int offset = 0;
       int len = PREPEND_STRING.length;
       char[] charArray2 = this.valueString;
       //int offset2 = 0;
       int len2 = this.valueTotalDigits;

       this.basicSetColorUtil.setBasicColorP(graphics, getBasicColorP());

       graphics.drawChars(charArray, 0, //offset, 
               len, x, y, 0);

       graphics.drawChars(charArray2, 0, //offset2, 
               len2, x - this.offset, y, 0);
   }

   public void paintThreed(Graphics graphics)
   {
   }   
}
