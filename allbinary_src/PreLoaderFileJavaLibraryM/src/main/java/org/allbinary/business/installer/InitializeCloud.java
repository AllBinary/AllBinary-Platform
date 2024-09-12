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
package org.allbinary.business.installer;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.AbFileSystem;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.globals.PATH_GLOBALS;
import org.allbinary.util.BasicArrayList;

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

                final StringBuffer stringBuffer = new StringBuffer();
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

                BasicArrayList fileBasicArrayList = Directory.getInstance().search(file, true);

                int size = fileBasicArrayList.size();

                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append("Searched: ");
                stringBuffer.append(path.toFileSystemString());
                stringBuffer.append(" BasicArrayList: ");
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
                    AbFile nextFile = (AbFile) fileBasicArrayList.get(index);

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
