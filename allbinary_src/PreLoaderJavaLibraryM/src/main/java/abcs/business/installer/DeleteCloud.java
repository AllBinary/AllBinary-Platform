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
import abcs.logic.basic.io.file.directory.Directory;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.http.file.upload.FileUploadData;

public class DeleteCloud
{

    public DeleteCloud()
    {
    }

    public boolean delete(String prePath, int current, int total)
    {
        try
        {
            if (AbFileSystem.getInstance().isType("com.vobject.appengine.java.io"))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append(URLGLOBALS.getWebappPath());
                stringBuffer.append(prePath);

                AbPath path = new AbPath(stringBuffer.toString());

                //Using cloud file search currently causes problems when trying to output new file
                AbFile file = new AbFile(path);

                Vector fileVector = Directory.getInstance().search(file, true);

                int size = fileVector.size();

                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Searched: ");
                stringBuffer.append(path.toFileSystemString());
                stringBuffer.append(" Vector: ");
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

                LogUtil.put(LogFactory.getInstance(
                    stringBuffer.toString(), this, "initialize()"));

                for (int index = start; index < end; index++)
                {
                    AbFile nextFile = (AbFile) fileVector.get(index);

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

                LogUtil.put(LogFactory.getInstance("Deleted Files From Cloud", this, "initialize()"));
            }

            return true;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to copy installer files into cloud", this, "initialize()", e));
            return false;
        }
    }
}
