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

public class ScrollOverAtEndAI extends BasicAI
{
   public ScrollOverAtEndAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);
   }

   @Override
   public void processAI(AllBinaryLayerManager allBinaryLayerManager)
           throws Exception
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
      int x = ownerLayerInterface.getXP();
      int y = ownerLayerInterface.getYP();
      
      int width = ownerLayerInterface.getWidth();
      int height = ownerLayerInterface.getHeight();
      
      DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
      //was x2
      if (x > displayInfo.getLastWidth())
      {
          ownerLayerInterface.setPosition(0, y, ownerLayerInterface.getZP());
         //ownerLayerInterface.setPosition(width + 1, y);
      }

      if (y > displayInfo.getLastHeight())
      {
         //ownerLayerInterface.setPosition(x, height + 1);
          ownerLayerInterface.setPosition(x, 0, ownerLayerInterface.getZP());
      }

      if (x < -width)
      {
         ownerLayerInterface.setPosition(displayInfo.getLastWidth() - width, y, ownerLayerInterface.getZP());
      }

      if (y < -height)
      {
         ownerLayerInterface.setPosition(x, displayInfo.getLastHeight() - height, ownerLayerInterface.getZP());
      }
   }   
}
