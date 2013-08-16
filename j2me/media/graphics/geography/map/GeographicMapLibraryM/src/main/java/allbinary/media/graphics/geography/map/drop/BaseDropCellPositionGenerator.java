package allbinary.media.graphics.geography.map.drop;

import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import allbinary.media.graphics.geography.map.BasicGeographicMap;

public class BaseDropCellPositionGenerator
implements DropCellPositionGeneratorInterface
{
    /* (non-Javadoc)
     * @see allbinary.media.graphics.geography.map.racetrack.drop.DropCellPositionGeneratorInterface#getName()
     */
    public String getName()
    {
        return this.getClass().getName();
    }

    public void update(AllBinaryGameLayerManager allBinaryGameLayerManager,
            BasicGeographicMap geographicMapInterface) throws Exception
    {
        
    }

    public boolean isDropAllowedAt(
            GeographicMapCellPosition geographicMapCellPosition)
            throws Exception
    {
        return true;
    }

    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        
    }
}
