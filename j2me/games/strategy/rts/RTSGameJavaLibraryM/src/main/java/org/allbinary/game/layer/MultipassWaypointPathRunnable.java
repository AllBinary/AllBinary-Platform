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

import org.allbinary.logic.NullUtil;
import org.allbinary.thread.ARunnable;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.pathfinding.MultipassState;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author user
 */
public class MultipassWaypointPathRunnable extends WaypointPathRunnableBase
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    private final MultipassState multipassState = new MultipassState();
    
    private boolean done = false;
    private BasicArrayList list = this.basicArrayListUtil.getImmutableInstance();
    private Object pathFindingInfo = NullUtil.getInstance().NULL_OBJECT;
    
    private class FirstRunnable extends ARunnable {
        
        private final MultipassWaypointPathRunnable multipassWaypointPathRunnable;
        
        FirstRunnable(final MultipassWaypointPathRunnable multipassWaypointPathRunnable) {
            this.multipassWaypointPathRunnable = multipassWaypointPathRunnable;
        }
        
        @Override
        public void run() {
            this.multipassWaypointPathRunnable.processFirstRunnable();
        }
        
    };
    
    private final Runnable FIRST_RUNNABLE = new FirstRunnable(this);

//    private boolean first = true;
    
    private class SecondRunnable extends ARunnable {
        
        private final MultipassWaypointPathRunnable multipassWaypointPathRunnable;
        
        SecondRunnable(final MultipassWaypointPathRunnable multipassWaypointPathRunnable) {
            this.multipassWaypointPathRunnable = multipassWaypointPathRunnable;
        }
        
        @Override
        public void run() {
            this.multipassWaypointPathRunnable.processSecondRunnable();
        }
        
    };
    
    private final Runnable SECOND_RUNNABLE = new SecondRunnable(this);

    private class EndRunnable extends ARunnable {
        
        private final MultipassWaypointPathRunnable multipassWaypointPathRunnable;
        
        EndRunnable(final MultipassWaypointPathRunnable multipassWaypointPathRunnable) {
            this.multipassWaypointPathRunnable = multipassWaypointPathRunnable;
        }
        
        @Override
        public void run() {
            this.multipassWaypointPathRunnable.processEndRunnable();
        }            
        
    };
    
    private final Runnable END_RUNNABLE = new EndRunnable(this);

    private final Runnable ALREADY_ENDED_RUNNABLE = new ARunnable() {
            
        @Override
        public void run() {
            throw new RuntimeException();
        }
        
    };
    
    private Runnable currentPassRunnable = this.FIRST_RUNNABLE;
    
    public MultipassWaypointPathRunnable()
    {
    }
    
    @Override
    public void setRunning(boolean isRunning)
    {        
        this.runningP = isRunning;
        
        if(this.runningP) {
            this.reset();
            this.done = false;
        }
        
    }

    @Override
    public void run()
    {
        final LogUtil logUtil = LogUtil.getInstance();
        try
        {
            this.currentPassRunnable.run();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            //logUtil.put(commonStrings.EXCEPTION, this, "run", e);
            this.setRunning(false);
        }
    }

    private void reset2() {
        this.multipassState.step = 0;
        this.multipassState.iteration = 0;
        this.multipassState.iteration2 = 0;
        this.pathFindingInfo = NullUtil.getInstance().NULL_OBJECT;
//        first = true;
    }
    
    private void finish() {
        this.reset2();
        this.currentPassRunnable = this.ALREADY_ENDED_RUNNABLE;
        this.done = true;
    }

    @Override
    public boolean isDone() {
        return this.done;
    }
    
    @Override
    public void reset() {
        this.reset2();
        this.currentPassRunnable = this.FIRST_RUNNABLE;
        this.done = false;
    }
    

    private void processFirstRunnable() {
        try {

            this.pathFindingLayer.getWaypointRunnableLogHelper().start(this.pathFindingLayer);

            this.reset2();

            final GeographicMapCellPosition geographicMapCellPosition =
                this.pathFindingLayer.getCurrentGeographicMapCellPosition();

            if (geographicMapCellPosition == null) {
                throw new Exception("Should never be running here");
            }

            this.pathFindingInfo = this.targetPathFindingLayer.getWaypointBehavior().getWaypoint().getPathFindingInfo(geographicMapCellPosition);
            final PathFindingInfo localPathFindingInfo = (PathFindingInfo) this.pathFindingInfo;

//                logUtil.putF("first set: " + pathFindingInfo, this, "getPathsList");
            this.list = this.targetPathFindingLayer.getWaypointBehavior().getWaypoint().getPathsList(geographicMapCellPosition, localPathFindingInfo, this.multipassState);

            if (this.list != this.basicArrayListUtil.getImmutableInstance()) {
                this.END_RUNNABLE.run();
            } else {
                this.currentPassRunnable = this.SECOND_RUNNABLE;
            }

        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            //logUtil.put(commonStrings.EXCEPTION, this, "run", e);
            this.setRunning(false);
            this.finish();
        }
    }

    private void processSecondRunnable() {
        try {

//                if(first) {
//                    first = false;
//                    logUtil.putF("second set: " + pathFindingInfo, this, "getPathsList");
//                }
            final GeographicMapCellPosition geographicMapCellPosition =
                this.pathFindingLayer.getCurrentGeographicMapCellPosition();

            final PathFindingInfo localPathFindingInfo = (PathFindingInfo) this.pathFindingInfo;
            this.list = this.targetPathFindingLayer.getWaypointBehavior().getWaypoint().getPathsList(geographicMapCellPosition, localPathFindingInfo, this.multipassState);

            if (this.list != this.basicArrayListUtil.getImmutableInstance()) {
                this.END_RUNNABLE.run();
            }

        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            //logUtil.put(commonStrings.EXCEPTION, this, "run", e);
            this.setRunning(false);
            this.finish();
        }
    }

    private void processEndRunnable() {
        try {
            final WaypointBehaviorBase waypointBehavior = this.pathFindingLayer.getWaypointBehavior();

//                logUtil.putF("end: " + pathFindingInfo, this, "getPathsList");
            waypointBehavior.setWaypointPathsList(this.list);

            this.pathFindingLayer.getWaypointRunnableLogHelper().end(this.pathFindingLayer);

        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            //logUtil.put(commonStrings.EXCEPTION, this, "run", e);
            this.setRunning(false);
        }

        this.finish();
    }
    
}
