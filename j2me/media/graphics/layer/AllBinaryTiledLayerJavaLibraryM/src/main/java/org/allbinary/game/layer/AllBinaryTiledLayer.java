/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.layer.Layer;
import org.allbinary.layer.NamedInterface;
import org.allbinary.string.CommonStrings;

public class AllBinaryTiledLayer extends Layer
    implements NamedInterface, PaintableInterface
//LayerInterface
{
    private int cellWidth;
    private int cellHeight;
    
    private final int halfWidth;
    private final int halfHeight;
    private final int halfCellWidth;
    private final int halfCellHeight;

    private final Integer dataId;
    
    public AllBinaryTiledLayer(
            final Integer dataId, final int width, final int height, final int cellWidth, final int cellHeight)
    {
        //TWB LayerInterface to Layer
        //super(tiledLayer.getWidth(), tiledLayer.getHeight());
        super(0, 0);
        
        this.dataId = dataId;
        
        this.setLayerWidth(width);
        this.setLayerHeight(height);
        //LogUtil.put(LogFactory.getInstance("TiledLayer: w: " + width + " h: " + height, this, commonStrings.CONSTRUCTOR));
        super.setPosition(0, 0, 0);
        //LogUtil.put(LogFactory.getInstance("TiledLayer: x: " + this.x + " y: " + this.y, this, commonStrings.CONSTRUCTOR));

        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        
        this.halfWidth = (this.getWidth() >> 1);
        this.halfHeight = (this.getHeight() >> 1);
        //LogUtil.put(LogFactory.getInstance("TiledLayer: hw: " + this.halfWidth + " hh: " + this.halfHeight, this, commonStrings.CONSTRUCTOR));
        
        this.halfCellWidth = (cellWidth >> 1);
        this.halfCellHeight = (cellHeight >> 1);
                
    }

    public void setCells(final int[][] mapTwoDArray)
    {
        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Start: r: ");
        stringBuffer.append(mapTwoDArray.length);
        stringBuffer.append(" c: ");
        stringBuffer.append(mapTwoDArray[0].length);
        stringBuffer.append(" rows: ");
        stringBuffer.append(this.getRows());
        stringBuffer.append(" columns: ");
        stringBuffer.append(this.getColumns());

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "setCells"));

        final int rows = this.getRows();
        final int columns = this.getColumns();

        for (int col = 0; col < columns; col++)
        {
            for (int row = 0; row < rows; row++)
            {
                this.setCell(col, row, mapTwoDArray[row][col]);
            }
        }
    }

    public void updateCells(final int[][] mapTwoDArray, final int fromTileId, final int toTileId)
    {
//        final StringMaker stringBuffer = new StringMaker();

//        final String UPDATE_CELLS = "updateCells";
//        final String TO = " -> ";
//        final String AT = " at:";
//        LogUtil.put(LogFactory.getInstance(stringBuffer.append(fromTileId).append(TO).append(toTileId).toString(), this, UPDATE_CELLS));

        final int rows = this.getRows();
        final int columns = this.getColumns();

        for (int col = 0; col < columns; col++)
        {
            for (int row = 0; row < rows; row++)
            {
                if(fromTileId == mapTwoDArray[row][col]) {
//                    stringBuffer.delete(0, stringBuffer.length());
//                    LogUtil.put(LogFactory.getInstance(stringBuffer.append(fromTileId).append(TO).append(toTileId).append(AT).append(col).append(',').append(row).toString(), this, UPDATE_CELLS));
                    mapTwoDArray[row][col] = toTileId;
                    this.setCell(col, row, mapTwoDArray[row][col]);
                }
            }
        }
    }
    
    public String getName()
    {
        return this.getClass().getName();
    }

    public void paint(final Graphics graphics)
    {
    }

    public void paintThreed(final Graphics graphics)
    {
    }
    
    public int getX2()
    {
        return this.x + this.getWidth();
    }

    public int getY2()
    {
        return this.y + this.getHeight();
    }
    
    public int getZ2()
    {
       return (int) (this.z + this.getDepth());
    }

    public int getCellWidth()
    {
        return cellWidth;
    }

    public int getCellHeight()
    {
        return cellHeight;
    }

    public int getCell(final int col, final int row)
    {
        return -1;
    }

    public int getColumns()
    {
        return -1;
    }

    public int getRows()
    {
        return -1;
    }

    public void setCell(final int col, final int row, final int tileIndex)
    {
        
    }

    public int getHalfWidth()
    {
        return halfWidth;
    }

    public int getHalfHeight()
    {
        return halfHeight;
    }

    public Integer getDataId()
    {
        return dataId;
    }

    public int getHalfCellWidth()
    {
        return halfCellWidth;
    }

    public int getHalfCellHeight()
    {
        return halfCellHeight;
    }

    public boolean isOnTileLayer(final int column, final int row)
    {
        if (this.getColumns() > column && this.getRows() > row && row >= 0 && column >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int getAnimatedTile(final int animationTileIndex) {
        return animationTileIndex;
    }
    
}
