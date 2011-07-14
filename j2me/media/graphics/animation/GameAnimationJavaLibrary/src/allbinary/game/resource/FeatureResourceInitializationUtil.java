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
package allbinary.game.resource;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;

public class FeatureResourceInitializationUtil
{
    private static final FeatureResourceInitializationUtil instance = 
        new FeatureResourceInitializationUtil();
 
    public static FeatureResourceInitializationUtil getInstance()
    {
        return instance;
    }
    
    public void init(int level) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().INIT));

        FeaturedResourceFactory featuredResourceFactory = 
            FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();

        featuredResourceFactory.init(level);

        // only needs to occur if game level resources are reloaded - scale or
        // opengl change
        FeaturedResourceRelativeRelationshipFactory.getInstance().init(level);
    }
}
