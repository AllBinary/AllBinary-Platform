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
package allbinary.game.ai.scroller;

import java.util.Hashtable;

import allbinary.ai.ArtificialIntelligenceInterface;
import allbinary.game.ai.ArtificialIntelligenceInterfaceFactoryInterface;
import allbinary.game.input.GameInput;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class TerrainPatrolAIFactory
    implements ArtificialIntelligenceInterfaceFactoryInterface
{
    public ArtificialIntelligenceInterface getInstance(
          Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput)
          throws Exception
    {
      hashtable.put(PacePatrolAI.MAX_DISTANCE, SmallIntegerSingletonFactory.getInstance().getInstance(220));
    	return new TerrainPatrolAI(hashtable, ownerLayerInterface, gameInput);
    }
}
