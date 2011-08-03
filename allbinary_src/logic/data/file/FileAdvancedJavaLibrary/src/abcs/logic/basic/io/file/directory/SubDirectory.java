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
package abcs.logic.basic.io.file.directory;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.string.CommonSeps;

import java.io.FileFilter;
import java.util.Vector;

public class SubDirectory
{
    public SubDirectory()
    {
    }

    // Find the files matching the FileFilter in the given directory
    public Vector search(FileFilter fileFilter, AbFile file)
    {
        return Directory.getInstance().search(fileFilter, file, true);
    }

    // Return the files in the given directory
    public Vector search(AbFile file)
    {
        return Directory.getInstance().search(file, true);
    }

    // Find the files matching the searchValue in the given directory
    public Vector search(String searchValue, AbFile file)
    {
        return Directory.getInstance().search(searchValue, file, true);
    }

    public Vector search(int level, AbFile file)
    {
        return Directory.getInstance().search(level, file, true);
    }

    public static String toString(Vector files)
    {
        StringBuffer stringBuffer = new StringBuffer();

        final String NEW_LINE = CommonSeps.getInstance().NEW_LINE;
        
        for (int index = 0; index < files.size(); index++)
        {
            AbFile file = (AbFile) files.get(index);
            stringBuffer.append(file.getPath());
            stringBuffer.append(NEW_LINE);
        }
        return stringBuffer.toString();
    }
}
