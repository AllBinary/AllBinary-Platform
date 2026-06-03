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
package org.allbinary.ai;

import org.allbinary.layer.AllBinaryLayerManager;

/**
 *
 * @author user
 */
public class ArtificialIntelligence
    implements ArtificialIntelligenceInterface
{
    public static final int AI_ID = 2;
    
    private static final ArtificialIntelligence instance = new ArtificialIntelligence();

    /**
     * @return the instance
     */
    public static ArtificialIntelligence getInstance()
    {
        return ArtificialIntelligence.instance;
    }
    
    public ArtificialIntelligence()
    {
    }

    @Override
    public void processAI(AllBinaryLayerManager layerManager)
        throws Exception
    {
    }

    @Override
    public int getId()
    {
        return 1;
    }
}
