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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;

public class TouchScreenFactory
{
    private static final TouchScreenFactory SINGLETON = new TouchScreenFactory();
    
    private boolean touch;

    private boolean multiTouch;
    private boolean multiTouchDistinct;
    
    private TouchScreenType touchScreenType;

    private TouchScreenFactory()
    {
        try
        {
            //HardwareInterface hardwareInterface = 
              // SystemHardwareFactory.getInstance(
                //    OperatingSystemFactory.getInstance().getOperatingSystemInstance());

            //String hardwareString = StringUtil.getInstance(hardwareInterface
              //      .toString());
            
            //System.out.println("Hardware: " + hardwareString);
            
            this.setTouch(true);
            /*
            if(hardwareString.indexOf(TOUCHSCREEN) >= 0)
            {
                //System.out.println("Touch");
                this.setTouch(true);
            }
            else
                if(hardwareString.length() < 400)
            {
                    //System.out.println("Touch for Emulator");
                    this.setTouch(true);
            }
                else
                {
                    //System.out.println("No Touch");
                    this.setTouch(false);
                }
                */
            this.setMultiTouch(false);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, SINGLETON, CommonStrings.getInstance().GET_INSTANCE, e));
        }
    }

    public static final TouchScreenFactory getInstance()
    {
        return SINGLETON;
    }

    public void setMultiTouch(boolean multiTouch)
    {
        this.multiTouch = multiTouch;
    }

    public boolean isMultiTouch()
    {
        return multiTouch;
    }

    public void setTouch(boolean touch)
    {
        this.touch = touch;
    }

    public boolean isTouch()
    {
        return touch;
    }

    public void setMultiTouchDistinct(boolean multiTouchDistinct)
    {
        this.multiTouchDistinct = multiTouchDistinct;
    }

    public boolean isMultiTouchDistinct()
    {
        return multiTouchDistinct;
    }

    public void setTouchScreenType(TouchScreenType touchScreenType)
    {
        this.touchScreenType = touchScreenType;
    }

    public TouchScreenType getTouchScreenType()
    {
        return touchScreenType;
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        /*
        private boolean touch;

        private boolean multiTouch;
        private boolean multiTouchDistinct;
        
        private TouchScreenType touchScreenType;
        */
        
        stringBuffer.append("TouchScreen: ");
        stringBuffer.append(this.isTouch());
        stringBuffer.append(" MultiTouch: ");
        stringBuffer.append(this.isMultiTouch());
        stringBuffer.append(" Distinct MultiTouch: ");
        stringBuffer.append(this.isMultiTouchDistinct());
        stringBuffer.append(" TouchScreenType: ");
        stringBuffer.append(StringUtil.getInstance().toString(this.getTouchScreenType()));

        return stringBuffer.toString();
    }
}
