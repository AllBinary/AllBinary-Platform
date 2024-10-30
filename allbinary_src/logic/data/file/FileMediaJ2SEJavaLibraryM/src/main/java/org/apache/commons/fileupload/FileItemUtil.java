package org.apache.commons.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class FileItemUtil {

    private static final FileItemUtil instance = new FileItemUtil();
    
    public static final String DEFAULT_CHARSET = "ISO-8859-1";

    public static String getString(byte[] byteArray)
        throws Exception
    {
        return new String(byteArray, DEFAULT_CHARSET);
    }

    public static String getString(FileItemStream fileItem)
        throws Exception
    {
        return new String(FileItemUtil.getBytes(fileItem), DEFAULT_CHARSET);
    }
    
    public static byte[] getBytes(FileItemStream fileItem)
        throws Exception
    {
        final StreamUtil streamUtil = StreamUtil.getInstance();
        
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

    	try
    	{
            inputStream = fileItem.openStream();
            outputStream = new ByteArrayOutputStream();

        if (fileItem.isFormField())
        {
            LogUtil.put(LogFactory.getInstance("FileItemStream FieldName: " + fileItem.getFieldName(), instance, "write()"));
        } else
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Uploaded File FieldName: ");
            stringBuffer.append(fileItem.getFieldName());
            stringBuffer.append(" name = ");
            stringBuffer.append(fileItem.getName());

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "write()"));
        }

        outputStream = (ByteArrayOutputStream) 
            streamUtil.get(inputStream, outputStream, new byte[16384]);

        return outputStream.toByteArray();
 	   }
        finally
        {
        	streamUtil.close(outputStream);
        	streamUtil.close(inputStream);
        }      
    }
    
    public static void write(FileItem fileItem, AbFile file)
        throws Exception
    {
        fileItem.write(AbFileNativeUtil.get(file));
    }
}
