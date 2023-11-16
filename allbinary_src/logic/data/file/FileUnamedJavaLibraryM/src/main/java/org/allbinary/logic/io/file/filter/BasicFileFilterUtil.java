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
import java.util.Vector;
import org.allbinary.logic.io.file.visitor.IncludeFileExtensionsBooleanFileVisitor;

public class BasicFileFilterUtil
{
    
    private BasicFileFilterUtil()
    {
    }
    
    public static FileFilter getInstance(String extension)
    {
        Vector vector = new Vector();
        vector.add(extension);
        
        IncludeFileExtensionsBooleanFileVisitor 
            includeFileExtensionsBooleanFileVisitor = 
            new IncludeFileExtensionsBooleanFileVisitor(vector);
        return (FileFilter) new VisitorFileFilter(
            includeFileExtensionsBooleanFileVisitor);
    }
}
