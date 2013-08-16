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

import allbinary.game.layer.AllBinaryTiledLayer;
import allbinary.graphics.color.BasicColor;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class SimpleGeographicMap
{
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(20);

    private final Integer id;
    private final String name;

    protected final int[] cellTypeIdToGeographicMapCellType;

    private final AllBinaryTiledLayer tiledLayer;

    private BasicColor foregroundBasicColor;
    private BasicColor backgroundBasicColor;

    public SimpleGeographicMap(Integer id, String name, int[] cellTypeIdToGeographicMapCellType,
            AllBinaryTiledLayer tiledLayer, BasicColor foregroundBasicColor,
            BasicColor backgroundBasicColor) throws Exception
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
    
    public GeographicMapCellType getCellTypeAt(GeographicMapCellPosition cellPosition)
            throws Exception
    {
        int i_column = cellPosition.getColumn();
        int i_row = cellPosition.getRow();

        int cellTypeId = this.tiledLayer.getCell(i_column, i_row);

        return this.geographicMapCellTypeFactory.getInstance(
                this.cellTypeIdToGeographicMapCellType[cellTypeId]);
    }

    public int getCellTypeFromMapCellTypeInt(int cellTypeId)
    {
        return this.cellTypeIdToGeographicMapCellType[cellTypeId];
    }

    public BasicColor getForegroundBasicColor()
    {
        return foregroundBasicColor;
    }

    private void setForegroundBasicColor(BasicColor foregroundBasicColor)
    {
        this.foregroundBasicColor = foregroundBasicColor;
    }

    public BasicColor getBackgroundBasicColor()
    {
        return backgroundBasicColor;
    }

    private void setBackgroundBasicColor(BasicColor backgroundBasicColor)
    {
        this.backgroundBasicColor = backgroundBasicColor;
    }
}