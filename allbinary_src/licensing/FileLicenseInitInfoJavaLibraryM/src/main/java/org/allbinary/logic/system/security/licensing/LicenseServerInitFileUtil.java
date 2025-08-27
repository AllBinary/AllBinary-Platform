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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.allbinary.business.init.LicenseInitInfoUtil;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.FileStreamFactory;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.FileFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class LicenseServerInitFileUtil
{
    public static OutputStream nullOutputStream() {
        return new OutputStream() {

            @Override
            public void write(int b) throws IOException {
            }

            @Override
            public void write(byte b[], int off, int len) throws IOException {
            }

            @Override
            public void close() {
            }
        };
    }
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private final OutputStream NULL_OUTPUT_STREAM = nullOutputStream();
    
    public final void init()
    {
        try
        {

            // File file = this.getFilesDir();
            // String path = file.getAbsolutePath() + FilePathData.SEPARATOR;

            // logUtil.put("Path: " + path, this, AndroidStrings.getInstance().START);

            final String filePath = LicenseInitInfoUtil.getInstance().INITFILENAME;
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
        OutputStream fileOutputStream = NULL_OUTPUT_STREAM;
        try
        {
            final ResourceUtil resourceUtil = ResourceUtil.getInstance();

            final String filePath = LicenseInitInfoUtil.getInstance().INITFILENAME;

            final InputStream inputStream = resourceUtil.getResourceAsStream(filePath);

            logUtil.put("Writing Default License File", this, commonStrings.INIT);

            final FileStreamFactory fileStreamFactory = FileStreamFactory.getInstance();

            fileOutputStream = fileStreamFactory.getFileOutputStreamInstance(
                StringUtil.getInstance().EMPTY_STRING, filePath);

            int b;
            int index = 0;
            while (true)
            {
                b = inputStream.read();
                if(b == -1) {
                    break;
                }

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
