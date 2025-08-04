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

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.game.ai.sequence.SequenceAI;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.layer.identification.GroupLayerManagerListener;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class NumberOnOwnGroupSequenceAI extends SequenceAI
{   
   private Integer[] numberOnSameTeam;

   public NumberOnOwnGroupSequenceAI(Integer[] numberOnSameTeam,
      ArtificialIntelligenceInterface[] artificialIntelligenceInterface,
      AllBinaryLayer ownerLayerInterface,
      GameInput gameInput)
   {
      super(artificialIntelligenceInterface, ownerLayerInterface, gameInput);
      this.numberOnSameTeam = numberOnSameTeam;
   }

   @Override
   public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      final AllBinaryLayer layerInterface = this.getOwnerLayerInterface();

      int index = this.getIndex();
      if (numberOnSameTeam.length > index)
      {
         final int size = GroupLayerManagerListener.getInstance().getGroupSize(layerInterface);
         
         if (numberOnSameTeam[index].intValue() > size)
         {
            this.next();
         }
      }
      super.processAI(allBinaryLayerManager);
   }
}