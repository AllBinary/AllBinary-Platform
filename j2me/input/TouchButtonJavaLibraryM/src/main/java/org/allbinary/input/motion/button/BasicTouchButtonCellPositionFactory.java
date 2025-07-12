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

import org.allbinary.graphics.CellPosition;
import org.allbinary.graphics.CellPositionFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class BasicTouchButtonCellPositionFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final CellPosition TOP_RIGHT;
    public final CellPosition TOP_LEFT;

    public final CellPosition SECOND_FROM_TOP_LEFT;
    public final CellPosition SECOND_FROM_TOP_RIGHT;

    public final CellPosition TOP_SECOND_FROM_LEFT;
    public final CellPosition TOP_SECOND_FROM_RIGHT;
    
    //public CellPosition SECOND_FROM_BOTTOM_RIGHT;
    public final CellPosition BOTTOM_RIGHT;
    public final CellPosition BOTTOM_SECOND_FROM_RIGHT;
    public final CellPosition SECOND_FROM_BOTTOM_RIGHT;
    public final CellPosition THIRD_FROM_BOTTOM_RIGHT;
    public final CellPosition FOURTH_FROM_BOTTOM_RIGHT;

    public final CellPosition SECOND_FROM_BOTTOM_SECOND_FROM_RIGHT;
    
    public final CellPosition SECOND_FROM_BOTTOM_THIRD_FROM_RIGHT;
    public final CellPosition SECOND_FROM_BOTTOM_FOURTH_FROM_RIGHT;
    public final CellPosition BOTTOM_THIRD_FROM_RIGHT;
    public final CellPosition BOTTOM_FOURTH_FROM_RIGHT;
    
    public final CellPosition BOTTOM_SECOND_FROM_LEFT;
    public final CellPosition SECOND_FROM_BOTTOM_LEFT;
    public final CellPosition THIRD_FROM_BOTTOM_LEFT;
    public final CellPosition BOTTOM_LEFT;
    
    public final CellPosition SECOND_FROM_BOTTOM_SECOND_FROM_LEFT;

    public BasicTouchButtonCellPositionFactory()
    {
        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        
        final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

        final CellPositionFactory cellPositionFactory = CellPositionFactory.getInstance();
        
        cellPositionFactory.init(
                touchButtonLocationHelper.getTotalColumns(), 
                touchButtonLocationHelper.getTotalRows());

        TOP_LEFT = cellPositionFactory.getInstance(0, 0);
        TOP_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1, 0);
        
        BOTTOM_LEFT = cellPositionFactory.getInstance(
                0, touchButtonLocationHelper.getTotalRows() - 1);
        BOTTOM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1,
                touchButtonLocationHelper.getTotalRows() - 1);
        
        BOTTOM_SECOND_FROM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 2,
                touchButtonLocationHelper.getTotalRows() - 1);
        SECOND_FROM_BOTTOM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1,
                touchButtonLocationHelper.getTotalRows() - 2);

        SECOND_FROM_BOTTOM_SECOND_FROM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 2,
                touchButtonLocationHelper.getTotalRows() - 2);
        
        BOTTOM_SECOND_FROM_LEFT = cellPositionFactory.getInstance(
                1, touchButtonLocationHelper.getTotalRows() - 1);
        SECOND_FROM_BOTTOM_LEFT = cellPositionFactory.getInstance(
                0, touchButtonLocationHelper.getTotalRows() - 2);
        
        SECOND_FROM_BOTTOM_SECOND_FROM_LEFT = cellPositionFactory.getInstance(
                1,
                touchButtonLocationHelper.getTotalRows() - 2);
        
        if(touchButtonLocationHelper.getTotalRows() > 2)
        {
            SECOND_FROM_TOP_LEFT = cellPositionFactory.getInstance(0, 1);
            SECOND_FROM_TOP_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1, 1);
        }
        else
        {
            SECOND_FROM_TOP_LEFT = cellPositionFactory.NONE;
            SECOND_FROM_TOP_RIGHT = cellPositionFactory.NONE;
        }

        if(touchButtonLocationHelper.getTotalRows() >= 4)
        {
            THIRD_FROM_BOTTOM_RIGHT = cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 1,
                    touchButtonLocationHelper.getTotalRows() - 3);
            FOURTH_FROM_BOTTOM_RIGHT = cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 1,
                    touchButtonLocationHelper.getTotalRows() - 4);

            THIRD_FROM_BOTTOM_LEFT = cellPositionFactory.getInstance(
                    0, touchButtonLocationHelper.getTotalRows() - 3);
        }
        else
            {
                THIRD_FROM_BOTTOM_RIGHT = cellPositionFactory.NONE;
                FOURTH_FROM_BOTTOM_RIGHT = cellPositionFactory.NONE;
                
                THIRD_FROM_BOTTOM_LEFT = cellPositionFactory.NONE;
            }

        if(touchButtonLocationHelper.getTotalColumns() >= 4)
        {
            SECOND_FROM_BOTTOM_THIRD_FROM_RIGHT =
                cellPositionFactory.getInstance(
                        touchButtonLocationHelper.getTotalColumns() - 3,
                        touchButtonLocationHelper.getTotalRows() - 2);

            SECOND_FROM_BOTTOM_FOURTH_FROM_RIGHT = 
                cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 4,
                    touchButtonLocationHelper.getTotalRows() - 2);
            
            BOTTOM_THIRD_FROM_RIGHT = 
                cellPositionFactory.getInstance(
                        touchButtonLocationHelper.getTotalColumns() - 3,
                        touchButtonLocationHelper.getTotalRows() - 1);
            BOTTOM_FOURTH_FROM_RIGHT = 
                cellPositionFactory.getInstance(
                        touchButtonLocationHelper.getTotalColumns() - 4,
                        touchButtonLocationHelper.getTotalRows() - 1);

            TOP_SECOND_FROM_LEFT = cellPositionFactory.getInstance(1, 0);
            TOP_SECOND_FROM_RIGHT = cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 2, 0);
        }
        else
            {
            SECOND_FROM_BOTTOM_THIRD_FROM_RIGHT = cellPositionFactory.NONE;
            SECOND_FROM_BOTTOM_FOURTH_FROM_RIGHT = cellPositionFactory.NONE;
            BOTTOM_THIRD_FROM_RIGHT = cellPositionFactory.NONE;
            BOTTOM_FOURTH_FROM_RIGHT = cellPositionFactory.NONE;
            
            TOP_SECOND_FROM_LEFT = cellPositionFactory.NONE;
            TOP_SECOND_FROM_RIGHT = cellPositionFactory.NONE;
            
            }
    }
}
