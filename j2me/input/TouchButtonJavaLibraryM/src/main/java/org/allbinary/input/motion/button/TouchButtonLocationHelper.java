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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;

public class TouchButtonLocationHelper
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final int totalColumns;
    private final int totalRows;
    
    private int columnsRemainder;
    private int rowsRemainder;

    private int columnsRemainderHalf;
    private int rowsRemainderHalf;
    
    public TouchButtonLocationHelper()
    {
        int buttonSize = CommonButtons.getInstance().STANDARD_BUTTON_SIZE;
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        //PreLogUtil.put(commonStrings.START_LABEL + displayInfo.toString(), this, "TouchButtonLocationHelper");

        this.totalColumns = displayInfo.getLastWidth() / buttonSize;
        this.totalRows = displayInfo.getLastHeight() / buttonSize;

        this.columnsRemainder = displayInfo.getLastWidth() % buttonSize;
        this.columnsRemainderHalf = (this.columnsRemainder >> 1);

        this.rowsRemainder = displayInfo.getLastHeight() % buttonSize;
        this.rowsRemainderHalf = (this.rowsRemainder >> 1);
    }
    
    public int getTotalColumns()
    {
        return totalColumns;
    }

    public int getTotalRows()
    {
        return totalRows;
    }

    public int getColumnsRemainder()
    {
        return columnsRemainder;
    }

    public int getRowsRemainder()
    {
        return rowsRemainder;
    }

    public int getColumnsRemainderHalf()
    {
        return columnsRemainderHalf;
    }

    public int getRowsRemainderHalf()
    {
        return rowsRemainderHalf;
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("totalColumns: ");
        stringBuffer.append(totalColumns);

        stringBuffer.append(" totalRows: ");
        stringBuffer.append(totalRows);

        stringBuffer.append(" columnsRemainder: ");
        stringBuffer.append(columnsRemainder);

        stringBuffer.append(" rowsRemainder: ");
        stringBuffer.append(rowsRemainder);

        stringBuffer.append(" columnsRemainderHalf: ");
        stringBuffer.append(columnsRemainderHalf);
        
        stringBuffer.append(" rowsRemainderHalf: ");
        stringBuffer.append(rowsRemainderHalf);

        return stringBuffer.toString();
    }
}
