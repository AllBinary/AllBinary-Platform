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
package org.allbinary.logic.io.file.directory;

import java.io.FileFilter;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.util.BasicArrayList;

public class SubDirectory
{
    private static final SubDirectory instance = new SubDirectory();
    
    public static SubDirectory getInstance() {
        return instance;
    }
    
    private final Directory directory = Directory.getInstance();
    
    private SubDirectory()
    {
    }

    // Find the files matching the FileFilter in the given directory
    public BasicArrayList search(FileFilter fileFilter, AbFile file)
    {
        return directory.search(fileFilter, file, true);
    }

    // Return the files in the given directory
    public BasicArrayList search(AbFile file)
    {
        return directory.search(file, true);
    }

    // Find the files matching the searchValue in the given directory
    public BasicArrayList search(String searchValue, AbFile file)
    {
        return directory.search(searchValue, file, true);
    }

    public BasicArrayList search(int level, AbFile file)
    {
        return directory.search(level, file, true);
    }

    public static String toString(BasicArrayList files)
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
