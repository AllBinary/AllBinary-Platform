package org.allbinary.android.input.motion;

import org.allbinary.android.input.motion.event.AnalogControllerConfigurationEvent;
import org.allbinary.android.input.motion.event.AnalogControllerConfigurationEventHandler;

import allbinary.logic.math.ScaleFactorFactory;

public class AnalogControllerConfigurationFactory {

	private static final AnalogControllerConfigurationFactory instance = new AnalogControllerConfigurationFactory();

	public static AnalogControllerConfigurationFactory getInstance() {
		return instance;
	}
	
	public final int SCALE = ScaleFactorFactory.getInstance().DEFAULT_SCALE_VALUE;
	
	private boolean available;
	
	private final AnalogControllerConfigurationEvent analogControllerConfigurationEvent = 
			new AnalogControllerConfigurationEvent(this);
	
	private AnalogControllerConfigurationFactory()
	{
		
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) 
	throws Exception
	{
		this.available = available;
		
		AnalogControllerConfigurationEventHandler.getInstance().fireEvent(analogControllerConfigurationEvent);
	}
}
