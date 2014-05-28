package org.allbinary.android.input.motion;

import allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import allbinary.time.TimeDelayHelper;

public class AnalogControllerHelper {

    private final int reduceTimeFactor;
    
    public AnalogControllerHelper(int reduceTimeFactor) {

        this.reduceTimeFactor = reduceTimeFactor;
    }

    private final int SCALE_VALUE = AnalogControllerConfigurationFactory.getInstance().SCALE_VALUE;

    private final TimeDelayHelper rightTimeElapsedHelper = new TimeDelayHelper(0);
    private final TimeDelayHelper leftTimeElapsedHelper = new TimeDelayHelper(0);
    private final TimeDelayHelper downTimeElapsedHelper = new TimeDelayHelper(0);
    private final TimeDelayHelper upTimeElapsedHelper = new TimeDelayHelper(0);

    public void right(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.rightTimeElapsedHelper.setDelay((SCALE_VALUE - xAnalogValue) >> this.reduceTimeFactor);

        if (this.rightTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.right();
        }
    }

    public void left(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.leftTimeElapsedHelper.setDelay((SCALE_VALUE + xAnalogValue) >> this.reduceTimeFactor);

        if (this.leftTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.left();
        }
    }

    public void up(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.upTimeElapsedHelper.setDelay((SCALE_VALUE - xAnalogValue) >> this.reduceTimeFactor);

        if (this.upTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.up();
        }
    }

    public void down(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
            throws Exception {
        this.downTimeElapsedHelper.setDelay((SCALE_VALUE + xAnalogValue) >> this.reduceTimeFactor);

        if (this.downTimeElapsedHelper.isTime()) {
            collidableDestroyableDamageableLayer.down();
        }
    }

}
