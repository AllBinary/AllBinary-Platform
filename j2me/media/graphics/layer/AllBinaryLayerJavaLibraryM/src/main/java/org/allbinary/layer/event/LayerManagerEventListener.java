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
package org.allbinary.layer.event;

import jsinterop.annotations.JsType;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import jsinterop.annotations.JsMethod;


@JsType
public class LayerManagerEventListener
implements LayerManagerEventListenerInterface
{
    @Override
    @JsMethod
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        
    }

    @Override
    @JsMethod
    public void onCreateLayerManagerEvent(final LayerManagerEvent layerManagerEvent)
    throws Exception
    {
        
    }
    
    @Override
    @JsMethod
    public void onDeleteLayerManagerEvent(final LayerManagerEvent layerManagerEvent)
    throws Exception
    {
        
    }
}
