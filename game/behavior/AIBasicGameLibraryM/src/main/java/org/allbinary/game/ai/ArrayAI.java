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
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class ArrayAI extends BasicAI
{
   private final ArtificialIntelligenceInterface[] artificialIntelligenceInterfaceArray;

   public ArrayAI(ArtificialIntelligenceInterface[] artificialIntelligenceInterface,
           AllBinaryLayer ownerLayerInterface,
      GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);
      this.artificialIntelligenceInterfaceArray = artificialIntelligenceInterface;
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      int size = this.artificialIntelligenceInterfaceArray.length;
      for(int index = 0; index < size; index++)
      {
          this.artificialIntelligenceInterfaceArray[index].processAI(allBinaryLayerManager);
      }
   }

   public ArtificialIntelligenceInterface[] getArtificialIntelligenceInterface()
   {
      return artificialIntelligenceInterfaceArray;
   }
}