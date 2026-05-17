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
   public StopAtEdgeAI(final AllBinaryLayer ownerLayerInterface, final GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);
   }

   @Override
   public void processAI(final AllBinaryLayerManager allBinaryLayerManager)
           throws Exception
   {
       final AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
       
       final VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface = 
           ((VelocityInterfaceCompositeInterface) ownerLayerInterface);
       final BasicVelocityProperties velocityProperties = velocityInterfaceCompositeInterface.getVelocityProperties();
       
      final int x = ownerLayerInterface.getXP();
      final int y = ownerLayerInterface.getYP();
      final int x2 = ownerLayerInterface.getX2();
      final int y2 = ownerLayerInterface.getY2();
      
      final int width = ownerLayerInterface.getWidth();
      final int height = ownerLayerInterface.getHeight();
      
      final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
      if (x2 + velocityProperties.getVelocityXBasicDecimalP().getScaled() > displayInfo.getLastWidth())
      {
          ownerLayerInterface.setPosition(displayInfo.getLastWidth() - width, y, ownerLayerInterface.getZP());
         //ownerLayerInterface.setPosition(width + 1, y);
          velocityProperties.getVelocityYBasicDecimalP().setint(0);
      }

      if (y2 + velocityProperties.getVelocityYBasicDecimalP().getScaled() > displayInfo.getLastHeight())
      {
         //ownerLayerInterface.setPosition(x, height + 1);
          ownerLayerInterface.setPosition(x, displayInfo.getLastHeight() - height, ownerLayerInterface.getZP());
          velocityProperties.getVelocityXBasicDecimalP().setint(0);
      }

      if (x + velocityProperties.getVelocityXBasicDecimalP().getScaled() < 0)
      {
         ownerLayerInterface.setPosition(0, y, ownerLayerInterface.getZP());
         velocityProperties.getVelocityYBasicDecimalP().setint(0);
      }

      if (y + velocityProperties.getVelocityYBasicDecimalP().getScaled() < 0)
      {
         ownerLayerInterface.setPosition(x, 0, ownerLayerInterface.getZP());
         velocityProperties.getVelocityXBasicDecimalP().setint(0);
      }
   }
}
