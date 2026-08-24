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
package org.allbinary.game.combat.destroy;

import jsinterop.annotations.JsType;

import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;


@JsType
public class DestroyableBaseBehavior implements DestroyableInterface
{
    private static final DestroyableBaseBehavior instance = new DestroyableBaseBehavior();
    
    @JsMethod
    public static DestroyableBaseBehavior getInstance()
    {
        return DestroyableBaseBehavior.instance;
    }

    @Override
    @JsMethod
    public String getName()
    {
        return this.getClass().getName();
    }
    
    @Override
    @JsMethod
    public boolean isDestroyed() throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
    
    @JsMethod
    public void setDestroyed(boolean destroyed)
    {
    }    
}
