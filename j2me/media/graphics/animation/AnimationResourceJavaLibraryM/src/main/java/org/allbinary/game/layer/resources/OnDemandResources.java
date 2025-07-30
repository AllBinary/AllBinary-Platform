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

import org.allbinary.animation.resource.BaseResourceAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogUtil;

public class OnDemandResources
{
    public static final OnDemandResources NULL_ON_DEMAND_RESOURCES = new OnDemandResources();
    
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    protected OnDemandResources()
    {
    }

    public void waitFor() throws Exception
    {
    }
    
    public void init() throws Exception
    {
    }

    public int init(
            ImageCache imageCache,
            BaseResourceAnimationInterfaceFactoryInterfaceFactory 
            resourceAnimationInterfaceFactoryInterfaceFactory, 
            int portion, String loadingString, int index)
        throws Exception
    {
        return 0;
    }
}
