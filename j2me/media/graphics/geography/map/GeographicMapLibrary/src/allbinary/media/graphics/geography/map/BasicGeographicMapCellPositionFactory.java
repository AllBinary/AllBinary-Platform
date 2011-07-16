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
package allbinary.media.graphics.geography.map;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.layer.AllBinaryTiledLayer;

public class BasicGeographicMapCellPositionFactory
{
    protected final GeographicMapCellPosition[][] geographicMapCellPositionArray;
    protected final BasicGeographicMap geographicMapInterface;
    protected final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface;
    protected final int columns;
    protected final int rows;

    private final AllBinaryTiledLayer tiledLayer;
    
    protected BasicGeographicMapCellPositionFactory(BasicGeographicMap geographicMapInterface)
        throws Exception
    {
        this.geographicMapInterface = geographicMapInterface;
        this.geographicMapCellPositionFactoryInterface = this.geographicMapInterface.getGeographicMapCellPositionFactoryInterface();

        this.tiledLayer = this.geographicMapInterface.getAllBinaryTiledLayer();

        this.columns = this.tiledLayer.getColumns();
        this.rows = this.tiledLayer.getRows();

        this.geographicMapCellPositionArray = new GeographicMapCellPosition[rows][columns];

        this.init();
    }

    private void init() throws Exception
    {
        AllBinaryTiledLayer tiledLayer = this.tiledLayer;

        int columns = this.getColumns();
        int rows = this.getRows();

        int width = tiledLayer.getCellWidth();
        int height = tiledLayer.getCellHeight();

        for (int column = 0; column < columns; column++)
        {
            // LogUtil.put(LogFactory.getInstance("Initializing Column: " + col, this, CommonStrings.getInstance().INIT));
            for (int row = 0; row < rows; row++)
            {
                // LogUtil.put(LogFactory.getInstance("Initializing Row: " + row, this, CommonStrings.getInstance().INIT));
                // GeographicMapCellPosition cellPosition =
                this.createInstance(column, row, width, height);
            }
        }
    }

    public void visit(
        GeographicMapCelPositionFactoryInitVisitorInterface geographicMapCelPositionFactoryInitVisitorInterface)
        throws Exception
    {
        AllBinaryTiledLayer tiledLayer = this.tiledLayer;

        int columns = this.getColumns();
        int rows = this.getRows();

        //int width = tiledLayer.getCellWidth();
        //int height = tiledLayer.getCellHeight();
        try
        {
            
        for (int column = 0; column < columns; column++)
        {
            // LogUtil.put(LogFactory.getInstance("Initializing Column: " + col, this, CommonStrings.getInstance().INIT));
            for (int row = 0; row < rows; row++)
            {
                // LogUtil.put(LogFactory.getInstance("Initializing Row: " + row, this, CommonStrings.getInstance().INIT));
                GeographicMapCellPosition cellPosition = this.getInstance(column, row);
                geographicMapCelPositionFactoryInitVisitorInterface.visit(tiledLayer, cellPosition);
            }
        }
        
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("[" + rows + "][" + columns + "]", this, "visit", e));
            throw e;
        }
    }

    public GeographicMapCellPosition getInstance(int i_column, int i_row) throws Exception
    {
        // GeographicMapCellPosition cellPosition =
        // geographicMapCellPositionArray[i_column][i_row];

        /*
         * if(cellPosition == null) { throw new Exception("Should Already Be
         * Loaded"); }
         */

        // return cellPosition;

        /*
        if (i_column >= geographicMapCellPositionArray.length
            || i_row >= geographicMapCellPositionArray[0].length)
        {
            StringBuilder stringBuffer = new StringBuilder();

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
        GeographicMapCellPosition anotherMapGeographicMapCellPosition) throws Exception
    {
        return this.getInstance(anotherMapGeographicMapCellPosition.getColumn(),
            anotherMapGeographicMapCellPosition.getRow());
    }

    public GeographicMapCellPosition createInstance(int i_column, int i_row, int width, int height)
        throws Exception
    {
        // String cellPositionKey = GeographicMapCellPosition.toString(i_column, i_row);

        GeographicMapCellPosition cellPosition = geographicMapCellPositionArray[i_row][i_column];

        if (cellPosition == null)
        {
            cellPosition = this.geographicMapCellPositionFactoryInterface.getInstance(
                this.geographicMapInterface, i_column, i_row, columns, rows, width, height);
            geographicMapCellPositionArray[i_row][i_column] = cellPosition;
        }

        return cellPosition;
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
