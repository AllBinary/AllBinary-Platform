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

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.string.CommonStrings;

public class SimpleGeographicMap
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Integer id;
    private final String name;

    protected final int[] cellTypeIdToGeographicMapCellType;

    private final AllBinaryTiledLayer tiledLayer;

    private BasicColor foregroundBasicColor = BasicColorFactory.getInstance().NULL_COLOR;
    private BasicColor backgroundBasicColor = BasicColorFactory.getInstance().NULL_COLOR;

    public SimpleGeographicMap(final Integer id, final String name, final int[] cellTypeIdToGeographicMapCellType,
            final AllBinaryTiledLayer tiledLayer, final BasicColor foregroundBasicColor,
            final BasicColor backgroundBasicColor) throws Exception
    {
        this.id = id;
        this.name = name;

        this.cellTypeIdToGeographicMapCellType = cellTypeIdToGeographicMapCellType;

        this.tiledLayer = tiledLayer;
        this.setBackgroundBasicColor(backgroundBasicColor);
        this.setForegroundBasicColor(foregroundBasicColor);
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void reset()
    {

    }

    public AllBinaryTiledLayer getAllBinaryTiledLayer()
    {
        return tiledLayer;
    }

    private final GeographicMapCellTypeFactory geographicMapCellTypeFactory = 
        GeographicMapCellTypeFactory.getInstance();
    
    public GeographicMapCellType getCellTypeAt(final GeographicMapCellPosition cellPosition)
            throws Exception
    {
        final int i_column = cellPosition.getColumn();
        final int i_row = cellPosition.getRow();

        int cellTypeId = this.tiledLayer.getCell(i_column, i_row);

        if(cellTypeId < 0) {
            cellTypeId = this.tiledLayer.getAnimatedTile(cellTypeId);
        }

        return this.geographicMapCellTypeFactory.getInstance(
                this.cellTypeIdToGeographicMapCellType[cellTypeId]);
    }

    public int getCellTypeFromMapCellTypeInt(final int cellTypeId)
    {
        return this.cellTypeIdToGeographicMapCellType[cellTypeId];
    }

    public BasicColor getForegroundBasicColor()
    {
        return foregroundBasicColor;
    }

    private void setForegroundBasicColor(final BasicColor foregroundBasicColor)
    {
        this.foregroundBasicColor = foregroundBasicColor;
    }

    public BasicColor getBackgroundBasicColor()
    {
        return backgroundBasicColor;
    }

    private void setBackgroundBasicColor(final BasicColor backgroundBasicColor)
    {
        this.backgroundBasicColor = backgroundBasicColor;
    }
}