package allbinary.media.graphics.geography.map.drop;

import org.allbinary.util.BasicArrayList;

import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.layer.AllBinaryTiledLayer;
import allbinary.game.rand.MyRandomFactory;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import allbinary.media.graphics.geography.map.BasicGeographicMap;

public class AnyRandomDropCellPositionGenerator
extends BaseDropCellPositionGenerator
{
    protected final BasicArrayList list = new BasicArrayList();
    protected BasicGeographicMap basicGeographicMap;
    
    public void update(AllBinaryGameLayerManager allBinaryGameLayerManager,
            BasicGeographicMap raceTrackGeographicMap) throws Exception
    {
        this.basicGeographicMap = raceTrackGeographicMap;
        
        BasicGeographicMapCellPositionFactory basicGeographicMapCellPositionFactory =
                raceTrackGeographicMap.getGeographicMapCellPositionFactory();
        
        MyRandomFactory randomFactory = MyRandomFactory.getInstance();
        
        AllBinaryTiledLayer tiledLayer = 
                raceTrackGeographicMap.getAllBinaryTiledLayer();
        
        int total = tiledLayer.getColumns() * tiledLayer.getRows();
        
        int randomColumn;
        int randomRow;
        GeographicMapCellPosition geographicMapCellPosition;

        //Create a list of random drops
        for(int index = total; --index >= 0;)
        {
            randomColumn = randomFactory.getAbsoluteNextInt(tiledLayer.getColumns());
            randomRow = randomFactory.getAbsoluteNextInt(tiledLayer.getRows());
            
            geographicMapCellPosition = 
                    basicGeographicMapCellPositionFactory.getInstance(
                            randomColumn, randomRow);

            //!this.list.contains(geographicMapCellPosition)
            if(this.isDropAllowedAt(geographicMapCellPosition))
            {
                this.list.add(geographicMapCellPosition);
            }
        }
    }

    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        
    }
}
