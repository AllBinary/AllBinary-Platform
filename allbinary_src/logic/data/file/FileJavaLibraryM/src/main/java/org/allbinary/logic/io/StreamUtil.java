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
package org.allbinary.logic.io;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class StreamUtil {

    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final StreamUtil instance = new StreamUtil();

    /**
     * @return the instance
     */
    public static StreamUtil getInstance() {
        return instance;
    }

    private StreamUtil() {
    }

    /*
     * public static ByteArrayOutputStream get( InputStream in) throws Exception
     * { ByteArrayOutputStream buffer = new ByteArrayOutputStream();
     *
     * int ch;
     *
     * while ((ch = in.read()) != -1) { buffer.write((byte) ch); }
     *
     * in.close();
     *
     * return buffer; }
     */
    public OutputStream get(final InputStream inputStream,
        final OutputStream outputStream, byte[] buffer) throws Exception {
        //int total = 0;
        int len = 0;
        while (true) {
            len = inputStream.read(buffer, 0, buffer.length);

            if (len != -1) {
            } else {
                break;
            }

            outputStream.write(buffer, 0, len);
            //total = total + len;
        }

        /*
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILE))
        {
        logUtil.put("Total Bytes Written: " + total, this, commonStrings.GET);
        }
         */
        return outputStream;
    }

//    public byte[] getByteArray(final InputStream inputStream, final byte[] byteArray) 
//        throws Exception
//    {
//        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(8000);
//        return this.getByteArray(inputStream, outputStream, byteArray);
//    }
    public byte[] getByteArray(final InputStream inputStream, final ByteArrayOutputStream outputStream2, final byte[] byteArray)
        throws Exception {
        final ByteArrayOutputStream outputStream = outputStream2;
        try {
            this.get(inputStream, outputStream, byteArray);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            this.close(outputStream);
        }
    }

//    public String getAsString(final InputStream inputStream, final byte[] byteArray1) throws Exception {
//        final byte[] byteArray = this.getByteArray(inputStream, byteArray1);
//        return new String(byteArray);
//    }
    public boolean close(Closeable closeable) {
        try {
            if (closeable != null) {
                //logUtil.put("Closing: " + closeable, this, CLOSE);
                closeable.close();
            }
            return true;
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CLOSE, e);
            return false;
        }
    }
}
