package org.allbinary.game.terrain;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class TerrainEventListener
implements TerrainEventListenerInterface
{
    private final BasicArrayList list = new BasicArrayList();

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
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
