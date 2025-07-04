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
package org.allbinary.game.ai.scroller;

import org.allbinary.game.ai.scroller.PacePatrolAI;
import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.terrain.BasicTerrainInfo;
import org.allbinary.game.terrain.TerrainEvent;
import org.allbinary.game.terrain.TerrainEventCircularStaticPool;
import org.allbinary.game.terrain.TerrainEventHandler;
import org.allbinary.game.terrain.TerrainEventListener;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;

/**
 *
 * @author user
 */
public class TerrainPatrolAI extends PacePatrolAI
{
    private final TerrainEventListener terrainEventListener = new TerrainEventListener();
    
    private final Angle DOWN = AngleFactory.getInstance().DOWN;
    
    private final BasicTerrainInfo CLIFF = new BasicTerrainInfo(DOWN);

    public TerrainPatrolAI(final Hashtable hashtable,
            final AllBinaryLayer ownerLayerInterface, final GameInput gameInput)
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
        final BasicArrayList list = this.terrainEventListener.getList();
        
        // Limit pacing to a perch/shelf area
        final int size = list.size();
        for (int index = 0; index < size; index++)
        {
            final TerrainEvent terrainEvent = (TerrainEvent) list.remove(index);
            final BasicTerrainInfo basicTerrainInfo = terrainEvent.getBasicTerrainInfo();
            final Angle angle = basicTerrainInfo.getAngle();

            if (angle == DOWN)
            {
                // LogUtil.put(LogFactory.getInstance("TerrainEvent: " + terrainEvent.getBasicTerrainInfo().getAngle().getValue(), this, commonStrings.PROCESS));
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
