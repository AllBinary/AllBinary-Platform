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
package org.allbinary.logic.util.visitor;

import org.allbinary.logic.NullUtil;

public class Visitor implements VisitorInterface
{
    private static final Visitor instance = new Visitor();
    
    protected final NullUtil nullUtil = NullUtil.getInstance();
    
    @Override
    public Object visit(Object object)
    {
        return nullUtil.NULL_OBJECT;
    }

    public static Visitor getInstance()
    {
        return instance;
    }
}
