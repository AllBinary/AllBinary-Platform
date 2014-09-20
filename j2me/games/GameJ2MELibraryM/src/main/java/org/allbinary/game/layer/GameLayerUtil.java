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

import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.view.ViewPosition;

public class GameLayerUtil
{

   private GameLayerUtil()
   {
   }

   public static boolean isOnScreen(AllBinaryLayer layer)
   {
       DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
       ViewPosition viewPosition = layer.getViewPosition();
       
      return isInside(viewPosition.getX(), viewPosition.getY(),
              viewPosition.getX2(), viewPosition.getY2(), 0, 0,
              displayInfo.getLastWidth(), displayInfo.getLastHeight());
   }

   public static boolean isInside(int rectX1, int rectY1, int rectX2, int rectY2,
      int rect2X1, int rect2Y1, int rect2X2, int rect2Y2)
   {
      if (rectX1 > rect2X1 && rectX2 < rect2X2 && rectY1 > rect2Y1 && rectY2 < rect2Y2)
      {
         return true;
      } else
      {
          return false;
      }
   }
}