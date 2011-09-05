package allbinary.media.graphics.geography.map.drop;

import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public interface DropCellPositionGeneratorInterface
{
    String getName();

    void update(AllBinaryGameLayerManager allBinaryGameLayerManager,
            BasicGeographicMap basicGeographicMap) throws Exception;

    void processTick(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception;

    boolean isDropAllowedAt(
            GeographicMapCellPosition geographicMapCellPosition)
            throws Exception;

    //protected void drop(AllBinaryLayerManager allBinaryLayerManager, int index) throws Exception;
}