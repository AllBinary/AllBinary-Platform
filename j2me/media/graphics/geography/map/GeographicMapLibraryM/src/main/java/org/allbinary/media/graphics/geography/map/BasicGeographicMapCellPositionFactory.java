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


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

public class BasicGeographicMapCellPositionFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final GeographicMapCellPosition[][] geographicMapCellPositionArray;
    protected final BasicGeographicMap geographicMapInterface;
    protected final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface;
    private final int columns;
    private final int rows;

    private final AllBinaryTiledLayer tiledLayer;
    
    //protected
    public BasicGeographicMapCellPositionFactory(final BasicGeographicMap geographicMapInterface)
        throws Exception
    {
        this.geographicMapInterface = geographicMapInterface;
        this.geographicMapCellPositionFactoryInterface = this.geographicMapInterface.getGeographicMapCellPositionFactoryInterface();

        this.tiledLayer = this.geographicMapInterface.getAllBinaryTiledLayer();

        this.columns = this.tiledLayer.getColumns();
        this.rows = this.tiledLayer.getRows();

        this.geographicMapCellPositionArray = new GeographicMapCellPosition[this.rows][this.columns];

        this.init();
    }

    private void init() throws Exception
    {
        final AllBinaryTiledLayer tiledLayer = this.tiledLayer;

        final int columns = this.getColumns();
        final int rows = this.getRows();

        final int width = tiledLayer.getCellWidth();
        final int height = tiledLayer.getCellHeight();

        for (int column = 0; column < columns; column++)
        {
            // this.logUtil.putF("Initializing Column: ").append(col, this, commonStrings.INIT);
            for (int row = 0; row < rows; row++)
            {
                // this.logUtil.putF("Initializing Row: ").append(row, this, commonStrings.INIT);
                // GeographicMapCellPosition cellPosition =
                this.createInstance(column, row, width, height);
            }
        }
    }

    public void visit(
        final GeographicMapCellPositionFactoryInitVisitorInterface geographicMapCelPositionFactoryInitVisitorInterface)
        throws Exception
    {
        final AllBinaryTiledLayer tiledLayer = this.tiledLayer;

        final int columns = this.getColumns();
        final int rows = this.getRows();

        //int width = tiledLayer.getCellWidth();
        //int height = tiledLayer.getCellHeight();
        try
        {
            
            GeographicMapCellPosition cellPosition;
        for (int column = 0; column < columns; column++)
        {
            // this.logUtil.putF("Initializing Column: ").append(col, this, commonStrings.INIT);
            for (int row = 0; row < rows; row++)
            {
                // this.logUtil.putF("Initializing Row: ").append(row, this, commonStrings.INIT);
                cellPosition = this.getAt(column, row);
                geographicMapCelPositionFactoryInitVisitorInterface.visit(tiledLayer, cellPosition);
            }
        }
        
        }
        catch(Exception e)
        {
            final CommonSeps commonSeps = CommonSeps.getInstance();
            this.logUtil.put(new StringMaker().append(commonSeps.BRACKET_OPEN).appendint(rows).append(commonSeps.BRACKET_CLOSE).append(commonSeps.BRACKET_OPEN).appendint(columns).append(commonSeps.BRACKET_CLOSE).toString(), this, "visit", e);
            throw e;
        }
    }

    public GeographicMapCellPosition getAt(final int i_column, final int i_row) throws Exception
    {
        //final GeographicMapCellPosition cellPosition = geographicMapCellPositionArray[i_column][i_row];

        /*
         * if(cellPosition == null) { throw new Exception("Should Already Be
         * Loaded"); }
         */

        // return cellPosition;

        /*
        if (i_column >= this.geographicMapCellPositionArray.length
            || i_row >= this.geographicMapCellPositionArray[0].length)
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("columns: ");
            stringBuffer.append(this.getColumns());
            stringBuffer.append(" rows: ");
            stringBuffer.append(this.getRows());
            stringBuffer.append(" col: ");
            stringBuffer.append(i_column);
            stringBuffer.append(" row: ");
            stringBuffer.append(i_row);

            throw new Exception(stringBuffer.toString());
        }
        */

        return geographicMapCellPositionArray[i_row][i_column];
    }

    public GeographicMapCellPosition getInstance(
        final GeographicMapCellPosition anotherMapGeographicMapCellPosition) throws Exception
    {
        return this.getAt(anotherMapGeographicMapCellPosition.getColumn(),
            anotherMapGeographicMapCellPosition.getRow());
    }

    public GeographicMapCellPosition createInstance(final int i_column, final int i_row, final int width, final int height)
        throws Exception
    {
        // String cellPositionKey = GeographicMapCellPosition.toString(i_column, i_row);

        Object cellPositionCanBeNull = this.geographicMapCellPositionArray[i_row][i_column];

        if (cellPositionCanBeNull == null)
        {
            cellPositionCanBeNull = this.geographicMapCellPositionFactoryInterface.getInstance(
                this.geographicMapInterface, i_column, i_row, columns, rows, width, height);
            this.geographicMapCellPositionArray[i_row][i_column] = (GeographicMapCellPosition) cellPositionCanBeNull;
        }

        return (GeographicMapCellPosition) cellPositionCanBeNull;
    }

    public int getColumns()
    {
        return columns;
    }

    public int getRows()
    {
        return rows;
    }
}
