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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonPhoneStrings;

public class AngleFactory
{
    private static final AngleFactory instance = new AngleFactory();

    public static AngleFactory getInstance()
    {
        return instance;
    }
    
    public final short TOTAL_ANGLE = 360;

    private final Angle angle[] = new Angle[TOTAL_ANGLE];

    public final Angle DOWN;
    public final Angle UP;
    public final Angle LEFT;
    public final Angle RIGHT;

    public AngleFactory()
    {
        short total = (short) angle.length;
        for (short index = 0; index < total; index++)
        {
            angle[index] = new Angle(index);
        }
        DOWN = this.getInstance(180);
        UP = this.getInstance(0);
        LEFT = this.getInstance(270);
        RIGHT = this.getInstance(90);
    }

    private final FrameUtil frameUtil = FrameUtil.getInstance();
    
    public Angle getInstance(int index)
    {
        return angle[frameUtil.adjustAngleToFrameAngle(index)];
    }

    public Angle getClosestDirection(int angle)
    {
        if((angle >= 315 && angle < 360) || (angle >= 0 && angle < 45)) {
            return this.UP;
        } else if(angle >= 45 && angle < 135) {
            return this.RIGHT;
        } else if(angle >= 135 && angle < 225) {
            return this.DOWN;
        } else if(angle >= 225 && angle < 315) {
            return this.LEFT;
        }
        throw new RuntimeException();
    }

    public Angle getGeneralDirection()
    {
        return null;
    }
    
    public String toString(final int angle) {
        
        final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();

        if(this.UP.getValue() == angle) {
            return commonPhoneStrings.UP;
        } else if(this.DOWN.getValue() == angle) {
            return commonPhoneStrings.DOWN;
        } else if(this.LEFT.getValue() == angle) {
            return commonPhoneStrings.LEFT;
        } else if(this.RIGHT.getValue() == angle) {
            return commonPhoneStrings.RIGHT;
        }
        
        throw new RuntimeException();
    }
    
    public static void main(String[] args) {
     
        final StringMaker stringMaker = new StringMaker();
        final AngleFactory angleFactory = AngleFactory.getInstance();
        for(int index = 0; index < 360; index++) {
            stringMaker.append(index)
                .append(CommonSeps.getInstance().FORWARD_SLASH)
                .append(angleFactory.getClosestDirection(index).getValue())
                .append(CommonSeps.getInstance().NEW_LINE);
        }
        LogUtil.put(LogFactory.getInstance(stringMaker.toString(), "main", "main"));
    }
}
