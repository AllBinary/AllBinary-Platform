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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.pathfinding.MultipassState;
import org.allbinary.media.graphics.geography.pathfinding.PathFindingInfo;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class MultipassWaypointPathRunnable extends WaypointPathRunnableBase
{
    private boolean done = false;
    private BasicArrayList list;
    
    private PathFindingInfo pathFindingInfo;
    private final MultipassState multipassState = new MultipassState();
    
    private final Runnable FIRST_RUNNABLE = new Runnable() {
            
        public void run() {
            try {
                pathFindingLayer.getWaypointRunnableLogHelper().start(pathFindingLayer);

                reset2();
                
                final GeographicMapCellPosition geographicMapCellPosition = 
                    pathFindingLayer.getCurrentGeographicMapCellPosition();

                if (geographicMapCellPosition == null) {
                    throw new Exception("Should never be running here");
                }

                pathFindingInfo = targetLayer.getWaypointBehavior().getWaypoint().getPathFindingInfo(geographicMapCellPosition);
                
//                LogUtil.put(LogFactory.getInstance("first set: " + pathFindingInfo, this, "getPathsList"));
                                
                list = targetLayer.getWaypointBehavior().getWaypoint().getPathsList(geographicMapCellPosition, pathFindingInfo, multipassState);
                
                if(list != null) {
                    END_RUNNABLE.run();
                } else {
                    currentPassRunnable = SECOND_RUNNABLE;
                }
            } catch (Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
                //LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "run", e));
                setRunning(false);
                finish();
            }
        }
        
    };

//    private boolean first = true;
    
    private final Runnable SECOND_RUNNABLE = new Runnable() {
        
        public void run() {
            try {

//                if(first) {
//                    first = false;
//                    LogUtil.put(LogFactory.getInstance("second set: " + pathFindingInfo, this, "getPathsList"));
//                }
                
                final GeographicMapCellPosition geographicMapCellPosition = 
                    pathFindingLayer.getCurrentGeographicMapCellPosition();

                list = targetLayer.getWaypointBehavior().getWaypoint().getPathsList(geographicMapCellPosition, pathFindingInfo, multipassState);
                
                if(list != null) {
                    END_RUNNABLE.run();
                }
                
            } catch (Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
                //LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "run", e));
                setRunning(false);
                finish();
            }
        }
        
    };

    private final Runnable END_RUNNABLE = new Runnable() {
            
        public void run() {
            try {
                final WaypointBehaviorBase waypointBehavior = pathFindingLayer.getWaypointBehavior();
                
//                LogUtil.put(LogFactory.getInstance("end: " + pathFindingInfo, this, "getPathsList"));

                waypointBehavior.setWaypointPathsList(list);

                pathFindingLayer.getWaypointRunnableLogHelper().end(pathFindingLayer);
                
            } catch (Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
                //LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "run", e));
                setRunning(false);
            }

            finish();
        }
        
    };

    private final Runnable ALREADY_ENDED_RUNNABLE = new Runnable() {
            
        public void run() {
            throw new RuntimeException();
        }
        
    };
    
    private Runnable currentPassRunnable = FIRST_RUNNABLE;
    
    public MultipassWaypointPathRunnable()
    {
    }

    public void setRunning(boolean isRunning)
    {        
        this.running = isRunning;
        
        if(this.running) {
            this.reset();
            this.done = false;
        }
        
    }

    public void run()
    {
        try
        {
            this.currentPassRunnable.run();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
            //LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "run", e));
            this.setRunning(false);
        }
    }

    private void reset2() {
        multipassState.step = 0;
        multipassState.iteration = 0;
        multipassState.iteration2 = 0;
        pathFindingInfo = null;
//        first = true;
    }
    
    private void finish() {
        this.reset2();
        currentPassRunnable = ALREADY_ENDED_RUNNABLE;
        done = true;
    }

    public boolean isDone() {
        return done;
    }
    
    public void reset() {
        this.reset2();
        this.currentPassRunnable = FIRST_RUNNABLE;
        this.done = false;
    }
}
