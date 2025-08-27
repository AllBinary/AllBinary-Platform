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
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;

import org.allbinary.logic.NullUtil;
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

    
    private static final SimpleFileUtil instance = new SimpleFileUtil();

    /**
     * @return the instance
     */
    public static SimpleFileUtil getInstance() {
        return instance;
    }

    public static Writer nullWriter() {
        return new Writer() {
            private volatile boolean closed;

            @Override
            public Writer append(char c) throws IOException {
                return this;
            }

            @Override
            public Writer append(CharSequence csq) throws IOException {
                return this;
            }

            @Override
            public Writer append(CharSequence csq, int start, int end) throws IOException {
                return this;
            }

            @Override
            public void write(int c) throws IOException {
            }

            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
            }

            @Override
            public void write(String str) throws IOException {
            }

            @Override
            public void write(String str, int off, int len) throws IOException {
            }

            @Override
            public void flush() throws IOException {
            }

            @Override
            public void close() throws IOException {
            }
        };
    }
    
    public static Reader nullReader() {
        return new Reader() {

            @Override
            public int read() throws IOException {
                return -1;
            }

            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return -1;
            }

            @Override
            public int read(CharBuffer target) throws IOException {
                return -1;
            }

            @Override
            public boolean ready() throws IOException {
                return false;
            }

            @Override
            public long skip(long n) throws IOException {
                return 0L;
            }

            @Override
            public void close() {
            }
        };
    }
    
    public final Writer NULL_WRITER = nullWriter(); 
    public final Reader NULL_READER = nullReader(); 

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final NullUtil nullUtil = NullUtil.getInstance();
    private final StreamUtil streamUtil = StreamUtil.getInstance();
    
    public BasicArrayList loadFileAsList(final InputStream inputStream, final int max, final byte[] byteArray1) {
        return this.loadFileAsList(inputStream, max, byteArray1, 0);
    }
    
    public BasicArrayList loadFileAsList(final InputStream inputStream, final int max, final byte[] byteArray1, final int includeReturnLine) {

        final BasicArrayList stringList = new BasicArrayList();
        
        if(inputStream == null) {
            return stringList;
        }
        
        byte[] byteArray = nullUtil.NULL_BYTE_ARRAY;
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
            while(index < size - 1 && byteArray[index] != (byte) '\n') {
                index++;
            }

            if(includeReturnLine == 0) {
                if (index > 0 && byteArray[index - 1] == (byte) '\r') {
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
