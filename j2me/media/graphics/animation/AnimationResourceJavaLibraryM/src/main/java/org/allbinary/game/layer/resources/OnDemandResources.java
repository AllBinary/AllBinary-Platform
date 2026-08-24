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
package org.allbinary.game.layer.resources;

import jsinterop.annotations.JsType;

import org.allbinary.animation.resource.BaseResourceAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class OnDemandResources
{
    @JsProperty
    public static final OnDemandResources NULL_ON_DEMAND_RESOURCES = new OnDemandResources();
    
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    @JsConstructor
    protected OnDemandResources()
    {
    }

    @JsMethod
    public void waitFor() throws Exception
    {
    }
    
    @JsMethod
    public void init() throws Exception
    {
    }

    @JsMethod
    public int initAt(
            ImageCache imageCache,
            BaseResourceAnimationInterfaceFactoryInterfaceFactory 
            resourceAnimationInterfaceFactoryInterfaceFactory, 
            int portion, String loadingString, int index)
        throws Exception
    {
        return 0;
    }
}
