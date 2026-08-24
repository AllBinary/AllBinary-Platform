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
package org.allbinary.graphics.displayable;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author user
 */
@JsType
public class BaseScalable implements ScalableListener
{
    @Override
    @JsMethod
    public float getRatio(final float ratio) {
        return (float) 1;
    }

    @Override
    @JsMethod
    public int getLeft(final int xOffset)
    {
        return -1;
    }

    @Override
    @JsMethod
    public int getTop(final int yOffset)
    {
        return -1;
    }
    
    @Override
    @JsMethod
    public void scale(float ratio)
    {
        
    }
}
