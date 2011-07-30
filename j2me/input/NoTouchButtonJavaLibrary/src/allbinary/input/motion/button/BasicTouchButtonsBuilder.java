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
package allbinary.input.motion.button;

import abcs.logic.basic.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class BasicTouchButtonsBuilder
    extends BaseTouchInput
{
    private static final BasicTouchButtonsBuilder SINGLETON = new BasicTouchButtonsBuilder();

    public static final BaseTouchInput getInstance()
    {
        return SINGLETON;
    }

    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().GET_LIST));
            
            final BasicArrayList list = new BasicArrayList();
            
            return list;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_LIST, e));
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
