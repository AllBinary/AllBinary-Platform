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
package abcs.business.installer;

import java.util.Vector;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.io.AbFileSystem;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.FileUtil;
import abcs.logic.basic.io.file.directory.Directory;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.globals.PATH_GLOBALS;

public class InitializeCloud
{
    public static final String CLOUD = "cloud/";
    public static final String CLOUD_UPDATE = "cloudupdate/";

    public InitializeCloud()
    {
    }

    public boolean initialize(String cloud,
        boolean overwriteNewer, boolean overwriteAll, int current, int total)
    {
        try
        {
            if (AbFileSystem.getInstance().isType("com.vobject.appengine.java.io"))
            {
                //Copy all installer files into cloud

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(URLGLOBALS.getWebappPath());
                stringBuffer.append(cloud);
                stringBuffer.append(PATH_GLOBALS.getInstance().DATA_PATH);

                AbPath path = new AbPath(stringBuffer.toString());

                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(URLGLOBALS.getWebappPath());
                //stringBuffer.append(PATH_GLOBALS.getInstance().DATA_PATH);

                AbPath realPath = new AbPath(stringBuffer.toString());

                //Using cloud file search currently causes problems when trying to output new file
                AbFile file = new AbFile(path);

                Vector fileVector = Directory.getInstance().search(file, true);

                int size = fileVector.size();

                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append("Searched: ");
                stringBuffer.append(path.toFileSystemString());
                stringBuffer.append(" Vector: ");
                stringBuffer.append(size);

                //Add one to round up so files will not be missed
                int portion = size / total + 1;

                int start = portion * current;

                int end = start + portion;

                if(end > size)
                {
                    end = size;
                }

                stringBuffer.append(" Section: ");
                stringBuffer.append(start);
                stringBuffer.append(" - ");
                stringBuffer.append(end);

                LogUtil.put(LogFactory.getInstance(
                    stringBuffer.toString(), this, "initialize()"));

                for (int index = start; index < end; index++)
                {
                    AbFile nextFile = (AbFile) fileVector.get(index);

                    if (nextFile.isDirectory())
                    {
                        //Directory.create(nextFile.getPath());
                    } else
                    {
                        FileUtil.getInstance().copyToCloud(nextFile, path, realPath, cloud, overwriteNewer, overwriteAll);
                    }
                }

                LogUtil.put(LogFactory.getInstance("Copied Files To Cloud", this, "initialize()"));
            }

            return true;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to copy installer files into cloud", this, "initialize()", e));
            return false;
        }
    }
}
