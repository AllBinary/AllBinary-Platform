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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;

public class BasicTouchButtonsBuilder
    extends BaseTouchInput
{
    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));
            
            final BasicArrayList list = new BasicArrayList();
            
            final CommonButtons commonButtons = CommonButtons.getInstance();
            
            final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

            final BasicTouchButtonCellPositionFactory basicTouchButtonCellPositionFactory = 
                new BasicTouchButtonCellPositionFactory();
            
            /*
            (Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
            .getInstance().get(TouchButtonBlankResource.RESOURCE).getInstance();
            (Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
        .getInstance().get(TouchButtonGenericActionResource.RESOURCE).getInstance();
            (Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
        .getInstance().get(TouchButtonStrafeLeftResource.RESOURCE).getInstance();
            (Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
        .getInstance().get(TouchButtonStrafeRightResource.RESOURCE).getInstance();
            */

            final FeaturedAnimationInterfaceFactoryInterfaceFactory 
            featuredAnimationInterfaceFactoryInterfaceFactory = 
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();
            
            TouchButton UP = new TouchButton(BasicTouchInputFactory.getInstance().UP,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                             TouchButtonUpResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton DOWN = new TouchButton(BasicTouchInputFactory.getInstance().DOWN,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonDownResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );
            
            //SPECIAL_BUTTON_FOUR
            TouchButton LEFT = new TouchButton(BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_FOUR,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(TouchButtonTurnLeftResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.BOTTOM_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton RIGHT = new TouchButton(BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_THREE,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonTurnRightResource.RESOURCE).getInstance(),
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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_LIST, e));
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
