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
package org.allbinary.game.resource;

import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class FeatureResourceInitializationUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final FeatureResourceInitializationUtil instance = 
        new FeatureResourceInitializationUtil();
 
    public static FeatureResourceInitializationUtil getInstance()
    {
        return instance;
    }
    
    public void init(int level) throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(commonStrings.START, this, commonStrings.INIT);

        FeaturedResourceFactory featuredResourceFactory = 
            FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();

        featuredResourceFactory.init(level);

        // only needs to occur if game level resources are reloaded - scale or
        // opengl change
        FeaturedResourceRelativeRelationshipFactory.getInstance().init(level);
    }
}
