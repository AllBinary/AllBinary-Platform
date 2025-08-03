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
package org.allbinary.game.ai.sequence;

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class ComboSequenceAI extends SequenceAI
{
   public ComboSequenceAI(ArtificialIntelligenceInterface[] artificialIntelligenceInterface, 
           AllBinaryLayer ownerLayerInterface, GameInput gameInput) {
        super(artificialIntelligenceInterface, ownerLayerInterface, gameInput);
    }
   
   @Override
    public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception {
        final ArtificialIntelligenceInterface[] artificialIntelligenceInterfaceArray = this.getArtificialIntelligenceInterface();
        final int size = artificialIntelligenceInterfaceArray.length;
       for(int index = 0; index < size; index++)
       {
          this.setIndex(index);
          super.processAI(allBinaryLayerManager);
       }
    }
   
}
