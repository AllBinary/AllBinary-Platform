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

package org.allbinary.layer;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public class LayerManagerLoggingBase {

    @JsMethod
    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        
    }
    
    @JsMethod
    public void appendAt(final AllBinaryLayer layerInterface, final int index) {
        
    }
 
    @JsMethod
    public void remove(final AllBinaryLayer layerInterface) {
        
    }
    
    @JsMethod
    public void removeResult(final LayerManager layerManager, final AllBinaryLayer layerInterface, final boolean result) {
        
    }
 
    @JsMethod
    public void clear() {
        
    }
}