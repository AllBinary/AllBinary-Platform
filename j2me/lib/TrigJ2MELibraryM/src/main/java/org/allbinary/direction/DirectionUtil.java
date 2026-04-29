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
package org.allbinary.direction;

import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;

public class DirectionUtil
{
    private static final DirectionUtil instance = new DirectionUtil();

    public static DirectionUtil getInstance()
    {
        return DirectionUtil.instance;
    }
    
    private Angle[] directionArray = new Angle[10];
    private Angle[] directionArray2 = new Angle[10];

    private DirectionUtil()
    {
        AngleFactory angleFactory = AngleFactory.getInstance();

        DirectionFactory directionFactory = DirectionFactory.getInstance();
        
        this.directionArray[directionFactory.LEFT.getValue()] = angleFactory.LEFT;
        this.directionArray[directionFactory.RIGHT.getValue()] = angleFactory.RIGHT;
        this.directionArray[directionFactory.UP.getValue()] = angleFactory.UP;
        this.directionArray[directionFactory.DOWN.getValue()] = angleFactory.DOWN;

        this.directionArray2[directionFactory.LEFT.getValue()] = angleFactory.LEFT;
        this.directionArray2[directionFactory.RIGHT.getValue()] = angleFactory.RIGHT;
        this.directionArray2[directionFactory.UP.getValue()] = angleFactory.UP;
        this.directionArray2[directionFactory.DOWN.getValue()] = angleFactory.DOWN;

        // directionArray2Direction.DOWN_LEFT, angleFactory.RIGHT);
        // directionArray2Direction.DOWN_RIGHT, angleFactory.RIGHT);
        // directionArray2Direction.UP_LEFT, angleFactory.RIGHT);
        // directionArray2Direction.UP_RIGHT, angleFactory.RIGHT);
    }

    public Angle getAngle(Direction direction)
    {
        return this.directionArray2[direction.getValue()];
    }

    public Angle getFrameAngle(Direction direction)
    {
        return this.directionArray[direction.getValue()];
    }

}
