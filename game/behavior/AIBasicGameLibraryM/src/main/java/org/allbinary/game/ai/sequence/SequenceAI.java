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

import org.allbinary.ai.ArtificialIntelligence;
import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.ai.ArtificialIntelligenceTransitionInterface;
import org.allbinary.game.ai.ArrayAI;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class SequenceAI extends ArrayAI
{
    private static final String SEQUENCE_AI = "Sequence AI";
    
   private int index;

   public SequenceAI(final ArtificialIntelligenceInterface[] artificialIntelligenceInterfaceArray, final AllBinaryLayer ownerLayerInterface, final GameInput gameInput)
   {
      super(artificialIntelligenceInterfaceArray, ownerLayerInterface, gameInput);
   }

   @Override
   public void processAI(final AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      this.getArtificialIntelligenceInterface()[this.index].processAI(allBinaryLayerManager);
   }

   public void next()
   {
      this.index++;
      
      final ArtificialIntelligenceInterface artificialIntelligenceInterface = 
          this.getSelectedArtificialIntelligenceInterface();
      if(artificialIntelligenceInterface.getId() == ArtificialIntelligence.AI_ID)
      {
          final ArtificialIntelligenceTransitionInterface artificialIntelligenceTransitionInterface = (ArtificialIntelligenceTransitionInterface) /*TS as unknown*/ artificialIntelligenceInterface;
          artificialIntelligenceTransitionInterface.transition();
      }

      //this.logUtil.putF("Current AI: " + this.getSelectedArtificialIntelligenceInterface(), this, this.commonStrings.GET_INSTANCE);

   }

   public int getIndex()
   {
      return this.index;
   }

   public void setIndex(final int index)
   {
      this.index = index;
   }

   public ArtificialIntelligenceInterface getSelectedArtificialIntelligenceInterface()
   {
      return this.getArtificialIntelligenceInterface()[this.index];
   }
   
   @Override
   public String getName()
   {
       return SequenceAI.SEQUENCE_AI;
   }
   
   public String toString()
   {
       final StringMaker stringBuffer = new StringMaker();
       
       stringBuffer.append(super.toString());
       stringBuffer.append(" Selected AI: ");
       stringBuffer.append(StringUtil.getInstance().toString(this.getSelectedArtificialIntelligenceInterface()));
       
       return stringBuffer.toString();
   }
}