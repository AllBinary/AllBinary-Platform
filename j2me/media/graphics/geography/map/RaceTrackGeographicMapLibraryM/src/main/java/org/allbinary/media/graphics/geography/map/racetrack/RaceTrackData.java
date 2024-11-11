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
package org.allbinary.media.graphics.geography.map.racetrack;

import javax.microedition.lcdui.Image;

import org.allbinary.graphics.CellPosition;

public class RaceTrackData
{
    private Image tileSetImage;
    private Image miniTileSetImage;
    private int cellWidth;
    private int cellHeight;
    private int miniCellWidth;
    private int miniCellHeight;
    private int[][] mapArray;
    private int[] cellTypeIdToGeographicMapCellTypeArray;
    private CellPosition[] NO_OVER_PASSES_ARRAY = new CellPosition[0];
    private CellPosition[] overPassGeographicMapCellPositionArray = NO_OVER_PASSES_ARRAY;
    private Integer id;

    public RaceTrackData(final Integer id, final int cellWidth, final int cellHeight, final int miniCellWidth, final int miniCellHeight)
    {
        this(id, cellWidth, cellHeight, miniCellWidth, miniCellHeight, null);
    }

    public RaceTrackData(final Integer id, final int cellWidth, final int cellHeight, final int miniCellWidth, final int miniCellHeight, final int[][] mapArray)
    {
        this.id = id;

        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        this.miniCellWidth = miniCellWidth;
        this.miniCellHeight = miniCellHeight;

        this.mapArray = mapArray;
        //this.setCellTypeIdToGeographicMapCellTypeArray(cellTypeIdToGeographicMapCellTypeArray);
        
    }
    
    protected void setMapArray(int[][] mapArray)
    {
        //LogUtil.put(LogFactory.getInstance("Set Map Array: Rows: " + mapArray.length + "Cols:  " + mapArray[0].length, this, "setMapArray"));
        this.mapArray = mapArray;
    }

    public int[][] getMapArray()
    {
        return mapArray;
    }

    protected void setCellTypeIdToGeographicMapCellTypeArray(
       int[] cellTypeIdToGeographicMapCellTypeArray)
    {
        this.cellTypeIdToGeographicMapCellTypeArray = cellTypeIdToGeographicMapCellTypeArray;
    }

    public int[] getCellTypeIdToGeographicMapCellTypeArray()
    {
        return cellTypeIdToGeographicMapCellTypeArray;
    }

    private void setCellWidth(int cellWidth)
    {
        this.cellWidth = cellWidth;
    }

    public int getCellWidth()
    {
        return cellWidth;
    }

    private void setCellHeight(int cellHeight)
    {
        this.cellHeight = cellHeight;
    }

    public int getCellHeight()
    {
        return cellHeight;
    }

    public CellPosition[] getOverPassGeographicMapCellPositionArray()
    {
        return overPassGeographicMapCellPositionArray;
    }

    public void setOverPassGeographicMapCellPositionArray(CellPosition[] overPassGeographicMapCellPositionArray)
    {
        this.overPassGeographicMapCellPositionArray = overPassGeographicMapCellPositionArray;
    }

    public Image getTileSetImage()
    {
        return tileSetImage;
    }

    public void setTileSetImage(Image tileSetImage)
    {
        this.tileSetImage = tileSetImage;
    }

    public Image getMiniTileSetImage()
    {
        return miniTileSetImage;
    }

    public void setMiniTileSetImage(Image miniTileSetImage)
    {
        this.miniTileSetImage = miniTileSetImage;
    }

    public int getMiniCellWidth()
    {
        return miniCellWidth;
    }

    public void setMiniCellWidth(int miniCellWidth)
    {
        this.miniCellWidth = miniCellWidth;
    }

    public int getMiniCellHeight()
    {
        return miniCellHeight;
    }

    public void setMiniCellHeight(int miniCellHeight)
    {
        this.miniCellHeight = miniCellHeight;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
}
