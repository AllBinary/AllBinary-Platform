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
import org.allbinary.game.input.GameInput;
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class BasePatrolAI extends BasicAI
{
    public final static Integer MAX_DISTANCE = 
        SmallIntegerSingletonFactory.getInstance().getInstance(2);

    public final static Integer DIRECTION_ARRAY = 
        SmallIntegerSingletonFactory.getInstance().getInstance(3);
    
    public final static Integer FIRING_DISTANCE = 
        SmallIntegerSingletonFactory.getInstance().getInstance(4);

    public static Integer SPEED = 
        SmallIntegerSingletonFactory.getInstance().getInstance(5);

    protected Integer maxDistance;
    protected int currentDistance;

    //private Integer[] directionArray;
    protected int xTotalDistance;
    //private int yTotalDistance;
    protected int lastKeyDirection = Canvas.RIGHT;
    protected int keyDirection = Canvas.RIGHT;
    
    public BasePatrolAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
            throws Exception
    {
        super(ownerLayerInterface, gameInput);

        this.maxDistance = (Integer) hashtable.get(BasePatrolAI.MAX_DISTANCE);

        this.currentDistance = maxDistance.intValue();

        //this.directionArray = (Integer[]) hashtable
          //      .get(PacePatrolAI.DIRECTION_ARRAY);
        // Integer firingDistanceInteger = (Integer)
        // hashtable.get(PacePatrolAI.FIRING_DISTANCE);
        // this.firingDistance = firingDistanceInteger.intValue();
    }

    protected void update()
    {
        this.updateTotalDistance();

        this.changeDirectionIfReachedPacingAreaMax();
    }

    private void updateTotalDistance()
    {
        VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface = (VelocityInterfaceCompositeInterface) this
                .getOwnerLayerInterface();

        BasicVelocityProperties velocityProperties =
                velocityInterfaceCompositeInterface.getVelocityProperties();

        xTotalDistance += velocityProperties.getVelocityXBasicDecimalP().getScaled();

        //yTotalDistance += velocityProperties.getVelocityYBasicDecimalP()
          //      .getScaled();
    }

    // Limit pacing to a maximum distance
    private void changeDirectionIfReachedPacingAreaMax()
    {
        final MathUtil mathUtil = MathUtil.getInstance();
        int totalDistance = mathUtil.abs(xTotalDistance);

        if (totalDistance > this.currentDistance)
        {
            this.nextDirection();
            //this.isFollowLimitedByTerrain = false;
        }
    }

    protected void nextDirection()
    {
        // for(int index = 0; index < directionArray.length; index++)
        if (this.lastKeyDirection == Canvas.LEFT)
        {
            this.lastKeyDirection = keyDirection = Canvas.RIGHT;
            xTotalDistance = 0;
            //yTotalDistance = 0;
        }
        else if (this.lastKeyDirection == Canvas.RIGHT)
        {
            this.lastKeyDirection = keyDirection = Canvas.LEFT;
            xTotalDistance = 0;
            //yTotalDistance = 0;
        }
    }

    public void disable()
    {
    }
}