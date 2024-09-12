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

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.visitor.IncludeFileExtensionsBooleanFileVisitor;
import org.allbinary.util.BasicArrayList;

public class DirectoryOrIncludeFileExtensionBooleanFileVisitor
    extends IncludeFileExtensionsBooleanFileVisitor
{
    private final String includesString;
    
    public DirectoryOrIncludeFileExtensionBooleanFileVisitor(final BasicArrayList filterStringBasicArrayList)
    {
        super(filterStringBasicArrayList);
        
        this.includesString = null;
    }

    public DirectoryOrIncludeFileExtensionBooleanFileVisitor(final BasicArrayList filterStringBasicArrayList, final String includesString)
    {
        super(filterStringBasicArrayList);
        
        this.includesString = includesString;
    }
    
    public Boolean visit(AbFile file)
    {
        if(file.isDirectory())
        {
            return Boolean.TRUE;
        }
        
        if(this.includesString == null || file.getAbsolutePath().indexOf(this.includesString) >= 0) {
            return super.visit(file);
        } else {
            return Boolean.FALSE;
        }

    }
}
