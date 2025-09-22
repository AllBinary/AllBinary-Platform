/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.waypoint;

import javax.microedition.lcdui.Graphics;

import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.game.health.Health;
import org.allbinary.game.identification.Group;
import org.allbinary.game.input.form.NullRTSFormInputFactory;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.multiplayer.layer.RemoteInfo;
import org.allbinary.media.audio.AttackSound;

/**
 *
 * @author user
 */
public class WaypointLayer extends AdvancedRTSGameLayer
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected WaypointLayer(
        final RemoteInfo remoteInfo,
        final AdvancedRTSGameLayer parentLayer,
        final Group[] groupInterface,
        final String rootName,
        final String name,
        final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
        final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,
        final Rectangle rectangle, 
        final int x, final int y)
        throws Exception
    {
        super(
            remoteInfo,
            parentLayer,
            groupInterface,
            rootName,
            name,
            new Health(1000),
            NullRTSFormInputFactory.getInstance(),
            animationInterfaceFactoryInterface,
            animationInterfaceFactoryInterface,
            animationInterfaceFactoryInterface,
            animationInterfaceFactoryInterface,
            animationInterfaceFactoryInterface,
            proceduralAnimationInterfaceFactoryInterface,
            rectangle,
            x, y);

        //logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        
        this.setCollidableInferface(new CollidableWaypointBehavior(this, true));
        
        this.getWaypointBehavior().setWaypoint(new Waypoint(this, AttackSound.getInstance()));
        
        this.setAnimationInterface(this.indexedButShouldBeRotationAnimationInterface);
    }

    /*
    public WaypointLayer()
        throws Exception
    {
        super();
    }
    */

    public void construct(RTSPlayerLayerInterface rtsPlayerLayerInterface)
    {    
        this.percentComplete = 100;
      
      //Bots don't show waypoints
        this.initVisibility(rtsPlayerLayerInterface);
    }

    public void paint(Graphics graphics)
    {
        if(this.isVisible())
        {
            super.paint(graphics);
        }
    }
    
    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
    {
        try
        {
            this.indexedButShouldBeRotationAnimationInterface.nextFrame();
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "processTick", e);
        }
    }

    public void damage(int damage, int damageType)
    {
    }

    public int getDamage(int damageType) throws Exception
    {
        return 0;
    }

    public void setDestroyed(boolean destroyed)
        throws Exception
    {
        super.setDestroyed(destroyed);

        if (this.isDestroyed())
        {
            WaypointCellPositionHistory.getInstance().remove(this);
        }
    }
   
    public int getType()
    {
        return getStaticType();
    }

    public static int getStaticType()
    {
        return 4;
    }
    
    public boolean isSelfUpgradeable()
    {
        return false;
    }    
}