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
import allbinary.logic.communication.http.AbFileItemFactory;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemUtil;

public class AbFileUploadFactory
{
    private static final AbFileUploadFactory instance = new AbFileUploadFactory();

    private AbFileUploadFactory()
    {
    }

    public static List getFileItemStreamList(HttpServletRequest httpServletRequest) throws Exception
    {
        try
        {
            AbFileUpload fileUpload = new AbFileUpload(new AbFileItemFactory());
            FileItemIterator iterator = fileUpload.getItemIterator(httpServletRequest);
            List items = new ArrayList();
            while (iterator.hasNext())
            {
                FileItemStream fileItemStream = iterator.next();

                HttpFileUploadUtil.log(fileItemStream);

                AbFileItem fileItem = new AbFileItem(
                    fileItemStream.getName(),
                    fileItemStream.getFieldName(),
                    FileItemUtil.getBytes(fileItemStream));
                
                items.add(fileItem);
            }
            return items;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.HTTPERROR))
            {
                String error = "Failed to parse Uploaded Files";
                LogUtil.put(LogFactory.getInstance(error, instance, "get()", e));
            }
            throw e;
        }
    }

    //Returns a list of FileItems
    public static List getFileItemList(HttpServletRequest httpServletRequest) throws Exception
    {
        try
        {
            AbFileUpload abFileUpload = new AbFileUpload(new AbFileItemFactory());
            return abFileUpload.parseRequest(httpServletRequest);
        } catch (Exception e)
        {
            String error = "Failed to parse Uploaded Files";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.HTTPERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, instance, "get()", e));
            }
            throw e;
        }
    }
}
