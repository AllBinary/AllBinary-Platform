/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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

import java.io.File;
import java.io.FileFilter;

import org.allbinary.logic.util.visitor.VisitorInterface;

public class DirectoryFileVisitor implements VisitorInterface, FileFilter
{
    public DirectoryFileVisitor()
    {
    }
    
    public boolean accept(java.io.File arg0)
    {
    	return ((Boolean) this.visit((Object) arg0)).booleanValue();
    }
    
    public Object visit(Object object)
    {
        File file = (File) object;
        int index = 1;
        
        File aFile = file.getParentFile();
        while((aFile = aFile.getParentFile()) != null)
        {
        	index++;
        }
        
        if(file.isDirectory() && index < 5)
        {
            return Boolean.TRUE;
        }
        
        return Boolean.FALSE;
    }
}
