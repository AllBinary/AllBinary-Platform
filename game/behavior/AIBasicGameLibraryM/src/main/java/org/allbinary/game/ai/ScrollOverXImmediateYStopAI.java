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
package org.allbinary.game.ai;

import org.allbinary.game.input.GameInput;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class ScrollOverXImmediateYStopAI extends BasicAI
{
   public ScrollOverXImmediateYStopAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager)
           throws Exception
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
      int x = ownerLayerInterface.getX();
      int y = ownerLayerInterface.getY();
      int x2 = ownerLayerInterface.getX2();
      int y2 = ownerLayerInterface.getY2();
      
      int width = ownerLayerInterface.getWidth();
      int height = ownerLayerInterface.getHeight();
      
      DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
      if (x2 > displayInfo.getLastWidth())
      {
          ownerLayerInterface.setPosition(0, y, ownerLayerInterface.getZ());
         //ownerLayerInterface.setPosition(width + 1, y);
      }

      if (y2 > displayInfo.getLastHeight())
      {
         //ownerLayerInterface.setPosition(x, height + 1);
          ownerLayerInterface.setPosition(x, displayInfo.getLastHeight() - height, ownerLayerInterface.getZ());
      }

      if (x < 0)
      {
         ownerLayerInterface.setPosition(displayInfo.getLastWidth() - width, y, ownerLayerInterface.getZ());
      }

      if (y < 0)
      {
         ownerLayerInterface.setPosition(x, 0, ownerLayerInterface.getZ());
      }
   }   
}
