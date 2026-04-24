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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonSeps;

public class DirectionFactory
{
    private static final DirectionFactory instance = new DirectionFactory();
    
    public static DirectionFactory getInstance()
    {
        return instance;
    }

    public final String NAME = "DIRECTION";
    
    public final Direction LEFT = new Direction(CommonPhoneStrings.getInstance().LEFT, "WEST", 0, 1);
    public final Direction RIGHT = new Direction(CommonPhoneStrings.getInstance().RIGHT, "EAST", 1, 0);
    public final Direction DOWN = new Direction(CommonPhoneStrings.getInstance().DOWN, "SOUTH", 2, 0);
    public final Direction UP = new Direction(CommonPhoneStrings.getInstance().UP, "NORTH", 3, 0);

    public final Direction UP_LEFT;
    public final Direction UP_RIGHT;
    public final Direction DOWN_LEFT;
    public final Direction DOWN_RIGHT;
    
    public final Direction NOT_BORDERED_WITH = new Direction("NOT_BORDERED_WITH", "NOT_BORDERED_WITH", 8, 0);
    public final Direction NO_DIRECTION = new Direction("NO_DIRECTION", "NO_DIRECTION", 9, 0);
    
    public final Direction TOP = new Direction("TOP", "TOP", 10, 0);
    public final Direction BOTTOM = new Direction("BOTTOM", "BOTTOM", 11, 0);
    public final Direction ALL_DIRECTION = new Direction("ALL_DIRECTION", "ALL_DIRECTION", 12, 0);
    
    private DirectionFactory()
    {
        StringMaker stringBuffer = new StringMaker();
        
        CommonSeps commonSeps = CommonSeps.getInstance();
        
        this.UP_LEFT = new Direction("UP LEFT", 
                stringBuffer.append(UP.getOtherName()).
                append(commonSeps.SPACE).
                append(LEFT.getOtherName()).toString(), 4, 0);

        stringBuffer.delete(0, stringBuffer.length());
        
        this.UP_RIGHT = new Direction("UP RIGHT", 
                stringBuffer.append(UP.getOtherName()).
                        append(commonSeps.SPACE).
                        append(RIGHT.getOtherName()).toString(), 5, 0);
        
        stringBuffer.delete(0, stringBuffer.length());
        
        this.DOWN_LEFT = new Direction("DOWN LEFT", 
                stringBuffer.append(DOWN.getOtherName()).
                append(commonSeps.SPACE).
                append(LEFT.getOtherName()).toString(), 6, 0);
        
        stringBuffer.delete(0, stringBuffer.length());
        
        this.DOWN_RIGHT = new Direction("DOWN RIGHT", 
                stringBuffer.append(DOWN.getOtherName()).
                append(commonSeps.SPACE).
                append(RIGHT.getOtherName()).toString(), 7, 0);
    }
}
