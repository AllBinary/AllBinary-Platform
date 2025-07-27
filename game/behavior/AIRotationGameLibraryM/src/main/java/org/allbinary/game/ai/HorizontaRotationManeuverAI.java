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

import org.allbinary.ai.ArtificialIntelligenceTransitionInterface;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.RotationAnimationInterface;
import org.allbinary.animation.RotationAnimationInterfaceCompositeInterface;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.game.physics.velocity.VelocityInterface;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.math.AngleIncrementInfo;
import org.allbinary.util.CircularIndexUtil;

public class HorizontaRotationManeuverAI extends BasicAI
implements ArtificialIntelligenceTransitionInterface
{
    private final CircularIndexUtil circularIndexUtil = CircularIndexUtil.getInstance(10, Integer.MAX_VALUE);
    
    private int currentSpeedDivisor = 5;

    private final IndexedAnimation rotationAnimationInterface;
    private final BasicVelocityProperties baseVelocityInterface;
    private final VelocityInterface velocityInterface;

    public HorizontaRotationManeuverAI(AllBinaryLayer ownerLayerInterface,
            GameInput gameInput)
    {
        super(ownerLayerInterface, gameInput);
        
        final RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) this.getOwnerLayerInterface();
        this.rotationAnimationInterface = 
            rotationAnimationInterfaceCompositeInterface.getRotationAnimationInterface();

        VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface = 
            (VelocityInterfaceCompositeInterface) this.getOwnerLayerInterface();

        this.baseVelocityInterface =
            velocityInterfaceCompositeInterface.getVelocityProperties();

        this.velocityInterface = (VelocityInterface) baseVelocityInterface; 

        //this.transition();
        AngleIncrementInfo angleIncrementInfo = 
            ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();
        rotationAnimationInterfaceCompositeInterface.setFrame(angleIncrementInfo.RIGHT_FRAME.intValue());
    }

    public void transition()
    {
        AngleIncrementInfo angleIncrementInfo = 
            ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();
        final RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) this.getOwnerLayerInterface();
        rotationAnimationInterfaceCompositeInterface.setFrame(angleIncrementInfo.RIGHT_FRAME.intValue());
        
        //TWB - Hack to deal with enemies flying away when off screen
        AllBinaryLayer allbinaryLayer = this.getOwnerLayerInterface();
        
        allbinaryLayer.setPosition(
                allbinaryLayer.getXP(), 1, allbinaryLayer.getZP());
    }

    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {

        // int keyDirection = -1;
        //int x = this.getOwnerLayerInterface().getX();

        //logUtil.put("Angle: " + currentAngle + " X: " + x + " Y: " + y, this, commonStrings.PROCESS);
        AngleIncrementInfo angleIncrementInfo = 
            ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();

        int frame = this.rotationAnimationInterface.getFrame();

        AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
        
        if (ownerLayerInterface.getXP()
                - ownerLayerInterface.getWidth() <= 0
                && frame == angleIncrementInfo.LEFT_FRAME.intValue())
        {
            this.reverse();
            this.drop();
        }

        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        if (ownerLayerInterface.getX2()
                + ownerLayerInterface.getWidth() > displayInfo.getLastWidth()
                && frame == angleIncrementInfo.RIGHT_FRAME.intValue())
        {
            this.reverse();
            this.accelerate();
            this.drop();
        }

        int index = circularIndexUtil.getIndex();
        if (index % this.currentSpeedDivisor == 0 && index % 2 == 0)
        {
            super.processAI(Canvas.UP);

            velocityInterface.limitMaxXYVelocity(
                    this.velocityInterface.getMaxForwardVelocity() / this.currentSpeedDivisor);
        }

        this.circularIndexUtil.next();

        if (frame == angleIncrementInfo.LEFT_FRAME.intValue())
        {
            super.processAI(Canvas.KEY_NUM0);
        }
        else if (frame == angleIncrementInfo.RIGHT_FRAME.intValue())
        {
            super.processAI(Canvas.KEY_POUND);
        }
    }

    protected void reverse()
    {
        AngleIncrementInfo angleIncrementInfo = 
            ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();

        int frame = this.rotationAnimationInterface.getFrame();

        if (frame == angleIncrementInfo.LEFT_FRAME.intValue())
        {
            final RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) this.getOwnerLayerInterface();
            rotationAnimationInterfaceCompositeInterface.setFrame(angleIncrementInfo.RIGHT_FRAME.intValue());

            this.baseVelocityInterface.zero();
        }
        else if (frame == angleIncrementInfo.RIGHT_FRAME.intValue())
        {
            final RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) this.getOwnerLayerInterface();
            rotationAnimationInterfaceCompositeInterface.setFrame(angleIncrementInfo.LEFT_FRAME.intValue());
            this.baseVelocityInterface.zero();
        }
    }

    private void accelerate()
    {
        if (currentSpeedDivisor > 1)
        {
            currentSpeedDivisor--;
        }
    }

    private void drop()
    {
        AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
        
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        int y = ownerLayerInterface.getYP();
        if (ownerLayerInterface.getY2() + ownerLayerInterface.getHeight() > displayInfo.getLastHeight())
        {
            y = 0;
        }
        else
        {
            y += ownerLayerInterface.getHeight() + 1;
        }
        ownerLayerInterface.setPosition(
                ownerLayerInterface.getXP(), y, ownerLayerInterface.getZP());
    }

    public int getId()
    {
        return ArtificialIntelligenceTransitionInterface.ID;
    }    
}