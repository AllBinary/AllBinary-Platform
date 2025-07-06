/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.CircularIndexUtil;
import org.allbinary.game.layer.geographic.map.LayerCoveringCellPositionsUtil;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.CellPositionsUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author user
 */
public class GeographicMapCellPositionArea
{
    private final CellPositionsUtil cellPositionsUtil = CellPositionsUtil.getInstance();
    private final LayerCoveringCellPositionsUtil layerCoveringCellPositionsUtil = LayerCoveringCellPositionsUtil.getInstance();
    
    private final BasicArrayList reusableOccupyingGeographicMapCellPositionList =
        new BasicArrayList(4);
    private final BasicArrayList reusableSurroundingGeographicMapCellPositionList =
        new BasicArrayList(12);

    private final CircularIndexUtil surroundingCircularIndexUtil =
        CircularIndexUtil.getInstance(0);

    private final AllBinaryLayer layerInterface;
    
    private BasicArrayList LIST = BasicArrayListUtil.getInstance().getImmutableInstance();
    private BasicArrayList occupyingGeographicMapCellPositionList = LIST;
    private BasicArrayList surroundingGeographicMapCellPositionList = LIST;

    public GeographicMapCellPositionArea(final AllBinaryLayer layerInterface)
        throws Exception
    {
        this.layerInterface = layerInterface;
    }

    public void update(final BasicGeographicMap geographicMapInterface)
        throws Exception
    {
        //final GeographicMapCellPosition topLeftGeographicMapCellPosition =
            //geographicMapInterface.getCellPositionAt(layerInterface.getX(), layerInterface.getY());
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append(layerInterface.getName()).append(" c: ").append(((PathFindingLayerInterface) layerInterface).getCurrentGeographicMapCellPosition()).append(' ').append(((PathFindingLayerInterface) layerInterface).getTopLeftGeographicMapCellPosition()).append(" topLeftGeographicMapCellPosition: ").append(topLeftGeographicMapCellPosition).toString(), this, "visit"));
        
        this.occupyingGeographicMapCellPositionList =
            layerCoveringCellPositionsUtil.getAll(
            geographicMapInterface, layerInterface,
                layerInterface.getX(), layerInterface.getY(),
                reusableOccupyingGeographicMapCellPositionList);

        this.surroundingGeographicMapCellPositionList =
            cellPositionsUtil.getAllSurrounding(
            geographicMapInterface,
            occupyingGeographicMapCellPositionList,
            reusableSurroundingGeographicMapCellPositionList);

        this.surroundingCircularIndexUtil.setSize(
            this.surroundingGeographicMapCellPositionList.size());
    }

    /**
     * @return the occupyingGeographicMapCellPositionList
     */
    public BasicArrayList getOccupyingGeographicMapCellPositionList()
    {
        return occupyingGeographicMapCellPositionList;
    }

    /**
     * @return the surroundingGeographicMapCellPositionList
     */
    public BasicArrayList getSurroundingGeographicMapCellPositionList()
    {
        return surroundingGeographicMapCellPositionList;
    }

    public GeographicMapCellPosition getNextSurroundingGeographicMapCellPosition()
    {
        final GeographicMapCellPosition geographicMapCellPosition =
            (GeographicMapCellPosition)
            this.surroundingGeographicMapCellPositionList.get(
            this.surroundingCircularIndexUtil.getIndex());

        this.surroundingCircularIndexUtil.next();

        return geographicMapCellPosition;
    }
}
