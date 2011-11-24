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
package allbinary.math;

import abcs.logic.communication.log.PreLogUtil;

public class FrameUtil
{
    private static final FrameUtil instance = new FrameUtil();

    public static FrameUtil getInstance()
    {
        return instance;
    }

    private FrameUtil()
    {
    }

    // TWB - Note the angle is actually 90 degrees off
    public int getFrameForAngle(short angle, int angleIncrement)
    {
        return this.adjustAngleToFrameAngle(angle) / angleIncrement;
    }

    public int getFrameAngle(int frame, int angleIncrement)
    {
        int frameAngle = angleIncrement * frame - 90;
        return this.adjustAngleToFrameAngle(frameAngle);
    }

    public short adjustAngleToFrameAngle(int currentAngle)
    {
        if (currentAngle > 359)
        {
            currentAngle = (short) (currentAngle - AngleFactory.getInstance().TOTAL_ANGLE);
            
            while(currentAngle > 359)
            {
                PreLogUtil.put(Integer.valueOf(currentAngle).toString(), this, "adjustAngleToFrameAngle >");
                currentAngle = (short) (currentAngle - AngleFactory.getInstance().TOTAL_ANGLE);
            }
            
        }

        if (currentAngle < 0)
        {
            currentAngle = (short) (currentAngle + AngleFactory.getInstance().TOTAL_ANGLE);
            
            while(currentAngle < 0)
            {
                PreLogUtil.put(Integer.valueOf(currentAngle).toString(), this, "adjustAngleToFrameAngle <");
                currentAngle = (short) (currentAngle + AngleFactory.getInstance().TOTAL_ANGLE);
            }
            
        }

        return (short) currentAngle;
    }

}
