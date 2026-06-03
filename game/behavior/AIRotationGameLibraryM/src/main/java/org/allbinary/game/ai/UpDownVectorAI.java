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
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.math.BasicDecimal;

public class UpDownVectorAI extends BasicAI
{
   private final BasicVelocityProperties velocityInterface;
   private int index = 0;

   public UpDownVectorAI(final AllBinaryLayer ownerLayerInterface, final GameInput gameInput) throws Exception
   {
      super(ownerLayerInterface, gameInput);

      final VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface = (VelocityInterfaceCompositeInterface) /*TS as unknown*/ this.getOwnerLayerInterface();

      this.velocityInterface =
         velocityInterfaceCompositeInterface.getVelocityProperties();
      this.velocityInterface.getVelocityYBasicDecimalP().setint(0);
   }

   @Override
   public void processAI(final AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      //this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.PROCESS);

      final int y = this.getOwnerLayerInterface().getHeight() * 270;
      
      if (this.index < 5)
      {
          final BasicDecimal basicDecimal = this.velocityInterface.getVelocityYBasicDecimalP();
          
          basicDecimal.setint(0);
          basicDecimal.addint(y);

         this.index++;
      } else if (this.index < 10)
      {
          final BasicDecimal basicDecimal = this.velocityInterface.getVelocityYBasicDecimalP();
          
          basicDecimal.setint(0);
          basicDecimal.subtractint(y);

         this.index++;
      } else
      {
         this.index = 0;
         this.processAI(allBinaryLayerManager);
      }
   }
}