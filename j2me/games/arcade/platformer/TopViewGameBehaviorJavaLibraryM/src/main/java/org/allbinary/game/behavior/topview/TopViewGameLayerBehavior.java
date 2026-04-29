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

/**
 *
 * @author User
 */
public class TopViewGameLayerBehavior extends GameLayerBehavior {
    //protected final LogUtil logUtil = LogUtil.getInstance();


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
        //this.logUtil.putF(commonStrings.START, this, "land");
        velocityProperties.getVelocityYBasicDecimalP().setint(0);
        this.landReset();
        // Takes a long time - unknown
        // jumpPlayer.stop();
    }
    
    public void landReset() {
        this.gravityActionIndex = 0;
        this.isFallingWithoutJumpAttempt = false;
        this.isJumpAction = true;
        this.isJumpOver = false;
    }

    public void up(final VelocityProperties velocityProperties, final BasicAccelerationProperties acceleration, final InitialJumpBehavior jumpBehavior, final int accelerationMultiplier) {
        if (!this.isJumpOver) {

            if(this.gravityActionIndex < this.maxGravityActionIndex) {
                final int acceleration2 = -acceleration.getForward() * accelerationMultiplier;
                //this.logUtil.putF("Jump: " + velocityProperties.getVelocityYBasicDecimalP().getUnscaled(), this, commonStrings.UP);
                //this.logUtil.putF("Acceleration: " + acceleration2, this, commonStrings.UP);
                velocityProperties.getVelocityYBasicDecimalP().addint(acceleration2);
                //this.logUtil.putF("Jumping: " + velocityProperties.getVelocityYBasicDecimalP().getUnscaled(), this, commonStrings.UP);
                velocityProperties.limitXYToForwardAndReverseMaxVelocity();

                // this.getVelocityProperties().addVelocity(this.acceleration.getForward(), angle);
                this.gravityActionIndex++;
            } else {
                //this.logUtil.putF("Jump peaked", this, commonStrings.UP);
            }
        } else {
            //this.logUtil.putF("Jump over", this, commonStrings.UP);
        }

        if (this.isJumpAction) {
            jumpBehavior.process();

            // jumpPlayer.start();
            this.isJumpAction = false;
            // TWB - Was this supposed to be remarked
            // this.specialAnimationInterfaceArray[LEGS_ANIMATION].setFrame(JUMP_LEGS_FRAME);
        }
    }
    
    public void inputFrames(final VelocityProperties velocityProperties) {
        if (this.gravityActionIndex > 0 && velocityProperties.getVelocityYBasicDecimalP().getUnscaled() > 0) {
            //this.logUtil.putF("Falling from jump now", this, "inputFrames");
            this.isJumpOver = true;
        }
    }
    
}
