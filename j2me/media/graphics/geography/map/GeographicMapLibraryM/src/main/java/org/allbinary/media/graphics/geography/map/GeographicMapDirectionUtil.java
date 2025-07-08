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
package org.allbinary.media.graphics.geography.map;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.graphics.CellPosition;

/**
 *
 * @author user
 */
public class GeographicMapDirectionUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final GeographicMapDirectionUtil instance = new GeographicMapDirectionUtil();
    
    public static GeographicMapDirectionUtil getInstance()
    {
        return instance;
    }
    
    private GeographicMapDirectionUtil()
    {
        
    }
    
    private final String MESSAGE = "Cell Position should have been cached!!!";
    
    public Direction getDirectionFromCellPositionToAdjacentCellPosition(
       GeographicMapCellPosition fromGeographicMapCellPosition,
       GeographicMapCellPosition toGeographicMapCellPosition)
       throws Exception
    {
        int fromColumn = fromGeographicMapCellPosition.getColumn();
        int fromRow = fromGeographicMapCellPosition.getRow();

        int goColumn = toGeographicMapCellPosition.getColumn();
        int goRow = toGeographicMapCellPosition.getRow();

        DirectionFactory directionFactory = 
            DirectionFactory.getInstance();
        
        if (fromColumn - 1 == goColumn)
        {
            return directionFactory.LEFT;
        }
        else if (fromColumn + 1 == goColumn)
        {
            return directionFactory.RIGHT;
        }
        else if (fromRow - 1 == goRow)
        {
            return directionFactory.UP;
        }
        else if (fromRow + 1 == goRow)
        {
            return directionFactory.DOWN;
        }
        else if (fromGeographicMapCellPosition == toGeographicMapCellPosition)
        {
            return directionFactory.NO_DIRECTION;
        }
        else if (fromRow == goRow && fromColumn == goColumn)
        {
            //PreLogUtil.put(MESSAGE, this, "getDirectionFromCellPositionToAdjacentCellPosition");
            //return directionFactory.NO_DIRECTION;
            throw new Exception(MESSAGE);
        }

        return directionFactory.NOT_BORDERED_WITH;
    }

    public Direction getEightDirectionFromCellPositionToAdjacentCellPosition(
       GeographicMapCellPosition fromGeographicMapCellPosition,
       GeographicMapCellPosition toGeographicMapCellPosition)
       throws Exception
    {
        int fromColumn = fromGeographicMapCellPosition.getColumn();
        int fromRow = fromGeographicMapCellPosition.getRow();

        int goColumn = toGeographicMapCellPosition.getColumn();
        int goRow = toGeographicMapCellPosition.getRow();

        DirectionFactory directionFactory = 
            DirectionFactory.getInstance();
        
        if (fromRow == goRow)
        {
            if (fromColumn - 1 == goColumn)
            {
                return directionFactory.LEFT;
            }
            else if (fromColumn + 1 == goColumn)
            {
                return directionFactory.RIGHT;
            }
        }
        else if (fromRow - 1 == goRow)
        {
            if (fromColumn == goColumn)
            {
                return directionFactory.UP;
            }
            if (fromColumn - 1 == goColumn)
            {
                return directionFactory.UP_LEFT;
            }
            else if (fromColumn + 1 == goColumn)
            {
                return directionFactory.UP_RIGHT;
            }
        }
        else if (fromRow + 1 == goRow)
        {
            if (fromColumn == goColumn)
            {
                return directionFactory.DOWN;
            }
            if (fromColumn - 1 == goColumn)
            {
                return directionFactory.DOWN_LEFT;
            }
            else if (fromColumn + 1 == goColumn)
            {
                return directionFactory.DOWN_RIGHT;
            }
        }
        else if (fromGeographicMapCellPosition == toGeographicMapCellPosition)
        {
            return directionFactory.NO_DIRECTION;
        }
        else if (fromRow == goRow && fromColumn == goColumn)
        {
            //PreLogUtil.put(MESSAGE, this, "getEightDirectionFromCellPositionToAdjacentCellPosition");
            //return directionFactory.NO_DRECTION;
            throw new Exception(MESSAGE);
        }

        return directionFactory.NOT_BORDERED_WITH;
    }

    // This should have combo directions or angle returned.
    public Direction getDirectionFromCellPositionToCellPosition(
            GeographicMapCellPosition fromGeographicMapCellPosition,
            GeographicMapCellPosition toGeographicMapCellPosition)
            throws Exception
    {
        int fromColumn = fromGeographicMapCellPosition.getColumn();
        int fromRow = fromGeographicMapCellPosition.getRow();

        int goColumn = toGeographicMapCellPosition.getColumn();
        int goRow = toGeographicMapCellPosition.getRow();

        DirectionFactory directionFactory = DirectionFactory.getInstance();

        if (fromColumn > goColumn)
        {
            return directionFactory.LEFT;
        }
        else if (fromColumn < goColumn)
        {
            return directionFactory.RIGHT;
        }
        else if (fromRow > goRow)
        {
            return directionFactory.UP;
        }
        else if (fromRow < goRow)
        {
            return directionFactory.DOWN;
        }
        final StringMaker stringMaker = new StringMaker();
        final String string = stringMaker.append(CellPosition.toString(fromGeographicMapCellPosition)
               ).append(" == ").append(CellPosition.toString(toGeographicMapCellPosition)).toString();
        stringMaker.delete(0, stringMaker.length());
        throw new Exception(stringMaker.append("Error: ").append(string).toString());
    }

    private final String ERROR = "Error: ";
    private final String EQUAL = " == ";
    
    public Direction getEightDirectionFromCellPositionToCellPosition(
            GeographicMapCellPosition fromGeographicMapCellPosition,
            GeographicMapCellPosition toGeographicMapCellPosition)
            throws Exception
    {
        int fromColumn = fromGeographicMapCellPosition.getColumn();
        int fromRow = fromGeographicMapCellPosition.getRow();

        int goColumn = toGeographicMapCellPosition.getColumn();
        int goRow = toGeographicMapCellPosition.getRow();

        DirectionFactory directionFactory = DirectionFactory.getInstance();

        if (fromGeographicMapCellPosition == toGeographicMapCellPosition)
        {
            return directionFactory.NO_DIRECTION;
        }
        else if (fromRow == goRow)
        {
            if (fromColumn > goColumn)
            {
                return directionFactory.LEFT;
            }
            else if (fromColumn < goColumn)
            {
                return directionFactory.RIGHT;
            }
        }
        else if (fromRow > goRow)
        {
            if (fromColumn > goColumn)
            {
                return directionFactory.UP_LEFT;
            }
            else if (fromColumn < goColumn)
            {
                return directionFactory.UP_RIGHT;
            }
            else if (fromColumn == goColumn)
            {
                return directionFactory.UP;
            }

        }
        else if (fromRow < goRow)
        {
            if (fromColumn > goColumn)
            {
                return directionFactory.DOWN_LEFT;
            }
            else if (fromColumn < goColumn)
            {
                return directionFactory.DOWN_RIGHT;
            }
            else if (fromColumn == goColumn)
            {
                return directionFactory.DOWN;
            }
        }

        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(ERROR);
        stringBuffer.append(CellPosition.toString(fromGeographicMapCellPosition));
        stringBuffer.append(EQUAL);
        stringBuffer.append(CellPosition.toString(toGeographicMapCellPosition));

        throw new Exception(stringBuffer.toString());
    }
}
