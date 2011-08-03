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
import java.util.Vector;

import abcs.logic.basic.io.file.visitor.IncludeFileExtensionsBooleanFileVisitor;

public class DirectoryOrIncludeFileExtensionBooleanFileVisitor
    extends IncludeFileExtensionsBooleanFileVisitor
{
    public DirectoryOrIncludeFileExtensionBooleanFileVisitor(Vector filterStringVector)
    {
        super(filterStringVector);
    }
    
    public Boolean visit(AbFile file)
    {
        if(file.isDirectory())
        {
            return Boolean.TRUE;
        }
        
        return super.visit(file);
    }
}
