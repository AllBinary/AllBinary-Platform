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
package allbinary.game.ai;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.StringMaker;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.ai.ArtificialIntelligenceInterface;
import allbinary.game.input.GameInput;
import allbinary.layer.AllBinaryLayer;

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
      
      LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getInstance"));
      
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