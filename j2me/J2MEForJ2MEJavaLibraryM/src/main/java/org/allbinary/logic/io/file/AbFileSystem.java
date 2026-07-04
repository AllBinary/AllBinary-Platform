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
package org.allbinary.logic.io.file;

import java.io.InputStream;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
//J2MEForJ2ME
public class AbFileSystem {
    
    private static final AbFileSystem instance = new AbFileSystem();
    
    public static AbFileSystem getInstance() {
        return AbFileSystem.instance;
    }

    private final LogUtil logUtil = LogUtil.getInstance();
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public String[] getFilesAsStringArrayForPath(final String currentDirPath) {
        //TODO - Use JSR 75 here
        return StringUtil.getInstance().getArrayInstance();
    }
    
    public String readAsString(final String fileName)
    {
        final byte[] bytes = new byte[1000000];
        return this.readAsString(fileName, bytes);
    }

    public String readAsString(final String fileName, final byte[] bytes)
    {
        //TODO - Use JSR 75 here
        return StringUtil.getInstance().EMPTY_STRING;
    }
    
    public boolean close(Object closeable) {
        try {
            if (closeable != null) {
                //this.logUtil.putF("Closing: " + closeable, this, CLOSE);
                ((InputStream) closeable).close();
            }
            return true;
        } catch (Exception e) {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CLOSE, e);
            return false;
        }
    }
    
}
