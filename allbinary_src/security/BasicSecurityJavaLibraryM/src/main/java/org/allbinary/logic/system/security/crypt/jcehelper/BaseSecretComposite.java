/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.logic.system.security.crypt.jcehelper;

import org.allbinary.logic.NullUtil;

/**
 *
 * @author User
 */
public class BaseSecretComposite {
   
    public static final BaseSecretComposite NULL_SECRET_COMPOSITE = new BaseSecretComposite();
    
    public byte[] encrypt(final byte[] array) throws Exception {
        return NullUtil.getInstance().NULL_BYTE_ARRAY;
    }
    
    public byte[] decrypt(byte[] array) throws Exception {
        return NullUtil.getInstance().NULL_BYTE_ARRAY;
    }

}
