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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
//AndroidToJ2ME
public class AbFileSystem {
    
    private static final AbFileSystem instance = new AbFileSystem();
    
    public static AbFileSystem getInstance() {
        return AbFileSystem.instance;
    }

    private final LogUtil logUtil = LogUtil.getInstance();
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    public boolean isDirectoryOrFile(final String path) {
        final File file = new File(path);
        return file.isDirectory() || file.isFile();
    }
    
    public boolean isDirectory(final String path) {
        return new File(path).isDirectory();
    }
    
    public String[] getFilesAsStringArrayForPath(final String currentDirPath) {
        final File file = new File(currentDirPath);
        if(file.exists()) {
            return file.list();
        } else {
            return StringUtil.getInstance().getArrayInstance();
        }
    }

    public String readAsString(final String fileName)
    {
        final byte[] bytes = new byte[1000000];
        return this.readAsString(fileName, bytes);
    }

    public String readAsString(final String fileName, final byte[] bytes)
    {
        Object closeable = NullUtil.getInstance().NULL_OBJECT;
        try
        {
            final InputStream idFile = new FileInputStream(fileName);
            closeable = idFile;
            final int size = idFile.read(bytes);
            if(size > 0) {
                return new String(bytes, 0, size);
            }
        } catch (Exception e)
        {
//            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(this.logConfigTypeFactory.IDLOGGING))
//            {
                this.logUtil.put(this.commonStrings.EXCEPTION, this, "readAsString", e);
//            }
        } finally {
            this.close(closeable);
        }
        return StringUtil.getInstance().EMPTY_STRING;
    }
    
    public boolean close(Object closeable) {
        try {
            if (closeable != null) {
                //this.logUtil.putF("Closing: " + closeable, this, CLOSE);
                final InputStream inputStream = ((InputStream) closeable);
                inputStream.close();
            }
            return true;
        } catch (Exception e) {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CLOSE, e);
            return false;
        }
    }
    
}
