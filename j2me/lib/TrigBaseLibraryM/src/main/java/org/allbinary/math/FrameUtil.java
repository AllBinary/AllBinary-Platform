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
package org.allbinary.math;

public class FrameUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final FrameUtil instance = new FrameUtil();

    public static FrameUtil getInstance()
    {
        return instance;
    }

    //private final String AJUST_ANGLE_TO_FRAME_ANGLE_MORE = "adjustAngleToFrameAngle >";
    //private final String AJUST_ANGLE_TO_FRAME_ANGLE_LESS = "adjustAngleToFrameAngle <";
    
    private FrameUtil()
    {
    }

    // TWB - Note the angle is actually 90 degrees off
    public int getFrameForAngle(final short angle, final int angleIncrement)
    {
        return this.adjustAngleToFrameAngle((int) angle) / angleIncrement;
    }

    public int getFrameAngle(final int frame, final int angleIncrement)
    {
        final int frameAngle = angleIncrement * frame - 90;
        return this.adjustAngleToFrameAngle(frameAngle);
    }

    public int adjustAngleToFrameAngle(int currentAngle)
    {
        final AngleFactory angleFactory = AngleFactory.getInstance();

        if (currentAngle > 359)
        {
            currentAngle = (currentAngle - angleFactory.TOTAL_ANGLE);
            
            while(currentAngle > 359)
            {
                //PreLogUtil.put(Integer.toString(currentAngle).toString(), this, AJUST_ANGLE_TO_FRAME_ANGLE_MORE);
                currentAngle = (currentAngle - angleFactory.TOTAL_ANGLE);
            }
            
        }

        if (currentAngle < 0)
        {
            currentAngle = (currentAngle + angleFactory.TOTAL_ANGLE);
            
            while(currentAngle < 0)
            {
                //PreLogUtil.put(Integer.toString(currentAngle).toString(), this, AJUST_ANGLE_TO_FRAME_ANGLE_LESS);
                currentAngle = (currentAngle + angleFactory.TOTAL_ANGLE);
            }
            
        }

        return currentAngle;
    }

}
