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
package org.allbinary.logic.system.security.licensing;

import jsinterop.annotations.JsType;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */

@JsType
public class ClientInformationFactory {
    
    public ClientInformation getInstance() {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.putF(commonStrings.NOT_IMPLEMENTED, this, commonStrings.GET_INSTANCE);
        final StringUtil stringUtil = StringUtil.getInstance();
        return new ClientInformation(stringUtil.NULL_STRING, stringUtil.NULL_STRING, stringUtil.NULL_STRING, stringUtil.NULL_STRING);
        //throw new RuntimeException();
    }
    
}
