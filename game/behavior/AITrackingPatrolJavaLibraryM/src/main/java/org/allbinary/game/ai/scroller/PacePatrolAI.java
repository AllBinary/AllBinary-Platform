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

import java.util.Hashtable;

import javax.microedition.lcdui.Canvas;

import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionCompositeInterface;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.game.tracking.TrackingEventHandler;
import org.allbinary.game.tracking.TrackingEventListenerInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.util.BasicArrayList;

public class PacePatrolAI extends BasePatrolAI
        implements TrackingEventListenerInterface
{
    private BasicArrayList trackingList;

    //private TimeDelayHelper moveTimeDelayHelper = new TimeDelayHelper(75);

    // private final int MIN_DISTANCE = 0;
    private int firingDistance;
    protected boolean isFollowLimitedByTerrain = false;

    public PacePatrolAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
            throws Exception
    {
        super(hashtable, ownerLayerInterface, gameInput);

        TrackingEventHandler.getInstance().addListener(this);

        this.trackingList = new BasicArrayList();
    }

    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        this.update();

        Direction direction = this.setFiringDirectionForTargetIfInRange();

        DirectionFactory directionFactory = 
            DirectionFactory.getInstance();

        // logUtil.put("Key: " + this.keyDirection, this, commonStrings.PROCESS);
        if (direction == directionFactory.LEFT)
        {
            // logUtil.put("Fire", this, commonStrings.PROCESS);

            //Turn around before firing or if in follow mode
            if(this.lastKeyDirection != keyDirection 
                    || !this.isFollowLimitedByTerrain)
            {
                super.processAI(Canvas.LEFT);
            }
                
            this.lastKeyDirection = keyDirection = Canvas.LEFT;
            xTotalDistance = 0;

            //
            super.processAI(Canvas.KEY_NUM1);

            //moveTimeDelayHelper.setStartTime();
        }
        else if (direction == directionFactory.RIGHT)
        {
            // logUtil.put("Fire", this, commonStrings.PROCESS);

            //Turn around before firing
            if(this.lastKeyDirection != keyDirection
                    || !this.isFollowLimitedByTerrain)
            {
                super.processAI(Canvas.RIGHT);
            }
            
            this.lastKeyDirection = keyDirection = Canvas.RIGHT;
            xTotalDistance = 0;
            
            super.processAI(Canvas.KEY_NUM1);

            //moveTimeDelayHelper.setStartTime();
        }
        else
        //Move in the current direction regardless
        //if (moveTimeDelayHelper.isTime())
        {
            super.processAI(this.keyDirection);
        }
    }

    private Direction setFiringDirectionForTargetIfInRange()
    {
        DirectionFactory directionFactory = 
            DirectionFactory.getInstance();
        
        Direction direction = directionFactory.NOT_BORDERED_WITH;

        AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
        
        int size = this.trackingList.size();
        for (int index = 0; index < size; index++)
        {
            TrackingEvent lastTrackingEvent = 
                    (TrackingEvent) this.trackingList.get(0);

            LayerInterface lastTrackingLayerInterface = 
                    lastTrackingEvent.getLayerInterface();

            int x = lastTrackingLayerInterface.getX();
            int y = lastTrackingLayerInterface.getY();

            int yDistance = ownerLayerInterface.getY() - y - ownerLayerInterface.getHeight();
            int xDistance = ownerLayerInterface.getX() - x - ownerLayerInterface.getWidth();

            // logUtil.put("x: " + x + " y: " + y + " xDistance: " + xDistance + " yDistance: " + yDistance, this, commonStrings.PROCESS);

            int absXDistance = Math.abs(xDistance);
            int absYDistance = Math.abs(yDistance);

            // Is closer in x direction
            //if (absYDistance <= absXDistance)
            if (absYDistance <= 100)
            {
                // Range is close enough
                if (absXDistance < getFiringDistance() / 2)
                {
                    DirectionCompositeInterface directionCompositeInterface = 
                            (DirectionCompositeInterface) this.getOwnerLayerInterface();

                    // logUtil.put(" xDistance: " + xDistance + " Direction: " + directionCompositeInterface.getDirection(), this, commonStrings.PROCESS);
                    // is direction the same as enemy
                    // if on the left side and pointing left
                    if (xDistance < 0
                            && directionCompositeInterface.getDirection() == directionFactory.RIGHT)
                    {
                        direction = directionFactory.RIGHT;
                        // if on the right side and pointing right
                    }
                    else if (xDistance > 0
                            && directionCompositeInterface.getDirection() == directionFactory.LEFT)
                    {
                        direction = directionFactory.LEFT;
                    }
                }                
            }
        }
        return direction;
    }

    public void disable()
    {
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
        /*
         * if (eventObject instanceof TrackingEvent) {
         * this.onMovement((TrackingEvent) eventObject); } else {
         * this.onTerrainEvent((TerrainEvent) eventObject); }
         */
    }

    public void onMovement(TrackingEvent trackingEvent)
    {
        this.trackingList.clear();
        this.trackingList.add(trackingEvent);
    }

    public int getFiringDistance()
    {
        return this.firingDistance;
    }

    public void setFiringDistance(int firingDistance)
    {
        this.firingDistance = firingDistance;
    }
}