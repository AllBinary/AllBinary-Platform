package org.allbinary.logic.io.file;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

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

    public List<String> loadFileAsList(final AbFile file, final int max, final byte[] byteArray1) {
        
        AbFileInputStream inputStream = null;
        byte[] byteArray = null;
        try {
            inputStream = new AbFileInputStream(file);
            final ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream(max);
            byteArray = streamUtil.getByteArray(inputStream, outputStream2, byteArray1);

            streamUtil.close(inputStream);
        } catch (Exception e) {
            streamUtil.close(inputStream);
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, commonStrings.ADD, e));
        }

        final ArrayList stringList = new ArrayList();

        if(byteArray == null) {
            return stringList;
        }

        final int size = byteArray.length;
        //LogUtil.put(LogFactory.getInstance("size: " + size, this, commonStrings.PROCESS));
        int index = 0;
        int startIndex;
        while(index < size) {
            //LogUtil.put(LogFactory.getInstance("index" + index, this, commonStrings.PROCESS));
            startIndex = index;
            while(byteArray[index] != '\n') {
                index++;
            }
            stringList.add(new String(byteArray, startIndex, (index - startIndex)));
            index++;
        }
        
        return stringList;
    }
    
}
