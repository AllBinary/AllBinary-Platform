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
package org.allbinary.game.input;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.mapping.PersistentInputMapping;

public class PlatformInputMappingFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PlatformInputMappingFactory instance = new PlatformInputMappingFactory();

    public static PlatformInputMappingFactory getInstance()
    {
        return instance;
    }

    private PersistentInputMapping SINGLETON = null;

    public PersistentInputMapping getPersistentInputMappingInstance()
    {
        try
        {
            if (SINGLETON == null)
            {
                //SINGLETON = new NormalJ2MEGameInputMapping();
                SINGLETON = new MotionJ2MEGameInputMapping();
            }
        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
        }
        return SINGLETON;
    }
}
