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
package org.allbinary.logic.io.file;


import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class FileUtil2 {
    
    private static final FileUtil2 instance = new FileUtil2();

    /**
     * @return the instance
     */
    public static FileUtil2 getInstance()
    {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final StreamUtil streamUtil = StreamUtil.getInstance();
    
    private FileUtil2()
    {
    }

    public BasicArrayList loadFileAsList(final AbFile file, final int max, final byte[] byteArray1) {
        
        AbFileInputStream inputStream = null;
        try {
            inputStream = new AbFileInputStream(file);
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
        }

        return SimpleFileUtil.getInstance().loadFileAsList(inputStream, max, byteArray1);
    }
    
}
