/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.persistance;

import jsinterop.annotations.JsType;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public class PlatformRecordIdUtil {
   
    private static final PlatformRecordIdUtil instance = new PlatformRecordIdUtil();

    /**
     * @return the instance
     */
    @JsMethod
    public static PlatformRecordIdUtil getInstance() {
        return PlatformRecordIdUtil.instance;
    }
    
    private final String PLATFORM_SHORT_NAME = "HTML";
    
    @JsMethod
    public String getRecordId(final AbeClientInformationInterface abeClientInformation, final String baseRecordId) {
        return new StringMaker().append(abeClientInformation.toShortString()).append(CommonSeps.getInstance().UNDERSCORE).append(this.PLATFORM_SHORT_NAME).append(baseRecordId).toString();
    }
    
}
