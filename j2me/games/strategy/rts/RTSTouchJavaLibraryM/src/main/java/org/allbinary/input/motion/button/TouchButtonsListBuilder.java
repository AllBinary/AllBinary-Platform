/*
 * AllBinary Open License Version 1
 * Copyright (c) 2007 AllBinary
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

package org.allbinary.input.motion.button;

import org.allbinary.util.BasicArrayList;

public class TouchButtonsListBuilder
extends BaseTouchInput
{
    private final BasicArrayList baseTouchInputList = new BasicArrayList();
    
    protected void add(BasicArrayList list)
    {
        int size = baseTouchInputList.size();
        for(int index = 0; index < size; index++)
        {
            list.addAll(((BaseTouchInput) baseTouchInputList.get(index)).getList());
        }
    }

    public void add(BaseTouchInput baseTouchInput)
    {
        this.baseTouchInputList.add(baseTouchInput);
    }

}
