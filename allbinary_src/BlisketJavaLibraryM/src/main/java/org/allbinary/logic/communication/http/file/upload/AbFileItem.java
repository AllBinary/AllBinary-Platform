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


import java.io.File;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.string.StringValidationUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemUtil;

/**
 *
 * @author user
 */
public class AbFileItem
    implements FileItem
{
    private final String name;
    private String fieldName;
    private final byte[] byteArray;

    public AbFileItem(String name, String fieldName, byte[] byteArray)
    {
        this.name = name;
        this.fieldName = fieldName;
        this.byteArray = byteArray;
    }

    public InputStream getInputStream() throws IOException
    {
        throw new IOException(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    public String getContentType()
    {
        return null;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isInMemory()
    {
        return true;
    }

    public long getSize()
    {
        return this.byteArray.length;
    }

    public byte[] get()
    {
        return this.byteArray;
    }

    public String getString(String encoding) throws UnsupportedEncodingException
    {
        return new String(this.byteArray, encoding);
    }

    public String getString()
    {
        try
        {
            return FileItemUtil.getString(byteArray);
        }
        catch(Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPERROR))
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "getString", e));
            }
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }

    public void write(File file) throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
    
    public void write(AbFile file) throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    public void delete()
    {
        ForcedLogUtil.log(CommonStrings.getInstance().NOT_IMPLEMENTED, "delete()");
    }

    public String getFieldName()
    {
        return this.fieldName;
    }

    public void setFieldName(String name)
    {
        this.fieldName = name;
    }

    public boolean isFormField()
    {
        if(StringValidationUtil.getInstance().isEmpty(this.fieldName))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void setFormField(boolean state)
    {

    }

    public OutputStream getOutputStream()
        throws IOException
    {
        throw new IOException(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
}
