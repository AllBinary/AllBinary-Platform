/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.logic;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class PropertyInitUtil {
 
    private static final PropertyInitUtil instance = new PropertyInitUtil();

    /**
     * @return the instance
     */
    public static PropertyInitUtil getInstance(final int value) {
        
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final LogUtil logUtil = LogUtil.getInstance();
        logUtil.putF("PropertyInitUtil: " + value, logUtil, commonStrings.CONSTRUCTOR);
        
        return PropertyInitUtil.instance;
    }

}
