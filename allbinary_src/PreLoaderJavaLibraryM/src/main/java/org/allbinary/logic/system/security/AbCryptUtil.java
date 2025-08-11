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
package org.allbinary.logic.system.security;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.system.security.crypt.jcehelper.AbCrypt;
import org.allbinary.logic.system.security.crypt.jcehelper.KeySpecFactory;

public class AbCryptUtil {

    private static final AbCryptUtil instance = new AbCryptUtil();
    
    public static AbCryptUtil getInstance() {
        return instance;
    }

    private final StreamUtil streamUtil = StreamUtil.getInstance();
    
    private AbCryptUtil() {
    }

    public byte[] decrypt(final InputStream inputStream, final String key)
            throws Exception {
        ByteArrayOutputStream outputStream = null;

        try {
            outputStream = (ByteArrayOutputStream) streamUtil.get(inputStream, new ByteArrayOutputStream(), new byte[16384]);

            final AbCrypt abCrypt = new AbCrypt(KeySpecFactory.getInstance().DESEDE);
            abCrypt.init(key);
            return abCrypt.decrypt(outputStream.toByteArray());
        } finally {
            streamUtil.close(outputStream);
            streamUtil.close(inputStream);
        }
    }
}
