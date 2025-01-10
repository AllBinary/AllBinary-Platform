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
import org.allbinary.util.BasicArrayList;

public class BasicGeographicMap
    extends SimpleGeographicMap
    implements GeographicMapInterface {

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

        this.geographicMapCellPositionFactory
            = geographicMapCellPositionBaseFactory.getInstance(this);

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
                return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn() - 1, oldGeographicMapCellPosition.getRow());
            case 1:
                return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn() + 1, oldGeographicMapCellPosition.getRow());
            case 2:
                return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() - 1);
            case 3:
                return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() + 1);
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
                    return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn() - 1, oldGeographicMapCellPosition.getRow());
                } else {
                    return null;
                }
            case 1:
                if(oldGeographicMapCellPosition.getColumn() + 1 < this.getAllBinaryTiledLayer().getColumns()) {
                    return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn() + 1, oldGeographicMapCellPosition.getRow());
                } else {
                    return null;
                }
            case 2:
                if(oldGeographicMapCellPosition.getRow() - 1 >= 0) {
                    return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() - 1);
                } else {
                    return null;
                }
            case 3:
                if(oldGeographicMapCellPosition.getRow() + 1 < this.getAllBinaryTiledLayer().getRows()) {
                    return geographicMapCellPositionFactory.getInstance(oldGeographicMapCellPosition.getColumn(), oldGeographicMapCellPosition.getRow() + 1);
                } else {
                    return null;
                }
            default:
                return null;
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
    
    public GeographicMapCellPosition getCellPositionAt(final int x, final int y) throws Exception {
        final AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        final int i_column = Math.abs(x / allBinaryTiledLayer.getCellHeight());
        final int i_row = Math.abs(y / allBinaryTiledLayer.getCellWidth());

        return geographicMapCellPositionFactory.getInstance(i_column, i_row);
    }

    public GeographicMapCellPosition getCellPositionAtNoThrow(final int x, final int y) throws Exception {
        final AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        final int i_column = Math.abs(x / allBinaryTiledLayer.getCellHeight());
        final int i_row = Math.abs(y / allBinaryTiledLayer.getCellWidth());

        if (allBinaryTiledLayer.getColumns() > i_column
            && allBinaryTiledLayer.getRows() > i_row) {
            return geographicMapCellPositionFactory.getInstance(i_column, i_row);
        } else {
            return null;
        }
    }
    
    public boolean isOnMap(final GeographicMapCellPosition geographicMapCellPosition) throws Exception {
        
        final AllBinaryTiledLayer allBinaryTiledLayer = this.getAllBinaryTiledLayer();
        final int i_column = geographicMapCellPosition.getColumns();
        final int i_row = geographicMapCellPosition.getRows();

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
        final int i_columnMin = Math.abs(x / allBinaryTiledLayer.getCellHeight());
        final int i_rowMin = Math.abs(y / allBinaryTiledLayer.getCellWidth());
        final int i_columnMax = Math.abs(x2 / allBinaryTiledLayer.getCellHeight()) + 1;
        final int i_rowMax = Math.abs(y2 / allBinaryTiledLayer.getCellWidth()) + 1;

        for (int columnIndex = i_columnMin; columnIndex < i_columnMax; columnIndex++) {
            for (int rowIndex = i_rowMin; rowIndex < i_rowMax; rowIndex++) {
                if (allBinaryTiledLayer.getColumns() > columnIndex
                    && allBinaryTiledLayer.getRows() > rowIndex) {
                    geographicMapCellPositionList.add(geographicMapCellPositionFactory.getInstance(columnIndex, rowIndex));
                }
            }
        }

        return geographicMapCellPositionList;
    }

    // Assumes array is tied to the ratio of tile cell width and height
    public boolean getCellPositionsAt(final Layer layer,
        final GeographicMapCellPosition[][] currentCellPositionArray,
        final GeographicMapCellPosition[][] cellPositionArray) throws Exception {
        boolean hasChanged = false;

        final int size = cellPositionArray.length;
        final int size2 = cellPositionArray[0].length;

        final int xPortion = layer.getX() / (size - 1);
        final int yPortion = layer.getY() / (size - 1);
        for (int index = 0; index < size; index++) {
            for (int index2 = 0; index2 < size2; index2++) {
                final int x = xPortion * index;
                final int y = yPortion * index;
                cellPositionArray[index][index2] = this.getCellPositionAt(x, y);

                if (currentCellPositionArray[index][index2] != cellPositionArray[index][index2]) {
                    hasChanged = true;
                }
            }
        }

        return hasChanged;
    }

    public GeographicMapCellType getCellTypeAt(int x, int y) throws Exception {
        GeographicMapCellPosition cellPosition = this.getCellPositionAt(x, y);
        return this.getCellTypeAt(cellPosition);
    }

    public BasicGeographicMapCellPositionFactory getGeographicMapCellPositionFactory() {
        return geographicMapCellPositionFactory;
    }

    /**
     * @return the geographicMapCellPositionFactoryInterface
     */
    public GeographicMapCellPositionFactoryInterface getGeographicMapCellPositionFactoryInterface() {
        return geographicMapCellPositionFactoryInterface;
    }

    public GeographicMapCellTypeFactory getGeographicMapCellTypeFactory() {
        return this.geographicMapCellTypeFactory;
    }
}
