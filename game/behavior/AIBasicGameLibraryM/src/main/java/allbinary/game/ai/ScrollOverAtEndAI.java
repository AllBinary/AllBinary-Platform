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

public class ScrollOverAtEndAI extends BasicAI
{
   public ScrollOverAtEndAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager)
           throws Exception
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
      int x = ownerLayerInterface.getX();
      int y = ownerLayerInterface.getY();
      
      int width = ownerLayerInterface.getWidth();
      int height = ownerLayerInterface.getHeight();
      
      DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
      //was x2
      if (x > displayInfo.getLastWidth())
      {
          ownerLayerInterface.setPosition(0, y, ownerLayerInterface.getZ());
         //ownerLayerInterface.setPosition(width + 1, y);
      }

      if (y > displayInfo.getLastHeight())
      {
         //ownerLayerInterface.setPosition(x, height + 1);
          ownerLayerInterface.setPosition(x, 0, ownerLayerInterface.getZ());
      }

      if (x < -width)
      {
         ownerLayerInterface.setPosition(displayInfo.getLastWidth() - width, y, ownerLayerInterface.getZ());
      }

      if (y < -height)
      {
         ownerLayerInterface.setPosition(x, displayInfo.getLastHeight() - height, ownerLayerInterface.getZ());
      }
   }   
}
