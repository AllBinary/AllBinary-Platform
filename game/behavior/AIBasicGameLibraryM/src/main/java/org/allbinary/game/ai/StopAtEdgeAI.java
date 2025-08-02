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
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

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
       
      int x = ownerLayerInterface.getXP();
      int y = ownerLayerInterface.getYP();
      int x2 = ownerLayerInterface.getX2();
      int y2 = ownerLayerInterface.getY2();
      
      int width = ownerLayerInterface.getWidth();
      int height = ownerLayerInterface.getHeight();
      
      DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
      if (x2 + velocityProperties.getVelocityXBasicDecimalP().getScaled() > displayInfo.getLastWidth())
      {
          ownerLayerInterface.setPosition(displayInfo.getLastWidth() - width, y, ownerLayerInterface.getZP());
         //ownerLayerInterface.setPosition(width + 1, y);
          velocityProperties.getVelocityYBasicDecimalP().set(0);
      }

      if (y2 + velocityProperties.getVelocityYBasicDecimalP().getScaled() > displayInfo.getLastHeight())
      {
         //ownerLayerInterface.setPosition(x, height + 1);
          ownerLayerInterface.setPosition(x, displayInfo.getLastHeight() - height, ownerLayerInterface.getZP());
          velocityProperties.getVelocityXBasicDecimalP().set(0);
      }

      if (x + velocityProperties.getVelocityXBasicDecimalP().getScaled() < 0)
      {
         ownerLayerInterface.setPosition(0, y, ownerLayerInterface.getZP());
         velocityProperties.getVelocityYBasicDecimalP().set(0);
      }

      if (y + velocityProperties.getVelocityYBasicDecimalP().getScaled() < 0)
      {
         ownerLayerInterface.setPosition(x, 0, ownerLayerInterface.getZP());
         velocityProperties.getVelocityXBasicDecimalP().set(0);
      }
   }
}
