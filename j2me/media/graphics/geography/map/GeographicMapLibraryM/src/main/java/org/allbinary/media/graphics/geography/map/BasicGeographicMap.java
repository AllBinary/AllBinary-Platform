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
import org.allbinary.layer.Layer;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.util.BasicArrayList;

public class BasicGeographicMap
    extends SimpleGeographicMap
    implements GeographicMapInterface {

    public static final BasicGeographicMap[] NULL_BASIC_GEOGRAPHIC_MAP_ARRAY = new BasicGeographicMap[0];
        
    private final MathUtil mathUtil = MathUtil.getInstance();
    
    private final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory;
    private final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface;
    private final GeographicMapCellTypeFactory geographicMapCellTypeFactory;

    public BasicGeographicMap(final Integer id, final String name, final int[] cellTypeIdToGeographicMapCellType,
        final AllBinaryTiledLayer tiledLayer, final BasicColor foregroundBasicColor,
        final BasicColor backgroundBasicColor,
        final GeographicMapCellPositionFactoryInterface geographicMapCellPositionFactoryInterface,
        final GeographicMapCellPositionBaseFactory geographicMapCellPositionBaseFactory,
        final GeographicMapCellTypeFactory geographicMapCellTypeFactory) throws Exception {
        super(id, name, cellTypeIdToGeographicMapCellType,
            tiledLayer, foregroundBasicColor, backgroundBasicColor);

        this.geographicMapCellPositionFactoryInterface = geographicMapCellPositionFactoryInterface;

        this.geographicMapCellPositionFactory = 
            geographicMapCellPositionBaseFactory.getInstance(this);

        this.geographicMapCellTypeFactory = geographicMapCellTypeFactory;
    }

    //Use getPoint in GeographicMapCellPosition
    /*
    public GPoint getPointAt(
       GeographicMapCellPosition geographicMapCellPosition)
       throws Exception
    {
        AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        int x = allBinaryTiledLayer.getCellHeight() * geographicMapCellPosition.getColumn();
        int y = allBinaryTiledLayer.getCellWidth() * geographicMapCellPosition.getRow();
        return PointFactory.getInstance(x, y);
    }
     */

    public GeographicMapCellPosition getCellPosition(
        final int direction, final GeographicMapCellPosition oldGeographicMapCellPosition)
        throws Exception {
        switch (direction) {
            case 0:
                return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn() - 1, oldGeographicMapCellPosition.getRow());
            case 1:
                return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn() + 1, oldGeographicMapCellPosition.getRow());
            case 2:
                return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() - 1);
            case 3:
                return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() + 1);
            default:
                throw new Exception("Only Four Directions");
        }
    }

    public GeographicMapCellPosition getCellPositionNoThrow(
        final int direction, final GeographicMapCellPosition oldGeographicMapCellPosition)
        throws Exception {
        switch (direction) {
            case 0:
                if(oldGeographicMapCellPosition.getColumn() - 1 >= 0) {
                    return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn() - 1, oldGeographicMapCellPosition.getRow());
                } else {
                    return SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
                }
            case 1:
                if(oldGeographicMapCellPosition.getColumn() + 1 < this.getAllBinaryTiledLayer().getColumns()) {
                    return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn() + 1, oldGeographicMapCellPosition.getRow());
                } else {
                    return SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
                }
            case 2:
                if(oldGeographicMapCellPosition.getRow() - 1 >= 0) {
                    return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() - 1);
                } else {
                    return SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
                }
            case 3:
                if(oldGeographicMapCellPosition.getRow() + 1 < this.getAllBinaryTiledLayer().getRows()) {
                    return this.geographicMapCellPositionFactory.getAt(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() + 1);
                } else {
                    return SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
                }
            default:
                return SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
        }
    }
    
    public boolean isOfFourDirections(final GeographicMapCellPosition oldGeographicMapCellPosition, final GeographicMapCellPosition newGeographicMapCellPosition) throws Exception {
                
        for(int index = 0; index < 4; index++) {
            if (newGeographicMapCellPosition == this.getCellPositionNoThrow(index, oldGeographicMapCellPosition)) {
                return true;
            }
        }

        return false;
    }
    
    @Override
    public GeographicMapCellPosition getCellPositionAtXY(final int x, final int y) throws Exception {
        final AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        final int i_column = this.mathUtil.abs(x / allBinaryTiledLayer.getCellHeight());
        final int i_row = this.mathUtil.abs(y / allBinaryTiledLayer.getCellWidth());

        return this.geographicMapCellPositionFactory.getAt(i_column, i_row);
    }

    @Override
    public GeographicMapCellPosition getCellPositionAtXYNoThrow(final int x, final int y) {
        final AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        final int i_column = this.mathUtil.abs(x / allBinaryTiledLayer.getCellHeight());
        final int i_row = this.mathUtil.abs(y / allBinaryTiledLayer.getCellWidth());

        if (allBinaryTiledLayer.getColumns() > i_column && allBinaryTiledLayer.getRows() > i_row) {
            try {
                return this.geographicMapCellPositionFactory.getAt(i_column, i_row);
            } catch(Exception e) {
                return SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
            }
        } else {
            return SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
        }
    }
    
    public boolean isOnMap(final GeographicMapCellPosition geographicMapCellPosition) throws Exception {
        
        final AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        final int i_column = geographicMapCellPosition.getColumn();
        final int i_row = geographicMapCellPosition.getRow();

        if (allBinaryTiledLayer.getColumns() > i_column
            && allBinaryTiledLayer.getRows() > i_row) {
            return true;
        } else {
            return false;
        }
    }
    
    public BasicArrayList getCellPositionAtNoThrow(final int x, final int y, final int x2, final int y2,
        final BasicArrayList geographicMapCellPositionList) throws Exception {
        geographicMapCellPositionList.clear();

        final AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        final int i_columnMin = this.mathUtil.abs(x / allBinaryTiledLayer.getCellHeight());
        final int i_rowMin = this.mathUtil.abs(y / allBinaryTiledLayer.getCellWidth());
        final int i_columnMax = this.mathUtil.abs(x2 / allBinaryTiledLayer.getCellHeight()) + 1;
        final int i_rowMax = this.mathUtil.abs(y2 / allBinaryTiledLayer.getCellWidth()) + 1;

        for (int columnIndex = i_columnMin; columnIndex < i_columnMax; columnIndex++) {
            for (int rowIndex = i_rowMin; rowIndex < i_rowMax; rowIndex++) {
                if (allBinaryTiledLayer.getColumns() > columnIndex
                    && allBinaryTiledLayer.getRows() > rowIndex) {
                    geographicMapCellPositionList.add(geographicMapCellPositionFactory.getAt(columnIndex, rowIndex));
                }
            }
        }

        return geographicMapCellPositionList;
    }

    // Assumes array is tied to the ratio of tile cell width and height
    @Override
    public boolean getCellPositionsAt(final Layer layer,
        final GeographicMapCellPosition[][] currentCellPositionArray,
        final GeographicMapCellPosition[][] cellPositionArray) throws Exception {
        boolean hasChanged = false;

        final int size = cellPositionArray.length;
        final int size2 = cellPositionArray[0].length;

        final int xPortion = layer.getXP() / (size - 1);
        final int yPortion = layer.getYP() / (size - 1);
        for (int index = 0; index < size; index++) {
            for (int index2 = 0; index2 < size2; index2++) {
                final int x = xPortion * index;
                final int y = yPortion * index;
                cellPositionArray[index][index2] = this.getCellPositionAtXY(x, y);

                if (currentCellPositionArray[index][index2] != cellPositionArray[index][index2]) {
                    hasChanged = true;
                }
            }
        }

        return hasChanged;
    }

    @Override
    public GeographicMapCellType getCellTypeAtXY(int x, int y) throws Exception {
        GeographicMapCellPosition cellPosition = this.getCellPositionAtXY(x, y);
        return this.getCellTypeAt(cellPosition);
    }

    @Override
    public BasicGeographicMapCellPositionFactory getGeographicMapCellPositionFactory() {
        return this.geographicMapCellPositionFactory;
    }

    /**
     * @return the geographicMapCellPositionFactoryInterface
     */
    @Override
    public GeographicMapCellPositionFactoryInterface getGeographicMapCellPositionFactoryInterface() {
        return this.geographicMapCellPositionFactoryInterface;
    }

    public GeographicMapCellTypeFactory getGeographicMapCellTypeFactory() {
        return this.geographicMapCellTypeFactory;
    }
}
