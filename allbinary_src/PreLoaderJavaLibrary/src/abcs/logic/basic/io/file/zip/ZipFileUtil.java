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
package abcs.logic.basic.io.file.zip;

import abcs.logic.basic.io.AbDataOutputStream;
import abcs.logic.basic.io.AbFileInputStream;
import abcs.logic.basic.io.AbFileOutputStream;
import abcs.logic.basic.io.DataOutputStreamFactory;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.FileUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author user
 */
public class ZipFileUtil
{

    private static final ZipFileUtil instance = new ZipFileUtil();

    /**
     * @return the instance
     */
    public static ZipFileUtil getInstance()
    {
        return instance;
    }

    public void create(String outFilename, Vector fileVector)
    {
        try
        {
            final AbFile zipFile = new AbFile(outFilename);

            if (zipFile.exists())
            {
                zipFile.delete();
            }
            zipFile.createNewFile();

            final ZipOutputStream outputStream = new ZipOutputStream(
                new AbFileOutputStream(zipFile));

            final StreamUtil streamUtil = StreamUtil.getInstance();

            final StringBuffer stringBuffer = new StringBuffer();

            final byte[] byteArray = new byte[16384];

            AbFileInputStream fileInputStream;
            final int size = fileVector.size();
            int current = 0;

            for (int i = 0; i < size; i++)
            {
                AbFile file = (AbFile) fileVector.get(i);

                if (file.isDirectory())
                {
                    //do not zip directories
                } else if (file.isFile())
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append(current);
                    stringBuffer.append(" Creating Zip File Entry: ");
                    stringBuffer.append(file.getPath());

                    LogUtil.put(LogFactory.getInstance(
                        stringBuffer.toString(), this, "create()"));

                    try
                    {
                        fileInputStream = new AbFileInputStream(file);

                        outputStream.putNextEntry(new ZipEntry(file.getPath()));

                        streamUtil.get(fileInputStream, outputStream, byteArray);

                        outputStream.closeEntry();

                        streamUtil.close(fileInputStream);
                    } catch (Exception e)
                    {
                        LogUtil.put(LogFactory.getInstance(
                            "Skipping File (Probably Local): " + file.getPath(), this, "create()"));
                    }
                }
                current++;
            }

            streamUtil.close(outputStream);

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "create", e));
        }
    }

    public void unzip(String path, AbFile zipFile, String fileName)
        throws Exception
    {
        byte[] buffer = new byte[16384];

        ZipInputStream inputStream = null;
        try
        {
            inputStream = new ZipInputStream(
                new AbFileInputStream(zipFile));

            ZipEntry zipEntry;

            final StringBuffer stringBuffer = new StringBuffer();

            final FileUtil fileUtil = FileUtil.getInstance();
            
            while ((zipEntry = inputStream.getNextEntry()) != null)
            {
                String entryName = zipEntry.getName();

                if (zipEntry.isDirectory())
                {
                } else
                {
                    AbFile entryFile = new AbFile(path + entryName);

                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("ZipEntry Name: ");
                    stringBuffer.append(entryName);
                    stringBuffer.append(" isFile: ");
                    stringBuffer.append(entryFile.isFile());
                    stringBuffer.append(" isDirectory: ");
                    stringBuffer.append(entryFile.isDirectory());
                    stringBuffer.append(" getParent: ");
                    stringBuffer.append(entryFile.getParent());

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "unzip"));

                    AbDataOutputStream dataOutputStream =
                        DataOutputStreamFactory.getInstance().getInstance(entryFile);

                    fileUtil.write(inputStream, dataOutputStream, buffer);
                }

                inputStream.closeEntry();
            }
        } finally
        {
            StreamUtil.getInstance().close(inputStream);
        }
    }
}
