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
        this.logUtil.putF(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        
        final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

        final CellPositionFactory cellPositionFactory = CellPositionFactory.getInstance();
        
        cellPositionFactory.init(
                touchButtonLocationHelper.getTotalColumns(), 
                touchButtonLocationHelper.getTotalRows());

        this.TOP_LEFT = cellPositionFactory.getInstance(0, 0);
        this.TOP_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1, 0);
        
        this.BOTTOM_LEFT = cellPositionFactory.getInstance(
                0, touchButtonLocationHelper.getTotalRows() - 1);
        this.BOTTOM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1,
                touchButtonLocationHelper.getTotalRows() - 1);
        
        this.BOTTOM_SECOND_FROM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 2,
                touchButtonLocationHelper.getTotalRows() - 1);
        this.SECOND_FROM_BOTTOM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1,
                touchButtonLocationHelper.getTotalRows() - 2);

        this.SECOND_FROM_BOTTOM_SECOND_FROM_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 2,
                touchButtonLocationHelper.getTotalRows() - 2);
        
        this.BOTTOM_SECOND_FROM_LEFT = cellPositionFactory.getInstance(
                1, touchButtonLocationHelper.getTotalRows() - 1);
        this.SECOND_FROM_BOTTOM_LEFT = cellPositionFactory.getInstance(
                0, touchButtonLocationHelper.getTotalRows() - 2);
        
        this.SECOND_FROM_BOTTOM_SECOND_FROM_LEFT = cellPositionFactory.getInstance(
                1,
                touchButtonLocationHelper.getTotalRows() - 2);
        
        if(touchButtonLocationHelper.getTotalRows() > 2)
        {
            this.SECOND_FROM_TOP_LEFT = cellPositionFactory.getInstance(0, 1);
            this.SECOND_FROM_TOP_RIGHT = cellPositionFactory.getInstance(
                touchButtonLocationHelper.getTotalColumns() - 1, 1);
        }
        else
        {
            this.SECOND_FROM_TOP_LEFT = cellPositionFactory.NONE;
            this.SECOND_FROM_TOP_RIGHT = cellPositionFactory.NONE;
        }

        if(touchButtonLocationHelper.getTotalRows() >= 4)
        {
            this.THIRD_FROM_BOTTOM_RIGHT = cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 1,
                    touchButtonLocationHelper.getTotalRows() - 3);
            this.FOURTH_FROM_BOTTOM_RIGHT = cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 1,
                    touchButtonLocationHelper.getTotalRows() - 4);

            this.THIRD_FROM_BOTTOM_LEFT = cellPositionFactory.getInstance(
                    0, touchButtonLocationHelper.getTotalRows() - 3);
        }
        else
            {
                this.THIRD_FROM_BOTTOM_RIGHT = cellPositionFactory.NONE;
                this.FOURTH_FROM_BOTTOM_RIGHT = cellPositionFactory.NONE;
                
                this.THIRD_FROM_BOTTOM_LEFT = cellPositionFactory.NONE;
            }

        if(touchButtonLocationHelper.getTotalColumns() >= 4)
        {
            this.SECOND_FROM_BOTTOM_THIRD_FROM_RIGHT =
                cellPositionFactory.getInstance(
                        touchButtonLocationHelper.getTotalColumns() - 3,
                        touchButtonLocationHelper.getTotalRows() - 2);

            this.SECOND_FROM_BOTTOM_FOURTH_FROM_RIGHT = 
                cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 4,
                    touchButtonLocationHelper.getTotalRows() - 2);
            
            this.BOTTOM_THIRD_FROM_RIGHT = 
                cellPositionFactory.getInstance(
                        touchButtonLocationHelper.getTotalColumns() - 3,
                        touchButtonLocationHelper.getTotalRows() - 1);
            this.BOTTOM_FOURTH_FROM_RIGHT = 
                cellPositionFactory.getInstance(
                        touchButtonLocationHelper.getTotalColumns() - 4,
                        touchButtonLocationHelper.getTotalRows() - 1);

            this.TOP_SECOND_FROM_LEFT = cellPositionFactory.getInstance(1, 0);
            this.TOP_SECOND_FROM_RIGHT = cellPositionFactory.getInstance(
                    touchButtonLocationHelper.getTotalColumns() - 2, 0);
        }
        else
            {
            this.SECOND_FROM_BOTTOM_THIRD_FROM_RIGHT = cellPositionFactory.NONE;
            this.SECOND_FROM_BOTTOM_FOURTH_FROM_RIGHT = cellPositionFactory.NONE;
            this.BOTTOM_THIRD_FROM_RIGHT = cellPositionFactory.NONE;
            this.BOTTOM_FOURTH_FROM_RIGHT = cellPositionFactory.NONE;
            
            this.TOP_SECOND_FROM_LEFT = cellPositionFactory.NONE;
            this.TOP_SECOND_FROM_RIGHT = cellPositionFactory.NONE;
            
            }
    }
}
