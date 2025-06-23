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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.http.AbFileItemFactory;

public class AbFileUploadFactory
{
    private static final AbFileUploadFactory instance = new AbFileUploadFactory();

    public static AbFileUploadFactory getInstance() {
        return instance;
    }
    
    private AbFileUploadFactory()
    {
    }

    public List getFileItemStreamList(HttpServletRequest httpServletRequest) throws Exception
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
                    FileItemUtil.getInstance().getBytes(fileItemStream));
                
                items.add(fileItem);
            }
            return items;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPERROR))
            {
                String error = "Failed to parse Uploaded Files";
                LogUtil.put(LogFactory.getInstance(error, this, "get()", e));
            }
            throw e;
        }
    }

    //Returns a list of FileItems
    public List getFileItemList(HttpServletRequest httpServletRequest) throws Exception
    {
        try
        {
            AbFileUpload abFileUpload = new AbFileUpload(new AbFileItemFactory());
            return abFileUpload.parseRequest(httpServletRequest);
        } catch (Exception e)
        {
            String error = "Failed to parse Uploaded Files";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "get()", e));
            }
            throw e;
        }
    }
}
