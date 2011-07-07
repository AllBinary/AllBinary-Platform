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
package allbinary.game.ai.sequence;

import allbinary.ai.ArtificialIntelligenceInterface;
import allbinary.ai.ArtificialIntelligenceTransitionInterface;
import allbinary.game.ai.ArrayAI;
import allbinary.game.input.GameInput;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;

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

      //LogUtil.put(LogFactory.getInstance("Current AI: " + this.getSelectedArtificialIntelligenceInterface(), this, CommonStrings.getInstance().GET_INSTANCE));

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
       StringBuilder stringBuffer = new StringBuilder();
       
       stringBuffer.append(super.toString());
       stringBuffer.append(" Selected AI: ");
       stringBuffer.append(this.getSelectedArtificialIntelligenceInterface());
       
       return stringBuffer.toString();
   }
}