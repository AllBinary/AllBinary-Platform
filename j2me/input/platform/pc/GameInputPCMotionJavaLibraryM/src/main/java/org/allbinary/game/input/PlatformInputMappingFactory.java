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

import org.allbinary.game.input.mapping.PersistentInputMapping;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class PlatformInputMappingFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PlatformInputMappingFactory instance = new PlatformInputMappingFactory();

    public static PlatformInputMappingFactory getInstance()
    {
        return instance;
    }

    private PersistentInputMapping SINGLETON = PersistentInputMapping.NULL_PERSISTENT_INPUT_MAPPING;

    public PersistentInputMapping getPersistentInputMappingInstance()
    {
        try
        {
            if (SINGLETON == PersistentInputMapping.NULL_PERSISTENT_INPUT_MAPPING)
            {
                SINGLETON = new MotionLaptopGameInputMapping();
            }
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
        }
        return SINGLETON;
    }

}
