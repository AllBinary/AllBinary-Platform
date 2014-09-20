package org.allbinary.game.input.analog;

import org.allbinary.util.BasicArrayList;

public class AnalogLocationInputFactory {

	private static final AnalogLocationInputFactory instance = new AnalogLocationInputFactory();

	public static AnalogLocationInputFactory getInstance() {
		return instance;
	}
	
	private final BasicArrayList list = new BasicArrayList();
	
	private AnalogLocationInputFactory()
	{
		
	}
	
	public void addPlayer(int playerInputId)
	{
		AnalogLocationInput analogLocationInput = null;
		
		if(playerInputId < list.size())
		{
			analogLocationInput = (AnalogLocationInput) list.get(playerInputId);
		}
		
		if(analogLocationInput == null)
		{
			analogLocationInput = new AnalogLocationInput(playerInputId);
			list.add(analogLocationInput);
		}

	}
	
	public AnalogLocationInput getInstance(int playerInputId)
	{
		return (AnalogLocationInput) list.get(playerInputId);
    }

	public BasicArrayList getList() {
		return list;
	}

}
