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
package org.allbinary.game.behavior.topview;

import org.allbinary.game.layer.behavior.GameLayerBehavior;
import org.allbinary.game.physics.acceleration.BasicAccelerationProperties;
import org.allbinary.game.physics.velocity.VelocityProperties;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class TopViewGameLayerBehavior extends GameLayerBehavior {

    //private final CommonStrings commonStrings = CommonStrings.getInstance();

    public final int maxGravityActionIndex;
    
    public boolean isJumpAction = true;
    public boolean isJumpOver = false;
    public boolean isFallingWithoutJumpAttempt = false;
    // Action associated with jumping or falling
    public int gravityActionIndex = 0;
        
    public TopViewGameLayerBehavior(final int maxGravityActionIndex) {
        this.maxGravityActionIndex = maxGravityActionIndex;
    }
    
    public void gravity() {
        if (this.gravityActionIndex == 0) {
            this.gravityActionIndex++;
            this.isFallingWithoutJumpAttempt = true;
        }
    }

    public void land(final VelocityProperties velocityProperties) {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "land"));
        velocityProperties.getVelocityYBasicDecimal().set(0);
        this.land();
        // Takes a long time - unknown
        // jumpPlayer.stop();
    }
    
    public void land() {
        this.gravityActionIndex = 0;
        this.isFallingWithoutJumpAttempt = false;
        this.isJumpAction = true;
        this.isJumpOver = false;
    }

    public void up(final VelocityProperties velocityProperties, final BasicAccelerationProperties acceleration, final InitialJumpBehavior jumpBehavior, final int accelerationMultiplier) {
        if (!isJumpOver) {

            if(gravityActionIndex < maxGravityActionIndex) {
                final int acceleration2 = -acceleration.getForward() * accelerationMultiplier;
                //LogUtil.put(LogFactory.getInstance("Jump: " + velocityProperties.getVelocityYBasicDecimal().getUnscaled(), this, commonStrings.UP));
                //LogUtil.put(LogFactory.getInstance("Acceleration: " + acceleration2, this, commonStrings.UP));
                velocityProperties.getVelocityYBasicDecimal().add(acceleration2);
                //LogUtil.put(LogFactory.getInstance("Jumping: " + velocityProperties.getVelocityYBasicDecimal().getUnscaled(), this, commonStrings.UP));
                velocityProperties.limitXYToForwardAndReverseMaxVelocity();

                // this.getVelocityProperties().addVelocity(this.acceleration.getForward(), angle);
                gravityActionIndex++;
            } else {
                //LogUtil.put(LogFactory.getInstance("Jump peaked", this, commonStrings.UP));
            }
        } else {
            //LogUtil.put(LogFactory.getInstance("Jump over", this, commonStrings.UP));
        }

        if (isJumpAction) {
            jumpBehavior.process();

            // jumpPlayer.start();
            isJumpAction = false;
            // TWB - Was this supposed to be remarked
            // this.specialAnimationInterfaceArray[LEGS_ANIMATION].setFrame(JUMP_LEGS_FRAME);
        }
    }
    
    public void inputFrames(final VelocityProperties velocityProperties) {
        if (this.gravityActionIndex > 0 && velocityProperties.getVelocityYBasicDecimal().getUnscaled() > 0) {
            //LogUtil.put(LogFactory.getInstance("Falling from jump now", this, "inputFrames"));
            this.isJumpOver = true;
        }
    }
    
}
