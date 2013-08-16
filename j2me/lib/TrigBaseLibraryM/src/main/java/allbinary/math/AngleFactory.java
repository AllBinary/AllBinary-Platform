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

    public Angle getGeneralDirection()
    {
        return null;
    }    
}
