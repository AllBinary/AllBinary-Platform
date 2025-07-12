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

import org.allbinary.game.ai.BasicAI;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.layer.identification.GroupLayerManagerListener;
import org.allbinary.game.layer.weapon.WeaponLayer;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.util.BasicArrayList;

public class FlockingAI extends BasicAI
{
    private final int allowedDistance;
    
    public FlockingAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
       throws Exception
    {
        super(ownerLayerInterface, gameInput);
        
        Integer allowedDistance = (Integer) hashtable.get(BasePatrolAI.MAX_DISTANCE);
        this.allowedDistance = allowedDistance.intValue();
    }
    
    public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        int maxDistance = 0;
        AllBinaryLayer farAllbinaryLayer = null;
        
        GroupLayerManagerListener groupLayerManagerListener = 
            GroupLayerManagerListener.getInstance();
        
        BasicArrayList list = groupLayerManagerListener.getList(BasicGroupFactory.getInstance().ENEMY);
        
        final int size = list.size();
        for(int index = 0; index < size; index++)
        {
            AllBinaryLayer allBinaryLayer = (AllBinaryLayer) list.get(index);
            
            if(allBinaryLayer.getType() != WeaponLayer.getStaticType())
            {
                int distance = this.getXYDistance(allBinaryLayer);
                
                if(distance > maxDistance)
                {
                    maxDistance = distance;
                    farAllbinaryLayer = allBinaryLayer;
                    
                    if(distance > this.allowedDistance)
                    {
                        break;
                    }
                }
            }
        }
        
        if(maxDistance > this.allowedDistance)
        {
            // Go towards flock
            if (farAllbinaryLayer != null)
            {
                AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
                
                if (farAllbinaryLayer.getX() < ownerLayerInterface.getX())
                {
                    this.processAI(Canvas.LEFT);
                } 
                else
                    if (farAllbinaryLayer.getX() > ownerLayerInterface.getX())
                    {
                        this.processAI(Canvas.RIGHT);
                    }
                    else
                        if (farAllbinaryLayer.getY() > ownerLayerInterface.getY())
                        {
                            this.processAI(Canvas.UP);
                        }
                        else
                            if (farAllbinaryLayer.getY() < ownerLayerInterface.getY())
                            {
                                this.processAI(Canvas.DOWN);
                            }
            }
        }
    }
    
    private int getXYDistance(AllBinaryLayer allBinaryLayer)
    {
        AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
        
        int xTotalDistance = (allBinaryLayer.getX() + allBinaryLayer.getHalfWidth()) - 
                (ownerLayerInterface.getX() + ownerLayerInterface.getHalfWidth());

        int yTotalDistance = (allBinaryLayer.getY() + allBinaryLayer.getHalfHeight()) - 
        (ownerLayerInterface.getY() + ownerLayerInterface.getHalfHeight());
        
        final MathUtil mathUtil = MathUtil.getInstance();
        int totalDistance = mathUtil.abs(xTotalDistance) + mathUtil.abs(yTotalDistance);
        
        return totalDistance;
    }
}
