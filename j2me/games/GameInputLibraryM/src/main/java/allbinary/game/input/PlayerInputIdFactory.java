package allbinary.game.input;

import allbinary.game.input.analog.AnalogLocationInputFactory;

public class PlayerInputIdFactory {

	private static final PlayerInputIdFactory instance = new PlayerInputIdFactory();

	public final int MAX_NUMBER_OF_PLAYERS = 6;
	private final int[] playerIdToDeviceId = new int[MAX_NUMBER_OF_PLAYERS];
	
	private int currentNumberOfPlayers = 0;

	private PlayerInputIdFactory()
	{
		
	}

	public static PlayerInputIdFactory getInstance() {
		return instance;
	}
	
	public int getPlayerForDevice(int deviceId)
	{
		int index;
		for(index = currentNumberOfPlayers - 1; index >= 0; index--)
		{
			if(this.playerIdToDeviceId[index] == deviceId)
			{
				return index;
			}
		}

		//Add New Player for device
		this.playerIdToDeviceId[currentNumberOfPlayers] = deviceId;
		index = currentNumberOfPlayers;
		
		AnalogLocationInputFactory.getInstance().addPlayer(currentNumberOfPlayers);
		
		currentNumberOfPlayers++;

		//At some point add Ouya and other special mapping here and return it instead of the internal mapping
		//int playerNum = OuyaController.getPlayerNumByDeviceId(deviceId);
		
		return index;
	}

}
