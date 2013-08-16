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
package allbinary.game.ai.tactical;

import java.util.Hashtable;

import allbinary.ai.ArtificialIntelligenceInterface;
import allbinary.game.ai.ArtificialIntelligenceInterfaceFactoryInterface;
import allbinary.game.ai.BasicAI;
import allbinary.game.ai.InputProbability;
import allbinary.game.input.GameInput;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.basic.util.visitor.Visitor;

public class BasicRandomAIFactory implements
        ArtificialIntelligenceInterfaceFactoryInterface
{
    public ArtificialIntelligenceInterface getInstance(
            Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput)
    throws Exception
    {        
        Visitor visitor = (Visitor) hashtable.get(BasicAI.AI_VISITOR);
        
        if(visitor == null)
        {
            throw new Exception("No Visitor Provided");
        }
        
        InputProbability inputProbability = (InputProbability) 
            hashtable.get(InputProbability.INPUT_PROBABILITY);

        return new BasicRandomAI(ownerLayerInterface, gameInput, inputProbability, visitor);
    }
}
