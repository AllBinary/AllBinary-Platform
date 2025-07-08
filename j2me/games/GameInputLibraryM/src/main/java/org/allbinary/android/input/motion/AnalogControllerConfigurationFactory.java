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

import org.allbinary.android.input.motion.event.AnalogControllerConfigurationEvent;
import org.allbinary.android.input.motion.event.AnalogControllerConfigurationEventHandler;

import org.allbinary.logic.math.ScaleFactorFactory;

public class AnalogControllerConfigurationFactory {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private static final AnalogControllerConfigurationFactory instance = new AnalogControllerConfigurationFactory();

    public static AnalogControllerConfigurationFactory getInstance() {
        return instance;
    }

    public final int SCALE_FACTOR = ScaleFactorFactory.getInstance().DEFAULT_SCALE_FACTOR;
    public final int SCALE_VALUE = ScaleFactorFactory.getInstance().DEFAULT_SCALE_VALUE;

    private boolean available;

    private final AnalogControllerConfigurationEvent analogControllerConfigurationEvent
            = new AnalogControllerConfigurationEvent(this);

    private AnalogControllerConfigurationFactory() {

    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available)
            throws Exception {
            //logUtil.put(commonStrings.START_LABEL + available, this, "setAvailable");

        this.available = available;

        AnalogControllerConfigurationEventHandler.getInstance().fireEvent(analogControllerConfigurationEvent);
    }
}
