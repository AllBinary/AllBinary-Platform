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
import allbinary.animation.Animation;
import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.animation.NullAnimationFactory;
import allbinary.graphics.CellPositionFactory;

public class TestGameDemoWithSensorTouchButtonsBuilder 
extends BaseTouchInput
{
    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));
            
            final BasicArrayList list = new BasicArrayList();
            
            TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

            BasicTouchButtonCellPositionFactory basicTouchButtonCellPositionFactory =
                new BasicTouchButtonCellPositionFactory();

            CommonButtons commonButtons = CommonButtons.getInstance();
            
            Animation animationInterface = 
                NullAnimationFactory.getFactoryInstance().getInstance();
            Animation hintAnimationInterface = animationInterface;

            BasicTouchInputFactory basicTouchInputFactory = BasicTouchInputFactory.getInstance();
            
            TouchButton LEFT = new FullTouchButton(basicTouchInputFactory.LEFT,
                    TouchButtonTurnLeftResource.getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(),
                    touchButtonLocationHelper.getRowsRemainderHalf());                    

            TouchButton LEFT2 = new FullTouchButton(basicTouchInputFactory.LEFT,
                    animationInterface,
                    hintAnimationInterface,
                    //(Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
                    //.getInstance().get(TouchButtonTurnLeftResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.BOTTOM_SECOND_FROM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf());                    

            TouchButton LEFT3 = new FullTouchButton(basicTouchInputFactory.LEFT,
                    animationInterface,
                    hintAnimationInterface,
                    //(Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
                    //.getInstance().get(TouchButtonTurnLeftResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf());                    

            TouchButton LEFT4 = new FullTouchButton(basicTouchInputFactory.LEFT,
                    animationInterface,
                    hintAnimationInterface,
                    //(Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
                    //.getInstance().get(TouchButtonTurnLeftResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_SECOND_FROM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf());

            
            TouchButton RIGHT = new FullTouchButton(basicTouchInputFactory.RIGHT,
                    TouchButtonTurnRightResource.getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.BOTTOM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf());                    

            TouchButton RIGHT2 = new FullTouchButton(basicTouchInputFactory.RIGHT,
                    animationInterface,
                    hintAnimationInterface,
                    //(Animation) FeaturedAnimationInterfaceFactoryInterfaceFactory
                    //.getInstance().get(TouchButtonTurnRightResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.BOTTOM_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf());                    
            
            TouchButton RIGHT3 = new FullTouchButton(basicTouchInputFactory.RIGHT,
                    animationInterface,
                    hintAnimationInterface,
                    //featuredAnimationInterfaceFactoryInterfaceFactory.get(TouchButtonTurnRightResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf());                    
            
            TouchButton RIGHT4 = new FullTouchButton(basicTouchInputFactory.RIGHT,
                    animationInterface,
                    hintAnimationInterface,
                    //featuredAnimationInterfaceFactoryInterfaceFactory.get(TouchButtonTurnRightResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf());

            list.add(LEFT);
            list.add(LEFT2);
            list.add(LEFT3);
            list.add(LEFT4);

            list.add(RIGHT);
            list.add(RIGHT2);
            list.add(RIGHT3);
            list.add(RIGHT4);

            if(basicTouchButtonCellPositionFactory.THIRD_FROM_BOTTOM_RIGHT != 
                CellPositionFactory.getInstance().NONE)
            {
                TouchButton WEAPON = new TouchButton(basicTouchInputFactory.SPECIAL_BUTTON_FIVE,
                        TouchButtonGenericActionResource.getInstance(),
                        commonButtons.NORMAL_BUTTON,
                        basicTouchButtonCellPositionFactory.THIRD_FROM_BOTTOM_RIGHT,
                        touchButtonLocationHelper.getColumnsRemainderHalf(), 
                        touchButtonLocationHelper.getRowsRemainderHalf()
                        );

                list.add(WEAPON);
            }
            
            TouchButton SPECIAL3 = new TouchButton(
                    basicTouchInputFactory.SPECIAL_BUTTON_SIX,
                    TouchButtonGenericActionResource.getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.TOP_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton SPECIAL4 = new TouchButton(
                    basicTouchInputFactory.SPECIAL_BUTTON_SEVEN_TESTING_ONLY,
                    TouchButtonGenericActionResource.getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.TOP_SECOND_FROM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );
            
            /*
            TouchButton ROTATE_Z = new TouchButton(basicTouchInputFactory.SPECIAL_BUTTON_EIGHT_TESTING_ONLY,
                    animationInterface,
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_TOP_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );
            */
            
            list.add(SPECIAL3);
            list.add(SPECIAL4);
            //list.add(ROTATE_Z);

            return list;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_LIST, e));
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
