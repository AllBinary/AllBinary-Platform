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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.string.CommonStrings;

public class NumberOnOwnGroupSequenceAIFactory
{
   public ArtificialIntelligenceInterface getInstance(Hashtable hashtable,
      ArtificialIntelligenceInterface[] artificialIntelligenceInterface,
      AllBinaryLayer ownerLayerInterface,
      GameInput gameInput)
      throws Exception
   {
      Integer[] integerArray =
          NumberInSameGroupSequence.getInstance().NUMBER_ON_SAME_TEAM_SEQUENCE;

      BasicArrayList list = new BasicArrayList();

      for (int index = 0; index < integerArray.length; index++)
      {
         Integer integer = (Integer) hashtable.get(integerArray[index]);
         if (integer == null)
         {
            break;
         }
         list.add(integer);
      }

      StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append("Total AI Properties: ");
      stringBuffer.append(list.size());
      stringBuffer.append(" == Total AI: ");
      stringBuffer.append(artificialIntelligenceInterface.length);
      stringBuffer.append(" + 1");
      
      final CommonStrings commonStrings = CommonStrings.getInstance();
      LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, commonStrings.GET_INSTANCE));
      
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