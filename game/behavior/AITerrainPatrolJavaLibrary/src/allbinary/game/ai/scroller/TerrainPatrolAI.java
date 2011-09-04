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
package allbinary.game.ai.scroller;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.GameInput;
import allbinary.game.terrain.BasicTerrainInfo;
import allbinary.game.terrain.TerrainEvent;
import allbinary.game.terrain.TerrainEventCircularStaticPool;
import allbinary.game.terrain.TerrainEventHandler;
import allbinary.game.terrain.TerrainEventListener;
import allbinary.layer.AllBinaryLayer;
import allbinary.math.Angle;
import allbinary.math.AngleFactory;

/**
 *
 * @author user
 */
public class TerrainPatrolAI extends PacePatrolAI
{
    private final TerrainEventListener terrainEventListener = new TerrainEventListener();
    
    private final Angle DOWN = AngleFactory.getInstance().DOWN;
    
    private BasicTerrainInfo CLIFF = new BasicTerrainInfo(DOWN);

    public TerrainPatrolAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
       throws Exception
    {
        super(hashtable, ownerLayerInterface, gameInput);

        TerrainEventHandler.getInstance(ownerLayerInterface).addListener(
                this.terrainEventListener);

        this.terrainEventListener.onTerrainEvent(
                TerrainEventCircularStaticPool.getInstance().getInstance(this.CLIFF));
    }

    protected void update()
    {
        super.update();

        this.changeDirectionIfCliffReached();
    }

    private void changeDirectionIfCliffReached()
    {
        BasicArrayList list = this.terrainEventListener.getList();
        
        // Limit pacing to a perch/shelf area
        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            TerrainEvent terrainEvent = (TerrainEvent) list.remove(index);
            BasicTerrainInfo basicTerrainInfo = terrainEvent
                    .getBasicTerrainInfo();
            Angle angle = basicTerrainInfo.getAngle();

            if (angle == DOWN)
            {
                // LogUtil.put(LogFactory.getInstance("TerrainEvent: " +
                // terrainEvent.getBasicTerrainInfo().getAngle().getValue(),
                // this, "processAI"));
                this.nextDirection();

                if (!this.isFollowLimitedByTerrain)
                {
                    LogUtil.put(LogFactory.getInstance("Following Limited", this, "onTerrainEvent"));
                    this.isFollowLimitedByTerrain = true;
                }

            }
        }
    }

}
