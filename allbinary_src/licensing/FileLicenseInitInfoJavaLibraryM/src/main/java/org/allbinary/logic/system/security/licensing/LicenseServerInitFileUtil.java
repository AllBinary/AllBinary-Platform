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
package org.allbinary.logic.system.security.licensing;

import java.io.InputStream;
import java.io.OutputStream;

import org.allbinary.business.init.LicenseInitInfoUtil;
import org.allbinary.logic.io.FileStreamFactory;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.FileFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.resource.ResourceUtil;

public class LicenseServerInitFileUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    public final void init()
    {
        try
        {

            // File file = this.getFilesDir();
            // String path = file.getAbsolutePath() + FilePathData.SEPARATOR;

            // logUtil.put("Path: " + path, this, AndroidStrings.getInstance().START);

            String filePath = LicenseInitInfoUtil.getInstance().INITFILENAME;
            LicenseInitInfoUtil.getInstance().setFilePath(StringUtil.getInstance().EMPTY_STRING);

            //PreLogUtil.put("SecurityManager = " + System.getSecurityManager(), this, commonStrings.INIT);

            if (FileFactory.getInstance().isFile(filePath))
            {
                logUtil.put("Using Existing License File", this, commonStrings.INIT);
            } else
            {
                write();
            }
        } catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        }
    }

    private void write()
    {
        OutputStream fileOutputStream = null;
        try
        {
            ResourceUtil resourceUtil = ResourceUtil.getInstance();

            String filePath = LicenseInitInfoUtil.getInstance().INITFILENAME;

            InputStream inputStream = resourceUtil.getResourceAsStream(filePath);

            logUtil.put("Writing Default License File", this, commonStrings.INIT);

            FileStreamFactory fileStreamFactory = FileStreamFactory.getInstance();

            fileOutputStream = fileStreamFactory.getFileOutputStreamInstance(
                StringUtil.getInstance().EMPTY_STRING, filePath);

            int b;
            int index = 0;
            while ((b = inputStream.read()) != -1)
            {
                fileOutputStream.write(b);
                index++;
            }

            logUtil.put("Wrote Bytes: " + index, this, commonStrings.INIT);

            fileOutputStream.flush();
        } catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        } finally
        {
            StreamUtil.getInstance().close(fileOutputStream);
        }
    }
}
