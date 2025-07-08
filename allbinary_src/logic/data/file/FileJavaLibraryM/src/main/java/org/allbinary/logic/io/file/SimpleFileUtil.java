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
import java.util.List;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class SimpleFileUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    protected static final SimpleFileUtil instance = new SimpleFileUtil();

    /**
     * @return the instance
     */
    public static SimpleFileUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final StreamUtil streamUtil = StreamUtil.getInstance();
    
    public BasicArrayList loadFileAsList(final InputStream inputStream, final int max, final byte[] byteArray1) {
        return this.loadFileAsList(inputStream, max, byteArray1, 0);
    }
    
    public BasicArrayList loadFileAsList(final InputStream inputStream, final int max, final byte[] byteArray1, final int includeReturnLine) {

        final BasicArrayList stringList = new BasicArrayList();
        
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
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.ADD, e);
        }

        if(byteArray == null) {
            return stringList;
        }

        //final StringMaker stringMaker = new StringMaker();
        
        final int size = byteArray.length;
        //logUtil.put("size: " + size, this, commonStrings.PROCESS);
        int index = 0;
        int startIndex;
        int returnLine = 0;
        while(index < size) {
            //logUtil.put("index" + index, this, commonStrings.PROCESS);
            startIndex = index;
            while(index < size - 1 && byteArray[index] != '\n') {
                index++;
            }
            
            if(includeReturnLine == 0) {
                if (index > 0 && byteArray[index - 1] == '\r') {
                    returnLine = 1;
                } else {
                    returnLine = 0;
                }
            }
            
            final String s = new String(byteArray, startIndex, (index + includeReturnLine - returnLine - startIndex));
            stringList.add(s);
            //stringMaker.append(s).append('\n');
            index++;
        }
        
        //logUtil.put("s: " + stringMaker.toString(), this, commonStrings.PROCESS);
        
        return stringList;
    }

    public String createStringFromArrayOfStrings(final String[] stringArray) {
        final StringMaker stringMaker = new StringMaker();
        
        final int size = stringArray.length;
        for(int index = 0; index < size; index++) {
            stringMaker.append(stringArray[index]);
        }
        
        return stringMaker.toString();
    }
    
}
