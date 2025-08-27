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

import java.util.Hashtable;

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class NumberOnOwnGroupSequenceAIFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public ArtificialIntelligenceInterface getInstance(final Hashtable hashtable,
      final ArtificialIntelligenceInterface[] artificialIntelligenceInterface,
      final AllBinaryLayer ownerLayerInterface,
      final GameInput gameInput)
      throws Exception
   {
      final Integer[] integerArray = NumberInSameGroupSequence.getInstance().NUMBER_ON_SAME_TEAM_SEQUENCE;

      final BasicArrayList list = new BasicArrayList();

      for (int index = 0; index < integerArray.length; index++)
      {
         Object integerCanBeNull = hashtable.get((Object) integerArray[index]);
         if (integerCanBeNull == null)
         {
            break;
         }
         list.add(integerCanBeNull);
      }

      final StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append("Total AI Properties: ");
      stringBuffer.append(list.size());
      stringBuffer.append(" == Total AI: ");
      stringBuffer.append(artificialIntelligenceInterface.length);
      stringBuffer.append(" + 1");
      
      final CommonStrings commonStrings = CommonStrings.getInstance();
      logUtil.put(stringBuffer.toString(), this, commonStrings.GET_INSTANCE);
      
      if(artificialIntelligenceInterface.length - 1 > list.size())
      {
         throw new Exception("Not enough AI properties.");
      }

      if(artificialIntelligenceInterface.length - 1 < list.size())
      {
         throw new Exception("Too Many AI properties.");
      }
      
      Integer[] numberOnSameTeam = new Integer[list.size()];
      for (int index = 0; index < numberOnSameTeam.length; index++)
      {
         numberOnSameTeam[index] = (Integer) list.objectArray[index];
      }

      return new NumberOnOwnGroupSequenceAI(numberOnSameTeam,
         artificialIntelligenceInterface, ownerLayerInterface, gameInput);
   }
}