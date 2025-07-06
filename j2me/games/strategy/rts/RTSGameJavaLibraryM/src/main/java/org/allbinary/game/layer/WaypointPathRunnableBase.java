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

    private final LayerDistanceUtil layerDistanceUtil = LayerDistanceUtil.getInstance();
    
    protected PathFindingLayerInterface pathFindingLayer;
    protected PathFindingLayerInterface targetLayer;

    protected int priority = Integer.MAX_VALUE;
    
    protected boolean running;

    public boolean isRunning()
    {
        return this.running;
    }

    public void setRunning(final boolean isRunning)
    {
        this.running = isRunning;
    }

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
        this.targetLayer = waypointLayer;
        
        this.setPriority();
        
    }

    public void setPriority() {
        
        final AllBinaryLayer pathFindingLayer = (AllBinaryLayer) this.pathFindingLayer;
        final AllBinaryLayer targetLayer = (AllBinaryLayer) this.targetLayer;
        
        if(targetLayer != null) {
            
            final int distance = layerDistanceUtil.getDistance(targetLayer, pathFindingLayer);
            
            final int distanceCategory = distance / 70;
            this.priority = distanceCategory;

            //LogUtil.put(LogFactory.getInstance(new StringMaker().append(SET_PRIORITY).append(this.priority).toString(), this, SET_TARGET));
        }
    }
    
    public PathFindingLayerInterface getTargetLayer()
    {
        return targetLayer;
    }

    /**
     * @param unitLayer the unitLayer to set
     */
    public void setUnitLayer(PathFindingLayerInterface unitLayer)
    {
        this.pathFindingLayer = unitLayer;
    }
    
    public int getPriority() {
        return this.priority;
    }
 
    public boolean isDone() {
        return true;
    }

    public void run()
    {
    }

    public void reset()
    {
    }
    
}
