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
package org.allbinary.logic.io.file.filter;

import java.io.FileFilter;

import org.allbinary.logic.io.file.visitor.IncludeFileExtensionsBooleanFileVisitor;
import org.allbinary.util.BasicArrayList;

public class BasicFileFilterUtil
{
    
    private BasicFileFilterUtil()
    {
    }
    
    public static FileFilter getInstance(final String extension)
    {
        final BasicArrayList vector = new BasicArrayList();
        vector.add(extension);
        
        final IncludeFileExtensionsBooleanFileVisitor 
            includeFileExtensionsBooleanFileVisitor = 
            new IncludeFileExtensionsBooleanFileVisitor(vector);
        return (FileFilter) new VisitorFileFilter(
            includeFileExtensionsBooleanFileVisitor);
    }
}
