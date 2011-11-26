package allbinary.game.terrain;

import org.allbinary.util.BasicArrayList;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class TerrainEventListener
implements TerrainEventListenerInterface
{
    private final BasicArrayList list = new BasicArrayList();

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }
    
    public void onTerrainEvent(TerrainEvent terrainEvent)
    {
        // LogUtil.put(LogFactory.getInstance("TerrainEvent: " +
        // terrainEvent.getBasicTerrainInfo().getAngle().getValue(), this, "onTerrainEvent"));
        this.list.clear();
        this.list.add(terrainEvent);
    }

    public BasicArrayList getList()
    {
        return list;
    }
}
