/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
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
