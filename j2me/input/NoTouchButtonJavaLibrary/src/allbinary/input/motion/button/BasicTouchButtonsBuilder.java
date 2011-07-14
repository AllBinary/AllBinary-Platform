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

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.animation.Animation;

public class BasicTouchButtonsBuilder
    extends BaseTouchInputFactory
{
    private static final BasicTouchButtonsBuilder SINGLETON = new BasicTouchButtonsBuilder();

    public static final BaseTouchInputFactory getInstance()
    {
        return SINGLETON;
    }

    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "Constructor"));
            
            final BasicArrayList list = new BasicArrayList();
            
            return list;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "getList", e));
            return BasicArrayListUtil.getImmutableInstance();
        }
    }
}
