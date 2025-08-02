package org.allbinary.game.terrain;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.util.BasicArrayList;

public class TerrainEventListener
implements TerrainEventListenerInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final BasicArrayList list = new BasicArrayList();

    @Override
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    @Override
    public void onTerrainEvent(TerrainEvent terrainEvent)
    {
        // logUtil.put("TerrainEvent: " +
        // terrainEvent.getBasicTerrainInfo().getAngle().getValue(), this, "onTerrainEvent");
        this.list.clear();
        this.list.add(terrainEvent);
    }

    public BasicArrayList getList()
    {
        return list;
    }
}
