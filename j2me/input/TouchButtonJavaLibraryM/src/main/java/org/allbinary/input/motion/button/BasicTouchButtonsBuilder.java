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

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class BasicTouchButtonsBuilder
    extends BaseTouchInput
{
    
    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));
            
            final BasicArrayList list = new BasicArrayList();
            
            final CommonButtons commonButtons = CommonButtons.getInstance();
            
            final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

            final BasicTouchButtonCellPositionFactory basicTouchButtonCellPositionFactory = 
                new BasicTouchButtonCellPositionFactory();
            
            /*
TouchButtonBlankResource.RESOURCE).getInstance();
TouchButtonGenericActionResource.RESOURCE).getInstance();
TouchButtonStrafeLeftResource.RESOURCE).getInstance();
TouchButtonStrafeRightResource.RESOURCE).getInstance();
            */
            
            TouchButton UP = new TouchButton(BasicTouchInputFactory.getInstance().UP,
                    TouchButtonUpResource.getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton DOWN = new TouchButton(BasicTouchInputFactory.getInstance().DOWN,
                    TouchButtonDownResource.getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );
            
            //SPECIAL_BUTTON_FOUR
            TouchButton LEFT = new TouchButton(BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_FOUR,
                    TouchButtonTurnLeftResource.getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.BOTTOM_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton RIGHT = new TouchButton(BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_THREE,
                    TouchButtonTurnRightResource.getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.BOTTOM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            list.add(UP);
            list.add(LEFT);
            list.add(RIGHT);
            list.add(DOWN);
            
            return list;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.GET_LIST, e));
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
