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
package org.allbinary.android.input.motion;

import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.time.SimpleTimeDelay;
import org.allbinary.time.TimeDelayHelper;

public class AnalogControllerHelper {

    private final int reduceTimeFactor;
    
    public AnalogControllerHelper(int reduceTimeFactor) {

        this.reduceTimeFactor = reduceTimeFactor;
    }

    private final int SCALE_VALUE = AnalogControllerConfigurationFactory.getInstance().SCALE_VALUE;

    private final SimpleTimeDelay rightSimpleTimeDelay = new SimpleTimeDelay(0);
    private final TimeDelayHelper rightTimeElapsedHelper = new TimeDelayHelper(rightSimpleTimeDelay);
    private final SimpleTimeDelay leftSimpleTimeDelay = new SimpleTimeDelay(0);
    private final TimeDelayHelper leftTimeElapsedHelper = new TimeDelayHelper(leftSimpleTimeDelay);
    private final SimpleTimeDelay downSimpleTimeDelay = new SimpleTimeDelay(0);
    private final TimeDelayHelper downTimeElapsedHelper = new TimeDelayHelper(downSimpleTimeDelay);
    private final SimpleTimeDelay upSimpleTimeDelay = new SimpleTimeDelay(0);
    private final TimeDelayHelper upTimeElapsedHelper = new TimeDelayHelper(upSimpleTimeDelay);

    public void right(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.rightSimpleTimeDelay.delay = ((SCALE_VALUE - xAnalogValue) >> this.reduceTimeFactor);        

        if (this.rightTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.right();
        }
    }

    public void left(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.leftSimpleTimeDelay.delay = ((SCALE_VALUE + xAnalogValue) >> this.reduceTimeFactor);

        if (this.leftTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.left();
        }
    }

    public void up(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.upSimpleTimeDelay.delay = ((SCALE_VALUE - xAnalogValue) >> this.reduceTimeFactor);

        if (this.upTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.up();
        }
    }

    public void down(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.downSimpleTimeDelay.delay = ((SCALE_VALUE + xAnalogValue) >> this.reduceTimeFactor);

        if (this.downTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.down();
        }
    }

}
