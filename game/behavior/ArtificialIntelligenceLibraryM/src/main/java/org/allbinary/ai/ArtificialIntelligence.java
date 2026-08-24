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

import jsinterop.annotations.JsType;

import org.allbinary.layer.AllBinaryLayerManager;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */

@JsType
public class ArtificialIntelligence
    implements ArtificialIntelligenceInterface
{
    @JsProperty
    public static final int AI_ID = 2;
    
    private static final ArtificialIntelligence instance = new ArtificialIntelligence();

    /**
     * @return the instance
     */
    @JsMethod
    public static ArtificialIntelligence getInstance()
    {
        return ArtificialIntelligence.instance;
    }
    
    @JsConstructor
    public ArtificialIntelligence()
    {
    }

    @Override
    @JsMethod
    public void processAI(AllBinaryLayerManager layerManager)
        throws Exception
    {
    }

    @Override
    @JsMethod
    public int getId()
    {
        return 1;
    }
}
