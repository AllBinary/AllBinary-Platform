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
package allbinary.graphics.color;

import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class ColorChangeEventHandler extends BasicEventHandler
{
   private static final ColorChangeEventHandler eventHandler = 
      new ColorChangeEventHandler();

   private ColorChangeEventHandler()
   {
   }

   public synchronized static ColorChangeEventHandler getInstance()
   {
      return ColorChangeEventHandler.eventHandler;
   }
}
