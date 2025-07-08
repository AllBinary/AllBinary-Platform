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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.ai.ArtificialIntelligenceTransitionInterface;
import org.allbinary.game.ai.ArrayAI;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.string.StringUtil;

public class SequenceAI extends ArrayAI
{
   private int index;

   public SequenceAI(ArtificialIntelligenceInterface[] artificialIntelligenceInterfaceArray,
           AllBinaryLayer ownerLayerInterface,
      GameInput gameInput)
   {
      super(artificialIntelligenceInterfaceArray, ownerLayerInterface, gameInput);
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      this.getArtificialIntelligenceInterface()[index].processAI(allBinaryLayerManager);
   }

   public void next()
   {
      this.index++;
      
      ArtificialIntelligenceInterface artificialIntelligenceInterface = 
          this.getSelectedArtificialIntelligenceInterface();
      if(artificialIntelligenceInterface.getId() == ArtificialIntelligenceTransitionInterface.ID)
      {
         ((ArtificialIntelligenceTransitionInterface) artificialIntelligenceInterface).transition();
      }

      //logUtil.put("Current AI: " + this.getSelectedArtificialIntelligenceInterface(), this, commonStrings.GET_INSTANCE);

   }

   public int getIndex()
   {
      return index;
   }

   public void setIndex(int index)
   {
      this.index = index;
   }

   public ArtificialIntelligenceInterface getSelectedArtificialIntelligenceInterface()
   {
      return this.getArtificialIntelligenceInterface()[this.index];
   }
   
   private static final String SEQUENCE_AI = "Sequence AI";
   public String getName()
   {
       return SEQUENCE_AI;
   }
   
   public String toString()
   {
       StringMaker stringBuffer = new StringMaker();
       
       stringBuffer.append(super.toString());
       stringBuffer.append(" Selected AI: ");
       stringBuffer.append(StringUtil.getInstance().toString(this.getSelectedArtificialIntelligenceInterface()));
       
       return stringBuffer.toString();
   }
}