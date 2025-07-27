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
package org.allbinary.game.ai;

import javax.microedition.lcdui.Canvas;

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.direction.DirectionalCompositeInterface;
import org.allbinary.direction.DirectionalInterface;
import org.allbinary.game.combat.destroy.event.DestroyedEvent;
import org.allbinary.game.combat.destroy.event.DestroyedEventHandler;
import org.allbinary.game.combat.destroy.event.DestroyedEventListenerInterface;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.game.tracking.TrackingEventHandler;
import org.allbinary.game.tracking.TrackingEventListenerInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.BasicArrayList;

public class DiveAndDirectionalTrackingAI extends BasicAI implements
        TrackingEventListenerInterface, DestroyedEventListenerInterface
{
    private final MathUtil mathUtil = MathUtil.getInstance();
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(500);
    // private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);

    private final BasicArrayList list = new BasicArrayList();

    // Yes really an interface oh the horror
    private final DirectionalInterface directionalInterface;
    private final BasicVelocityProperties velocityInterface;

    private boolean initialDropped;
    private boolean dive;
    // private boolean targeting;
    private Direction directionOfTarget;
    private final int MIN_DISTANCE = 40;
    private AllBinaryLayer lastTrackingLayerInterface;
    // When this AI can't do anything
    private final ArtificialIntelligenceInterface artificialIntelligenceInterface;

    private final Visitor aiVistor;

    private Direction lastDirection = DirectionFactory.getInstance().NO_DIRECTION;

    private final DirectionFactory directionFactory = DirectionFactory.getInstance();

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();

    public DiveAndDirectionalTrackingAI(AllBinaryLayer ownerLayerInterface,
            ArtificialIntelligenceInterface artificialIntelligenceInterface,
            GameInput gameInput, Visitor visitor)
    {
        super(ownerLayerInterface, gameInput);

        this.aiVistor = visitor;

        this.artificialIntelligenceInterface = artificialIntelligenceInterface;

        DirectionalCompositeInterface directionalCompositeInterface = (DirectionalCompositeInterface) this
                .getOwnerLayerInterface();

        this.directionalInterface = directionalCompositeInterface
                .getDirectionalInterface();

        VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface = (VelocityInterfaceCompositeInterface) this
                .getOwnerLayerInterface();

        this.velocityInterface = velocityInterfaceCompositeInterface
                .getVelocityProperties();

        DestroyedEventHandler.getInstance().addListener(this);

        this.init();
    }

    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        // Should Re-Target
        if (this.isBeyondTarget())
        {
            this.init();
            this.velocityInterface.zero();
        }

        if (!dive)
        {
            this.target(allBinaryLayerManager);
        }
        else
        {
            this.dive();
            this.attack();
        }
        this.list.clear();
    }

    private void init()
    {
        this.dive = false;
        // this.targeting = true;
        this.directionOfTarget = DirectionFactory.getInstance().NOT_BORDERED_WITH;
        TrackingEventHandler.getInstance().addListener(this);
    }

    private void target(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        if (this.list.size() == 0)
        {
            return;
        }

        TrackingEvent lastTrackingEvent = (TrackingEvent) list.remove(0);

        this.lastTrackingLayerInterface = lastTrackingEvent.getLayerInterface();

        // logUtil.put("AI Owner: " +
        // this.getOwnerLayerInterface() + " Tracking: " +
        // this.lastTrackingLayerInterface, this, "target");

        AllBinaryLayer layerInterface = this.lastTrackingLayerInterface;

        AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();

        if (layerInterface.getGroupInterface() == ownerLayerInterface
                .getGroupInterface())
        {
            return;
        }

        int x = this.lastTrackingLayerInterface.getXP();
        int y = this.lastTrackingLayerInterface.getYP();

        // If yDistance is negative then going down
        // If yDistance is positive then going up
        int yDistance = ownerLayerInterface.getYP() - y
                - ownerLayerInterface.getHeight();
        // If xDistance is negative then going right
        // If xDistance is positive then going left
        int xDistance = ownerLayerInterface.getXP() - x
                - ownerLayerInterface.getWidth();

        final DirectionFactory directionFactory = DirectionFactory.getInstance();

        if (mathUtil.abs(yDistance) > mathUtil.abs(xDistance))
        {
            if (yDistance > MIN_DISTANCE)
            {
                this.directionOfTarget = directionFactory.UP;
            }
            else if (yDistance < -MIN_DISTANCE)
            {
                this.directionOfTarget = directionFactory.DOWN;
            }
        }
        else
        {
            if (xDistance > MIN_DISTANCE)
            {
                this.directionOfTarget = directionFactory.LEFT;
            }
            else if (xDistance < -MIN_DISTANCE)
            {
                this.directionOfTarget = directionFactory.RIGHT;
            }
        }

        if (!initialDropped
                && this.directionOfTarget != directionFactory.NOT_BORDERED_WITH)
        {
            this.drop();
        }

        // Don't do anything until drop occurs
        if (initialDropped)
        {
            if (this.directionOfTarget == directionFactory.UP
                    || this.directionOfTarget == directionFactory.DOWN)
            {
                this.verticalTargeting(x);
            }
            else if (this.directionOfTarget == directionFactory.LEFT
                    || this.directionOfTarget == directionFactory.RIGHT)
            {
                this.horizontalTargeting(y);
            }
            else
            {
                this.artificialIntelligenceInterface
                        .processAI(allBinaryLayerManager);
            }
        }
    }

    private void verticalTargeting(int x) throws Exception
    {
        AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();

        if (ownerLayerInterface.getXP() < x)
        {
            this.moveRight();
        }

        if (ownerLayerInterface.getXP() > x)
        {
            this.moveLeft();
        }

        if (ownerLayerInterface.getXP() > x - 3
                && ownerLayerInterface.getXP() < x + 3)
        {
            this.setDive();
        }
    }

    private void horizontalTargeting(int y) throws Exception
    {
        AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();

        if (ownerLayerInterface.getYP() < y)
        {
            this.moveDown();
        }

        if (ownerLayerInterface.getYP() > y)
        {
            this.moveUp();
        }

        if (ownerLayerInterface.getYP() > y - 3
                && ownerLayerInterface.getYP() < y + 3)
        {
            this.setDive();
        }
    }

    private void setDive()
    {
        this.dive = true;
        // this.targeting = false;
        this.velocityInterface.zero();
        TrackingEventHandler.getInstance().removeListener(this);
    }

    private void moveRight() throws Exception
    {
        this.setLastDirection(this.directionFactory.RIGHT);
        this.directionalInterface.setFrame(this.lastDirection);

        this.aiVistor.visit(this);

        // velocityInterface.setMaxXVelocity(1);
    }

    private void moveLeft() throws Exception
    {
        this.setLastDirection(this.directionFactory.LEFT);
        this.directionalInterface.setFrame(this.lastDirection);

        this.aiVistor.visit(this);
        // velocityInterface.setMaxXVelocity(-1);
    }

    private void moveDown() throws Exception
    {
        this.setLastDirection(this.directionFactory.DOWN);
        this.directionalInterface.setFrame(this.lastDirection);

        this.aiVistor.visit(this);
        // velocityInterface.setMaxYVelocity(1);
    }

    private void moveUp() throws Exception
    {
        this.setLastDirection(this.directionFactory.UP);
        this.directionalInterface.setFrame(this.lastDirection);

        this.aiVistor.visit(this);
        // velocityInterface.setMaxYVelocity(-1);
    }

    private void dive() throws Exception
    {
        this.directionalInterface.setFrame(this.directionOfTarget);

        this.aiVistor.visit(this);
    }

    private void attack() throws Exception
    {
        super.processAI(Canvas.KEY_NUM1);
    }

    private void drop() throws Exception
    {
        // Don't start dives so quickly
        if (this.timeDelayHelper.isTime(this.gameTickTimeDelayHelper.startTime))
        {
            initialDropped = true;

            this.aiVistor.visit(this);
            /*
             * int y = ownerLayerInterface.getYP(); if
             * (ownerLayerInterface.getY2() + ownerLayerInterface.getHeight() >
             * DisplayInfoSingleton.getInstance()getLastHeight()) { y = 0; }
             * else { y += ownerLayerInterface.getHeight() + 1; }
             * ownerLayerInterface. setPosition(ownerLayerInterface.getXP(), y);
             */
        }
    }

    private boolean isBeyondTarget()
    {
        if (this.directionOfTarget == directionFactory.DOWN)
        {
            if (this.getOwnerLayerInterface().getYP() > this.lastTrackingLayerInterface
                    .getYP() + MIN_DISTANCE)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (this.directionOfTarget == directionFactory.UP)
        {
            if (this.getOwnerLayerInterface().getYP() < this.lastTrackingLayerInterface
                    .getYP() + MIN_DISTANCE)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (this.directionOfTarget == directionFactory.RIGHT)
        {
            if (this.getOwnerLayerInterface().getXP() > this.lastTrackingLayerInterface
                    .getXP() + MIN_DISTANCE)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (this.directionOfTarget == directionFactory.LEFT)
        {
            if (this.getOwnerLayerInterface().getXP() < this.lastTrackingLayerInterface
                    .getXP() + MIN_DISTANCE)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);

        /*
         * if (eventObject instance of TrackingEvent) {
         * this.onMovement((TrackingEvent) eventObject); } else {
         * this.onDestroyed((DestroyedEvent) eventObject); }
         */
    }

    public void onMovement(TrackingEvent trackingEvent)
    {
        this.list.add(trackingEvent);
    }

    public void onDestroyed(DestroyedEvent destroyedEvent)
    {
        if (this.getOwnerLayerInterface() == destroyedEvent.getLayerInterface())
        {
            TrackingEventHandler.getInstance().removeListener(this);
            DestroyedEventHandler.getInstance().removeListener(this);
            // this.list.clear();
        }
    }

    /*
     * public String getDebug() { StringMaker stringBuffer = new
     * StringMaker(); stringBuffer.append(" Dp: ");
     * stringBuffer.append(this.initialDropped); stringBuffer.append(" Dv: ");
     * stringBuffer.append(this.dive); stringBuffer.append(" T: ");
     * stringBuffer.append(this.targeting); stringBuffer.append(" D: ");
     * stringBuffer.append(this.direction); return stringBuffer.toString(); }
     * public String toString() { StringMaker stringBuffer = new
     * StringMaker(); stringBuffer.append(" Dropped: ");
     * stringBuffer.append(this.initialDropped);
     * stringBuffer.append(" Diving: "); stringBuffer.append(this.dive);
     * stringBuffer.append(" Targeting: "); stringBuffer.append(this.targeting);
     * stringBuffer.append(" Direction: "); stringBuffer.append(this.direction);
     * return stringBuffer.toString(); }
     */

    private static final int[] directionToKeyMap =
    { Canvas.LEFT, Canvas.DOWN, Canvas.UP, Canvas.RIGHT, };

    public void setLastDirection(Direction lastDirection)
    {
        this.lastDirection = lastDirection;

        int value = this.getLastDirection().getValue();

        if (value < 4)
        {
            super.setLastKey(directionToKeyMap[value]);
        }
    }

    public void setLastKey(int lastKey)
    {
        super.setLastKey(lastKey);

        if (this.getLastKey() == Canvas.LEFT)
        {
            this.lastDirection = DirectionFactory.getInstance().LEFT;
        }
        else if (this.getLastKey() == Canvas.DOWN)
        {
            this.lastDirection = DirectionFactory.getInstance().DOWN;
        }
        else if (this.getLastKey() == Canvas.UP)
        {
            this.lastDirection = DirectionFactory.getInstance().UP;
        }
        else if (this.getLastKey() == Canvas.RIGHT)
        {
            this.lastDirection = DirectionFactory.getInstance().RIGHT;
        }
    }

    public Direction getLastDirection()
    {
        return lastDirection;
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(super.toString());
        stringBuffer.append(" LastDirection: ");
        stringBuffer.append(StringUtil.getInstance().toString(this.getLastDirection()));

        return stringBuffer.toString();
    }

}