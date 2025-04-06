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
package org.allbinary.logic.io.file;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class SimpleFileUtil {
    
    protected static final SimpleFileUtil instance = new SimpleFileUtil();

    /**
     * @return the instance
     */
    public static SimpleFileUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final StreamUtil streamUtil = StreamUtil.getInstance();
    
    public List<String> loadFileAsList(final InputStream inputStream, final int max, final byte[] byteArray1) {
        return this.loadFileAsList(inputStream, max, byteArray1, 0);
    }
    
    public List<String> loadFileAsList(final InputStream inputStream, final int max, final byte[] byteArray1, final int includeReturnLine) {

        final ArrayList stringList = new ArrayList();
        
        if(inputStream == null) {
            return stringList;
        }
        
        byte[] byteArray = null;
        try {
            final ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream(max);
            byteArray = streamUtil.getByteArray(inputStream, outputStream2, byteArray1);
            streamUtil.close(inputStream);
        } catch (Exception e) {
            streamUtil.close(inputStream);
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.ADD, e));
        }

        if(byteArray == null) {
            return stringList;
        }

        //final StringMaker stringMaker = new StringMaker();
        
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
            final String s = new String(byteArray, startIndex, (index + includeReturnLine - startIndex));
            stringList.add(s);
            //stringMaker.append(s).append('\n');
            index++;
        }
        
        //LogUtil.put(LogFactory.getInstance("s: " + stringMaker.toString(), this, commonStrings.PROCESS));
        
        return stringList;
    }
    
}
