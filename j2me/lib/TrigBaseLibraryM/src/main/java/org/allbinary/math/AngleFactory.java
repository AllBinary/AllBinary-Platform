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
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonPhoneStrings;

public class AngleFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final AngleFactory instance = new AngleFactory();

    public static AngleFactory getInstance()
    {
        return instance;
    }
    
    public final short TOTAL_ANGLE = 360;

    private final Angle[] angleArray = new Angle[TOTAL_ANGLE];

    public final NamedAngle NOT_ANGLE = new NamedAngle((short) -1, CommonStrings.getInstance().EMPTY);
    public final NamedAngle DOWN;
    public final NamedAngle UP;
    public final NamedAngle LEFT;
    public final NamedAngle RIGHT;

    public AngleFactory()
    {
        final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();
        
        final short total = (short) angleArray.length;
        
        angleArray[0] = UP = new NamedAngle((short) 0, commonPhoneStrings.UP);
        
        for (short index = 1; index < 90; index++)
        {
            angleArray[index] = new Angle(index);
        }

        angleArray[90] = RIGHT = new NamedAngle((short) 90, commonPhoneStrings.RIGHT);
        
        for (short index = 91; index < 180; index++)
        {
            angleArray[index] = new Angle(index);
        }

        angleArray[180] = DOWN = new NamedAngle((short) 180, commonPhoneStrings.DOWN);
        
        for (short index = 181; index < 270; index++)
        {
            angleArray[index] = new Angle(index);
        }

        angleArray[270] = LEFT = new NamedAngle((short) 270, commonPhoneStrings.LEFT);
        
        for (short index = 271; index < total; index++)
        {
            angleArray[index] = new Angle(index);
        }
        
    }

    private final FrameUtil frameUtil = FrameUtil.getInstance();
    
    public Angle getInstance(int index)
    {
        return angleArray[frameUtil.adjustAngleToFrameAngle(index)];
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
    
//    public String toString(final int angle) {
//        
//        final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();
//
//        if(this.UP.getValue() == angle) {
//            return commonPhoneStrings.UP;
//        } else if(this.DOWN.getValue() == angle) {
//            return commonPhoneStrings.DOWN;
//        } else if(this.LEFT.getValue() == angle) {
//            return commonPhoneStrings.LEFT;
//        } else if(this.RIGHT.getValue() == angle) {
//            return commonPhoneStrings.RIGHT;
//        }
//        
//        throw new RuntimeException();
//    }
    
    public static void main(String[] args) {
     
        final StringMaker stringMaker = new StringMaker();
        final AngleFactory angleFactory = AngleFactory.getInstance();
        for(int index = 0; index < 360; index++) {
            stringMaker.append(index)
                .append(CommonSeps.getInstance().FORWARD_SLASH)
                .append(angleFactory.getClosestDirection(index).getValue())
                .append(CommonSeps.getInstance().NEW_LINE);
        }
        LogUtil.getInstance().put(stringMaker.toString(), "main", "main");
    }
}
