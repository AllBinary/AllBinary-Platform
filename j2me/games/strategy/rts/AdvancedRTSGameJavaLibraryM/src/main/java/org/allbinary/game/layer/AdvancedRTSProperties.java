/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.game.layer;

import org.allbinary.animation.transition.shake.NoShakeAnimationListener;
import org.allbinary.animation.transition.shake.ShakeAnimationListener;
import org.allbinary.animation.transition.shake.ShakeAnimationListenerFactory;
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.game.tick.TickableInterface;
import org.allbinary.media.AllBinaryNoVibration;
import org.allbinary.media.AllBinaryVibration;
import org.allbinary.media.AllBinaryVibrationME;

/**
 *
 * @author User
 */
public class AdvancedRTSProperties {
    
    public static AdvancedRTSProperties createSimulated() {
        return new AdvancedRTSProperties(NoShakeAnimationListener.NO_SHAKE_ANIMATION_LISTENER, AllBinaryNoVibration.NO_VIBRATION, 0, new WaypointBehaviorBase());
    }

    public static AdvancedRTSProperties create() {
        return new AdvancedRTSProperties(ShakeAnimationListenerFactory.getInstance(), AllBinaryVibration.getInstance(), GameConfigurationCentral.getInstance().VIBRATION.getValue().intValue() * 100, new WaypointBehaviorBase());
    }
    
    public final ShakeAnimationListener shakeListener;
    public final AllBinaryVibrationME vibration;
    public final int duration;
    public TickableInterface waypointBehaviorBase;
    
    public AdvancedRTSProperties(final ShakeAnimationListener shakeListener, final AllBinaryVibrationME vibration, final int duration, final TickableInterface waypointBehaviorBase) {
        this.shakeListener = shakeListener;
        this.vibration = vibration;
        this.duration = duration;
        this.waypointBehaviorBase = waypointBehaviorBase;
    }
    
}
