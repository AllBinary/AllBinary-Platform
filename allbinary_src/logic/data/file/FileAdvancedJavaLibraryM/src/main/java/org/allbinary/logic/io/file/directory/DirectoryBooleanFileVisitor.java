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
import org.allbinary.logic.util.visitor.VisitorInterface;

public class DirectoryBooleanFileVisitor
    implements VisitorInterface {

    public DirectoryBooleanFileVisitor() {

    }

    public Object visit(Object object) {
        return (Object) this.visit((AbFile) object);
    }

    public Boolean visit(AbFile file) {
        if (file.isDirectory()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
