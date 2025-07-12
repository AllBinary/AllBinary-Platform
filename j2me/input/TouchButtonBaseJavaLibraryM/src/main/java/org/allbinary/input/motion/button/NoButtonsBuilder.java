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
package org.allbinary.input.motion.button;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class NoButtonsBuilder 
extends BaseTouchInput
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    public BasicArrayList getList()
    {
        try
        {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

            final BasicArrayList list = BasicArrayListUtil.getInstance().getImmutableInstance();
            
            return list;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_LIST, e);
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
