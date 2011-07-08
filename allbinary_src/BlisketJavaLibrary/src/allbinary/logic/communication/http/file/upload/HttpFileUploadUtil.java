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
package allbinary.logic.communication.http.file.upload;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.object.clazz.ClassUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemStream;

public class HttpFileUploadUtil
{

    private static final HttpFileUploadUtil instance = new HttpFileUploadUtil();

    /**
     * @return the instance
     */
    public static HttpFileUploadUtil getInstance()
    {
        return instance;
    }

    private HttpFileUploadUtil()
    {
    }

    public boolean isValid(Object object)
        throws Exception
    {
        if (object != null && !(object instanceof String))
        {
            if (object instanceof FileItem)
            {
                return true;
            }
            else
            {
                throw new Exception("Object Not Instance Of FileItem but is: " + ClassUtil.viewAll(object, "\n"));
            }
        }
        return false;
    }

    public static void log(FileItemStream fileItem)
        throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("FileItem Log:");
        stringBuffer.append("\n");
        stringBuffer.append("Content Type:");
        stringBuffer.append(fileItem.getContentType());
        stringBuffer.append("\n");
        stringBuffer.append("Field Name:");
        stringBuffer.append(fileItem.getFieldName());
        stringBuffer.append("\n");
        stringBuffer.append("Name:");
        stringBuffer.append(fileItem.getName());
        stringBuffer.append("\n");
        stringBuffer.append("Size:");
        //result.append(fileItem.openStream().available());
        stringBuffer.append(" Not Calculated Yet");

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.HTTP))
        {
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "log()"));
        }
    }

    public static void log(FileItem fileItem)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("FileItem Log:");
        stringBuffer.append("\n");
        stringBuffer.append("Content Type:");
        stringBuffer.append(fileItem.getContentType());
        stringBuffer.append("\n");
        stringBuffer.append("Field Name:");
        stringBuffer.append(fileItem.getFieldName());
        stringBuffer.append("\n");
        stringBuffer.append("Name:");
        stringBuffer.append(fileItem.getName());
        stringBuffer.append("\n");
        stringBuffer.append("Size:");
        stringBuffer.append(fileItem.getSize());

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
        {
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), getInstance(), "log()"));
        }
    }
}
