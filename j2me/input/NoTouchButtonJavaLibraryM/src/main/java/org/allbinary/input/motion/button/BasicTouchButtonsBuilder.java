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
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class BasicTouchButtonsBuilder
    extends BaseTouchInput
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BasicTouchButtonsBuilder SINGLETON = new BasicTouchButtonsBuilder();

    public static final BaseTouchInput getInstance()
    {
        return SINGLETON;
    }
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    public BasicArrayList getList()
    {
        try
        {
            logUtil.put(commonStrings.START, this, commonStrings.GET_LIST);
            
            final BasicArrayList list = new BasicArrayList();
            
            return list;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_LIST, e);
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
