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

public class TestGameDemoTouchButtonsBuilder
{
    private static final TestGameDemoTouchButtonsBuilder SINGLETON = new TestGameDemoTouchButtonsBuilder();
    
    public static final TestGameDemoTouchButtonsBuilder getInstance()
    {
        return SINGLETON;
    }
    
    //private TouchButton UP;
    //private TouchButton LEFT;
    //private TouchButton RIGHT;
    //private TouchButton DOWN;
    
    public void build()
    {
        /*
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "Constructor"));
            
            TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

            Rectangle NORMAL_BUTTON = new Rectangle(PointFactory.ZERO_ZERO,
                    TouchButtonInput.STANDARD_BUTTON_SIZE, TouchButtonInput.STANDARD_BUTTON_SIZE);

            TouchButton UP = new TouchButton(BasicTouchInputFactory.getInstance().UP,
                    TouchButtonAnimationInterfaceFactory.getInstance().upTouchButtonAnimationInterface,
                    NORMAL_BUTTON, 
                    BasicTouchButtonCellPositionFactory.getInstance().BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );
            
            TouchButton LEFT = new TouchButton(BasicTouchInputFactory.getInstance().LEFT,
                    TouchButtonAnimationInterfaceFactory.getInstance().turnLeftTouchButtonAnimationInterface,
                    NORMAL_BUTTON, 
                    BasicTouchButtonCellPositionFactory.getInstance().BOTTOM_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton RIGHT = new TouchButton(BasicTouchInputFactory.getInstance().RIGHT,
                    TouchButtonAnimationInterfaceFactory.getInstance().turnRightTouchButtonAnimationInterface,
                    NORMAL_BUTTON, 
                    BasicTouchButtonCellPositionFactory.getInstance().BOTTOM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButtonFactory.getInstance().getList().add(UP);
            TouchButtonFactory.getInstance().getList().add(LEFT);
            TouchButtonFactory.getInstance().getList().add(RIGHT);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this,
                    "Constructor", e));
        }
        */
    }

}
