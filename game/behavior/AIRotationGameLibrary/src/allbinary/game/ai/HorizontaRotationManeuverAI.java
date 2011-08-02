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
package allbinary.game.ai;

import javax.microedition.lcdui.Canvas;

import org.allbinary.util.CircularIndexUtil;

import allbinary.ai.ArtificialIntelligenceTransitionInterface;
import allbinary.animation.IndexedAnimation;
import allbinary.animation.RotationAnimationInterface;
import allbinary.animation.RotationAnimationInterfaceCompositeInterface;
import allbinary.game.input.GameInput;
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.game.physics.velocity.VelocityInterface;
import allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.math.AngleIncrementInfo;

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
        
        RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) this
                .getOwnerLayerInterface();
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
        this.rotationAnimationInterface.setFrame(angleIncrementInfo.RIGHT_FRAME.intValue());
    }

    public void transition()
    {
        AngleIncrementInfo angleIncrementInfo = 
            ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();
        this.rotationAnimationInterface.setFrame(angleIncrementInfo.RIGHT_FRAME.intValue());
        
        //TWB - Hack to deal with enemies flying away when off screen
        this.getOwnerLayerInterface().setPosition(this.getOwnerLayerInterface().getX(), 1);
    }

    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {

        // int keyDirection = -1;
        //int x = this.getOwnerLayerInterface().getX();

        // LogUtil.put(LogFactory.getInstance("Angle: " + currentAngle + " X: "
        // + x + " Y: " +
        // y, this, "processAI"));
        AngleIncrementInfo angleIncrementInfo = 
            ((RotationAnimationInterface) this.rotationAnimationInterface).getAngleInfo().getAngleIncrementInfo();

        int frame = this.rotationAnimationInterface.getFrame();

        if (this.getOwnerLayerInterface().getX()
                - this.getOwnerLayerInterface().getWidth() <= 0
                && frame == angleIncrementInfo.LEFT_FRAME.intValue())
        {
            this.reverse();
            this.drop();
        }

        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        if (this.getOwnerLayerInterface().getX2()
                + this.getOwnerLayerInterface().getWidth() > displayInfo.getLastWidth()
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
            this.rotationAnimationInterface.setFrame(
                    angleIncrementInfo.RIGHT_FRAME.intValue());

            this.baseVelocityInterface.zero();
        }
        else if (frame == angleIncrementInfo.RIGHT_FRAME.intValue())
        {
            this.rotationAnimationInterface
                    .setFrame(angleIncrementInfo.LEFT_FRAME.intValue());
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
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        int y = this.getOwnerLayerInterface().getY();
        if (this.getOwnerLayerInterface().getY2()
                + this.getOwnerLayerInterface().getHeight() > displayInfo
                .getLastHeight())
        {
            y = 0;
        }
        else
        {
            y += this.getOwnerLayerInterface().getHeight() + 1;
        }
        this.getOwnerLayerInterface().setPosition(
                this.getOwnerLayerInterface().getX(), y);
    }

    public int getId()
    {
        return ArtificialIntelligenceTransitionInterface.ID;
    }    
}