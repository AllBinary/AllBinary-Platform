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
package org.allbinary.util;

import org.allbinary.logic.communication.log.ForcedLogUtil;

public class ImmutableBasicArrayList extends BasicArrayList
{
    public ImmutableBasicArrayList(int size)
    {
        super(size);
    }
    
    @Override
    public boolean add(Object e)
    {
        ForcedLogUtil.log("Immutable", this);
        return true;
    }

    @Override
    public void add(int index, Object element)
    {
        ForcedLogUtil.log("Immutable", this);
    }    
}