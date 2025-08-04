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
import org.allbinary.game.ai.tactical.BasicRandomAIFactory;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.util.visitor.Visitor;

public class DiveAndDirectionalTrackingAIFactory 
    implements ArtificialIntelligenceInterfaceFactoryInterface
{
    @Override
    public ArtificialIntelligenceInterface getInstance(
            Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput)
    throws Exception
    {
        Visitor visitor = (Visitor) hashtable.get(BasicAI.AI_VISITOR);
        
        if(visitor == null)
        {
            visitor = ThrustAIVisitorFactory.getInstance();
            //throw new Exception("No Visitor Provided");
        }
        
        Hashtable hashtable2 = new BasicProbabilityAIDataFactory().getInstance();
        
        hashtable2.put(BasicAI.AI_VISITOR, LastKeyAIVisitorFactory.getInstance());
        
        ArtificialIntelligenceInterface artificialIntelligenceInterface = 
            new BasicRandomAIFactory().getInstance(
                hashtable2, 
                ownerLayerInterface, gameInput);
        
        return new DiveAndDirectionalTrackingAI(ownerLayerInterface, artificialIntelligenceInterface, gameInput, visitor);
    }
}
