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
package org.allbinary.game.ag.ai;

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.game.ag.ai.tactical.BasicRandomAIFactory;
import org.allbinary.game.ai.ArtificialIntelligenceInterfaceFactoryInterface;
import org.allbinary.game.ai.BasicAI;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.util.ABHashtable;

public class DiveAndDirectionalTrackingAIFactory 
    implements ArtificialIntelligenceInterfaceFactoryInterface
{
    @Override
    public ArtificialIntelligenceInterface getInstance(
            final ABHashtable hashtable, final AllBinaryLayer ownerLayerInterface, final GameInput gameInput)
    throws Exception
    {
        Object visitorCanBeNull = hashtable.get((Object) BasicAI.AI_VISITOR);
        
        if(visitorCanBeNull == null)
        {
            visitorCanBeNull = ThrustAIVisitorFactory.getInstance();
            //throw new Exception("No Visitor Provided");
        }
        
        final ABHashtable hashtable2 = new BasicProbabilityAIDataFactory().getInstance();
        
        hashtable2.put(BasicAI.AI_VISITOR, LastKeyAIVisitorFactory.getInstance());
        
        final ArtificialIntelligenceInterface artificialIntelligenceInterface = 
            new BasicRandomAIFactory().getInstance(
                hashtable2, 
                ownerLayerInterface, gameInput);
        
        return new DiveAndDirectionalTrackingAI(ownerLayerInterface, artificialIntelligenceInterface, gameInput, (Visitor) visitorCanBeNull);
    }
}
