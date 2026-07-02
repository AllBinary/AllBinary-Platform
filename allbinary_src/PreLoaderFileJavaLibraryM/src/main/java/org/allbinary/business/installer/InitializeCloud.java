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

import org.allbinary.globals.PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.AbIOSystem;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class InitializeCloud
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public static final String CLOUD = "cloud/";
    public static final String CLOUD_UPDATE = "cloudupdate/";

    public InitializeCloud()
    {
    }

    public boolean initialize(final String cloud,
        final boolean overwriteNewer, final boolean overwriteAll, final int current, final int total)
    {
        try
        {
            if (AbIOSystem.getInstance().isType("com.vobject.appengine.java.io"))
            {
                //Copy all installer files into cloud

                final StringMaker stringBuffer = new StringMaker();
                stringBuffer.append(URLGLOBALS.getWebappPath());
                stringBuffer.append(cloud);
                stringBuffer.append(PATH_GLOBALS.getInstance().DATA_PATH);

                final AbPath path = new AbPath(stringBuffer.toString(), StringUtil.getInstance().EMPTY_STRING);

                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(URLGLOBALS.getWebappPath());
                //stringBuffer.append(PATH_GLOBALS.getInstance().DATA_PATH);

                final AbPath realPath = new AbPath(stringBuffer.toString(), StringUtil.getInstance().EMPTY_STRING);

                //Using cloud file search currently causes problems when trying to output new file
                final AbFile file = AbFile.createAbFileFromAbPath(path);

                final BasicArrayList fileBasicArrayList = Directory.getInstance().search(file, true);

                final int size = fileBasicArrayList.size();

                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append("Searched: ");
                stringBuffer.append(path.toFileSystemString());
                stringBuffer.append(" BasicArrayList: ");
                stringBuffer.appendint(size);

                //Add one to round up so files will not be missed
                final int portion = size / total + 1;

                final int start = portion * current;

                int end = start + portion;

                if(end > size)
                {
                    end = size;
                }

                stringBuffer.append(" Section: ");
                stringBuffer.appendint(start);
                stringBuffer.append(" - ");
                stringBuffer.appendint(end);

                this.logUtil.putF(
                    stringBuffer.toString(), this, "initialize()");

                AbFile nextFile;
                for (int index = start; index < end; index++)
                {
                    nextFile = (AbFile) fileBasicArrayList.get(index);

                    if (nextFile.isDirectory())
                    {
                        //Directory.create(nextFile.getPath());
                    } else
                    {
                        FileUtil.getInstance().copyToCloud(nextFile, path, realPath, cloud, overwriteNewer, overwriteAll);
                    }
                }

                this.logUtil.putF("Copied Files To Cloud", this, "initialize()");
            }

            return true;
        } catch (Exception e)
        {
            this.logUtil.put("Unable to copy installer files into cloud", this, "initialize()", e);
            return false;
        }
    }
}
