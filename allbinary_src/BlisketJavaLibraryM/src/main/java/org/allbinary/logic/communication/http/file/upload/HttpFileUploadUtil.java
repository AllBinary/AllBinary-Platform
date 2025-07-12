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
package org.allbinary.logic.communication.http.file.upload;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.object.clazz.ClassUtil;
import org.allbinary.logic.string.StringMaker;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemStream;

public class HttpFileUploadUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();


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
        final LogUtil logUtil = LogUtil.getInstance();
        final StringMaker stringBuffer = new StringMaker();

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

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTP))
        {
            logUtil.put(stringBuffer.toString(), getInstance(), "log()");
        }
    }

    public static void log(FileItem fileItem)
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final StringMaker stringBuffer = new StringMaker();

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

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
        {
            logUtil.put(stringBuffer.toString(), getInstance(), "log()");
        }
    }
}
