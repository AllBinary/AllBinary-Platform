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
package org.allbinary.game.ai.scroller;

import java.util.Hashtable;

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.game.ai.ArtificialIntelligenceInterfaceFactoryInterface;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class PacePatrolAIFactory 
    implements ArtificialIntelligenceInterfaceFactoryInterface
{
    @Override
    public ArtificialIntelligenceInterface getInstance(
          Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput)
          throws Exception
    {
      hashtable.put(BasePatrolAI.MAX_DISTANCE, SmallIntegerSingletonFactory.getInstance().getInstance(220));
    	return new PacePatrolAI(hashtable, ownerLayerInterface, gameInput);
    }
}
