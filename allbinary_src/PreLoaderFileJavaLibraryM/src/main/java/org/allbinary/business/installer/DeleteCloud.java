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
import org.allbinary.logic.communication.http.file.upload.FileUploadData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.AbFileSystem;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

public class DeleteCloud
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    public DeleteCloud()
    {
    }

    public boolean delete(String prePath, int current, int total)
    {
        try
        {
            if (AbFileSystem.getInstance().isType("com.vobject.appengine.java.io"))
            {
                StringMaker stringBuffer = new StringMaker();

                stringBuffer.append(URLGLOBALS.getWebappPath());
                stringBuffer.append(prePath);

                AbPath path = new AbPath(stringBuffer.toString());

                //Using cloud file search currently causes problems when trying to output new file
                AbFile file = new AbFile(path);

                BasicArrayList fileBasicArrayList = Directory.getInstance().search(file, true);

                int size = fileBasicArrayList.size();

                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Searched: ");
                stringBuffer.append(path.toFileSystemString());
                stringBuffer.append(" BasicArrayList: ");
                stringBuffer.append(size);

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

                logUtil.put(
                    stringBuffer.toString(), this, "initialize()");

                for (int index = start; index < end; index++)
                {
                    AbFile nextFile = (AbFile) fileBasicArrayList.get(index);

                    try
                    {
                    	//!nextFile.isDirectory() && 
                    	if(nextFile.getPath().indexOf(FileUploadData.getInstance().FILE) < 0)
                    	{
                    		nextFile.delete();
                    	}
                    }
                    catch(Exception e)
                    {

                    }
                }

                logUtil.put("Deleted Files From Cloud", this, "initialize()");
            }

            return true;
        } catch (Exception e)
        {
            logUtil.put("Unable to copy installer files into cloud", this, "initialize()", e);
            return false;
        }
    }
}
