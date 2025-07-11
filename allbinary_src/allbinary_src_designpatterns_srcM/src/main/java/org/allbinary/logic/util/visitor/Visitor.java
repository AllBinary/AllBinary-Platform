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

public class Visitor implements VisitorInterface
{
    private static final Visitor instance = new Visitor();
    
    @Override
    public Object visit(Object object)
    {
        return null;
    }

    public static Visitor getInstance()
    {
        return instance;
    }
}
