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
    
    protected void add(final BasicArrayList list)
    {
        final int size = baseTouchInputList.size();
        BaseTouchInput baseTouchInput;
        for(int index = 0; index < size; index++)
        {
            baseTouchInput = (BaseTouchInput) baseTouchInputList.get(index);
            list.addAll(baseTouchInput.getList());
        }
    }

    public void add(BaseTouchInput baseTouchInput)
    {
        this.baseTouchInputList.add(baseTouchInput);
    }

}
