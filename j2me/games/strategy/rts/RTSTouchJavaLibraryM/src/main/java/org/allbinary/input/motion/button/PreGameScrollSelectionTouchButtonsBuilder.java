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
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class PreGameScrollSelectionTouchButtonsBuilder
extends BaseTouchInput
{
    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.GET_LIST));

            final BasicArrayList list = new BasicArrayList();

            final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

            //PreLogUtil.put(touchButtonLocationHelper.toString(), this, commonStrings.GET_LIST);
            
            final CommonButtons commonButtons = CommonButtons.getInstance();
            
            final BasicTouchButtonCellPositionFactory basicTouchButtonCellPositionFactory = 
                new BasicTouchButtonCellPositionFactory();
            
            //Scroll Left Button
            TouchButton LEFT = new TouchButton(BasicTouchInputFactory.getInstance().LEFT,
                    TouchButtonStrafeLeftResource.getInstance(),
                    commonButtons.NORMAL_BUTTON,
               basicTouchButtonCellPositionFactory.TOP_LEFT,
               touchButtonLocationHelper.getColumnsRemainderHalf(),
               touchButtonLocationHelper.getRowsRemainderHalf());

            //Scroll Right Button
            TouchButton RIGHT = new TouchButton(BasicTouchInputFactory.getInstance().RIGHT,
                    TouchButtonStrafeRightResource.getInstance(),
                    commonButtons.NORMAL_BUTTON,
               basicTouchButtonCellPositionFactory.TOP_RIGHT,
               touchButtonLocationHelper.getColumnsRemainderHalf(),
               touchButtonLocationHelper.getRowsRemainderHalf());

            TouchButton START =
               new TouchButton(BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_FIVE,
                   TouchButtonStartResource.getInstance(),
                   commonButtons.NORMAL_BUTTON,
               basicTouchButtonCellPositionFactory.BOTTOM_RIGHT,
               touchButtonLocationHelper.getColumnsRemainderHalf(),
               touchButtonLocationHelper.getRowsRemainderHalf());

            list.add(LEFT);
            list.add(RIGHT);
            list.add(START);

            return list;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.GET_LIST, e));
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
