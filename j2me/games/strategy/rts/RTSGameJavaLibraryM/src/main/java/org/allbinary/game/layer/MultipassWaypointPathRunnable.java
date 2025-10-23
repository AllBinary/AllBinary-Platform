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
    private BasicArrayList list = basicArrayListUtil.getImmutableInstance();
    private Object pathFindingInfo = NullUtil.getInstance().NULL_OBJECT;
    
    private final Runnable FIRST_RUNNABLE = new ARunnable() {

        @Override
        public void run() {
            try {
                pathFindingLayer.getWaypointRunnableLogHelper().start(pathFindingLayer);

                reset2();
                
                final GeographicMapCellPosition geographicMapCellPosition = 
                    pathFindingLayer.getCurrentGeographicMapCellPosition();

                if (geographicMapCellPosition == null) {
                    throw new Exception("Should never be running here");
                }

                pathFindingInfo = targetPathFindingLayer.getWaypointBehavior().getWaypoint().getPathFindingInfo(geographicMapCellPosition);
                final PathFindingInfo localPathFindingInfo = (PathFindingInfo) pathFindingInfo;
                
//                logUtil.put("first set: " + pathFindingInfo, this, "getPathsList");
                                
                list = targetPathFindingLayer.getWaypointBehavior().getWaypoint().getPathsList(geographicMapCellPosition, localPathFindingInfo, multipassState);
                
                if(list != basicArrayListUtil.getImmutableInstance()) {
                    END_RUNNABLE.run();
                } else {
                    currentPassRunnable = SECOND_RUNNABLE;
                }
            } catch (Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                //logUtil.put(commonStrings.EXCEPTION, this, "run", e);
                setRunning(false);
                finish();
            }
        }
        
    };

//    private boolean first = true;
    
    private final Runnable SECOND_RUNNABLE = new ARunnable() {
        
        @Override
        public void run() {
            try {

//                if(first) {
//                    first = false;
//                    logUtil.put("second set: " + pathFindingInfo, this, "getPathsList");
//                }
                
                final GeographicMapCellPosition geographicMapCellPosition = 
                    pathFindingLayer.getCurrentGeographicMapCellPosition();

                final PathFindingInfo localPathFindingInfo = (PathFindingInfo) pathFindingInfo;
                list = targetPathFindingLayer.getWaypointBehavior().getWaypoint().getPathsList(geographicMapCellPosition, localPathFindingInfo, multipassState);
                
                if(list != basicArrayListUtil.getImmutableInstance()) {
                    END_RUNNABLE.run();
                }
                
            } catch (Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                //logUtil.put(commonStrings.EXCEPTION, this, "run", e);
                setRunning(false);
                finish();
            }
        }
        
    };

    private final Runnable END_RUNNABLE = new ARunnable() {
            
        @Override
        public void run() {
            try {
                final WaypointBehaviorBase waypointBehavior = pathFindingLayer.getWaypointBehavior();
                
//                logUtil.put("end: " + pathFindingInfo, this, "getPathsList");

                waypointBehavior.setWaypointPathsList(list);

                pathFindingLayer.getWaypointRunnableLogHelper().end(pathFindingLayer);
                
            } catch (Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                //logUtil.put(commonStrings.EXCEPTION, this, "run", e);
                setRunning(false);
            }

            finish();
        }
        
    };

    private final Runnable ALREADY_ENDED_RUNNABLE = new ARunnable() {
            
        @Override
        public void run() {
            throw new RuntimeException();
        }
        
    };
    
    private Runnable currentPassRunnable = FIRST_RUNNABLE;
    
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
        multipassState.step = 0;
        multipassState.iteration = 0;
        multipassState.iteration2 = 0;
        pathFindingInfo = NullUtil.getInstance().NULL_OBJECT;
//        first = true;
    }
    
    private void finish() {
        this.reset2();
        currentPassRunnable = ALREADY_ENDED_RUNNABLE;
        done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
    
    @Override
    public void reset() {
        this.reset2();
        this.currentPassRunnable = FIRST_RUNNABLE;
        this.done = false;
    }
}
