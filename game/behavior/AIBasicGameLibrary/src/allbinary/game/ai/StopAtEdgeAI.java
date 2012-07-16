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
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;

public class StopAtEdgeAI extends BasicAI
{
   public StopAtEdgeAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager)
           throws Exception
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
       
       BasicVelocityProperties velocityProperties = 
    		   ((VelocityInterfaceCompositeInterface) ownerLayerInterface).getVelocityProperties();
       
      int x = ownerLayerInterface.getX();
      int y = ownerLayerInterface.getY();
      int x2 = ownerLayerInterface.getX2();
      int y2 = ownerLayerInterface.getY2();
      
      int width = ownerLayerInterface.getWidth();
      int height = ownerLayerInterface.getHeight();
      
      DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
      if (x2 + velocityProperties.getVelocityXBasicDecimal().getScaled() > displayInfo.getLastWidth())
      {
          ownerLayerInterface.setPosition(displayInfo.getLastWidth() - width, y, ownerLayerInterface.getZ());
         //ownerLayerInterface.setPosition(width + 1, y);
          velocityProperties.getVelocityYBasicDecimal().set(0);
      }

      if (y2 + velocityProperties.getVelocityYBasicDecimal().getScaled() > displayInfo.getLastHeight())
      {
         //ownerLayerInterface.setPosition(x, height + 1);
          ownerLayerInterface.setPosition(x, displayInfo.getLastHeight() - height, ownerLayerInterface.getZ());
          velocityProperties.getVelocityXBasicDecimal().set(0);
      }

      if (x + velocityProperties.getVelocityXBasicDecimal().getScaled() < 0)
      {
         ownerLayerInterface.setPosition(0, y, ownerLayerInterface.getZ());
         velocityProperties.getVelocityYBasicDecimal().set(0);
      }

      if (y + velocityProperties.getVelocityYBasicDecimal().getScaled() < 0)
      {
         ownerLayerInterface.setPosition(x, 0, ownerLayerInterface.getZ());
         velocityProperties.getVelocityXBasicDecimal().set(0);
      }
   }
}
