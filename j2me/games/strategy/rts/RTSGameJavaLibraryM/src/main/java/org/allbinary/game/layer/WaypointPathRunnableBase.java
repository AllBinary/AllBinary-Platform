/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.math.LayerDistanceUtil;
import org.allbinary.thread.PriorityRunnable;
import org.allbinary.thread.RunnableInterface;

/**
 *
 * @author User
 */
public class WaypointPathRunnableBase implements RunnableInterface, PriorityRunnable {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private final LayerDistanceUtil layerDistanceUtil = LayerDistanceUtil.getInstance();
    
    protected PathFindingLayerInterface pathFindingLayer = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;
    protected PathFindingLayerInterface targetPathFindingLayer = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;

    protected int priorityP = Integer.MAX_VALUE;
    
    protected boolean runningP;

    @Override
    public boolean isRunning()
    {
        return this.runningP;
    }

    @Override
    public void setRunning(final boolean isRunning)
    {
        this.runningP = isRunning;
    }

    @Override
    public void setThread(final Thread thread)throws Exception
    {

    }

    //private final String SET_PRIORITY = "Set priority: ";
    //private final String SET_TARGET = "setTarget";
    
    /**
     * @param waypointLayer the waypointLayer to set
     */
    public void setTargetLayer(final PathFindingLayerInterface waypointLayer)
    {
        this.targetPathFindingLayer = waypointLayer;
        
        this.setPriority();
        
    }

    public void setPriority() {
        
        final AllBinaryLayer pathFindingLayer = (AllBinaryLayer) this.pathFindingLayer;
        final AllBinaryLayer targetLayer = (AllBinaryLayer) this.targetPathFindingLayer;
        
        if(targetLayer != null) {
            
            final int distance = layerDistanceUtil.getDistance(targetLayer, pathFindingLayer);
            
            final int distanceCategory = distance / 70;
            this.priorityP = distanceCategory;

            //logUtil.put(new StringMaker().append(SET_PRIORITY).append(this.priority).toString(), this, SET_TARGET);
        }
    }
    
    public PathFindingLayerInterface getTargetLayer()
    {
        return targetPathFindingLayer;
    }

    /**
     * @param unitLayer the unitLayer to set
     */
    public void setUnitLayer(PathFindingLayerInterface unitLayer)
    {
        this.pathFindingLayer = unitLayer;
    }
    
    @Override
    public int getPriority() {
        return this.priorityP;
    }
 
    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void run()
    {
    }

    @Override
    public void reset()
    {
    }
    
}
