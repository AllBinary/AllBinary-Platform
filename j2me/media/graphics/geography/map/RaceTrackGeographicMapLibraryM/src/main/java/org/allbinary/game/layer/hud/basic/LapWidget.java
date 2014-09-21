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

import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.game.layer.hud.LapInfo;
import org.allbinary.graphics.color.BasicColor;

public class LapWidget extends BasicHud
{
   private final String LAP_STR = "Lap ";
   private final String OF_STR = " of ";

   private String string = StringUtil.getInstance().EMPTY_STRING;
   
   public LapWidget(int location, int direction, BasicColor basicColor)
           throws Exception
   {
      super(location, direction, 15, 40, 2, basicColor);
   }
   
   public void update(LapInfo lapInfo)
   {
      StringMaker stringBuffer = new StringMaker();
      
	   stringBuffer.delete(0, stringBuffer.length());
	   stringBuffer.append(LAP_STR);
	   stringBuffer.append(lapInfo.getCurrentLap());
	   stringBuffer.append(OF_STR);
	   stringBuffer.append(lapInfo.getTotalLaps());
      this.string = stringBuffer.toString();
   }
   
   public void paint(Graphics graphics)
   {
      super.paint(graphics, this.string);
   }
}
