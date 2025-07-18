/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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
package org.allbinary.game.layer;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.util.visitor.Visitor;

public class SteeringVisitor extends Visitor
{
    private final BasicArrayList list = new BasicArrayList();
    
    public BasicArrayList getList()
    {
        return list;
    }
}
