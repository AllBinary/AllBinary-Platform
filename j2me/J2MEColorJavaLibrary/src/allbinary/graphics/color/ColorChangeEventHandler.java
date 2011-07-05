/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
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
