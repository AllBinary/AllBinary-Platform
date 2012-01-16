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
package allbinary.game.ai;

import allbinary.game.input.GameInput;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;

public class ScrollOverAtHalfAI extends BasicAI
{
   public ScrollOverAtHalfAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager)
           throws Exception
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
       
      int x = ownerLayerInterface.getX();
      int y = ownerLayerInterface.getY();
      int halfWidth = ownerLayerInterface.getHalfWidth();
      int halfHeight = ownerLayerInterface.getHalfHeight();
      
      DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
      if (x > displayInfo.getLastWidth() - halfWidth)
      {
         ownerLayerInterface.setPosition(halfWidth + 1, y, ownerLayerInterface.getZ());
      }

      if (y > displayInfo.getLastHeight() - halfHeight)
      {
         ownerLayerInterface.setPosition(x, halfHeight + 1, ownerLayerInterface.getZ());
      }

      if (x < -halfWidth)
      {
         ownerLayerInterface.setPosition(displayInfo.getLastWidth() - halfWidth, y, ownerLayerInterface.getZ());
      }

      if (y < -halfHeight)
      {
         ownerLayerInterface.setPosition(x, displayInfo.getLastHeight() - halfHeight, ownerLayerInterface.getZ());
      }
   }   
}
