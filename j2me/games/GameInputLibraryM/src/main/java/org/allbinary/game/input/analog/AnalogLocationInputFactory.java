package org.allbinary.game.input.analog;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class AnalogLocationInputFactory {

	private static final AnalogLocationInputFactory instance = new AnalogLocationInputFactory();

	public static AnalogLocationInputFactory getInstance() {
		return instance;
	}
	
	private final BasicArrayList list = new BasicArrayListD();
	
	private AnalogLocationInputFactory()
	{
		
	}
	
	public void addPlayer(int playerInputId)
	{
		AnalogLocationInput analogLocationInput = AnalogLocationInput.NULL_ANALOG_LOCATION_INPUT;
		
		if(playerInputId < this.list.size())
		{
			analogLocationInput = (AnalogLocationInput) this.list.get(playerInputId);
		}
		
		if(analogLocationInput == AnalogLocationInput.NULL_ANALOG_LOCATION_INPUT)
		{
			analogLocationInput = new AnalogLocationInput(playerInputId);
			this.list.add(analogLocationInput);
		}

	}
	
	public AnalogLocationInput getInstance(int playerInputId)
	{
		return (AnalogLocationInput) this.list.get(playerInputId);
    }

	public BasicArrayList getList() {
		return list;
	}

}
