package org.allbinary.business.advertisement;

import org.allbinary.game.GameAdState;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class GameAdStateFactory
{
    private static final GameAdStateFactory instance = new GameAdStateFactory();

    public static GameAdStateFactory getInstance()
    {
        return instance;
    }

    private final GameAdState[] gameAdStateArray = {
            new GameAdState(new AppNameLicensingAdConfiguration())
            };

    private GameAdState gameAdState = gameAdStateArray[0];

    public GameAdState getCurrentInstance()
    {
        return this.gameAdState;
    }
    
    public GameAdState getInstance(final AbeClientInformationInterface abeClientInformation)
    throws Exception
    {
        //if(abeClientInformation == TestSoftwareInfo.getInstance())
        //{
            this.gameAdState = gameAdStateArray[0];
            
            return gameAdStateArray[0];
        //}
        //else
        //{
          //  throw new Exception("No Such Ad Configuration: " + abeClientInformation);
        //}
    }
    
    public boolean isEnabled()
    {
    	return false;
    }
}
