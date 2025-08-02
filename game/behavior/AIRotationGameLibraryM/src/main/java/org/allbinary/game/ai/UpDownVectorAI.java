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
   private BasicVelocityProperties velocityInterface;
   private int index = 0;

   public UpDownVectorAI(AllBinaryLayer ownerLayerInterface,
      GameInput gameInput) throws Exception
   {
      super(ownerLayerInterface, gameInput);

      VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface =
         (VelocityInterfaceCompositeInterface) this.getOwnerLayerInterface();

      this.velocityInterface =
         velocityInterfaceCompositeInterface.getVelocityProperties();
      this.velocityInterface.getVelocityYBasicDecimalP().set(0);
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      //logUtil.put(commonStrings.START, this, commonStrings.PROCESS);

      int y = this.getOwnerLayerInterface().getHeight() * 270;
      
      if (this.index < 5)
      {
          BasicDecimal basicDecimal = this.velocityInterface.getVelocityYBasicDecimalP();
          
          basicDecimal.set(0);
          basicDecimal.add(y);

         this.index++;
      } else if (this.index < 10)
      {
          BasicDecimal basicDecimal = this.velocityInterface.getVelocityYBasicDecimalP();
          
          basicDecimal.set(0);
          basicDecimal.subtract(y);

         this.index++;
      } else
      {
         this.index = 0;
         this.processAI(allBinaryLayerManager);
      }
   }
}