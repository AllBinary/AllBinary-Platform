package org.allbinary.business.advertisement;

import jsinterop.annotations.JsType;

import org.allbinary.game.GameAdState;
import org.allbinary.logic.system.SoftwareInformation;
import jsinterop.annotations.JsMethod;


@JsType
public class GameAdStateFactory implements GameAdStateFactoryInterface
{
    private static final GameAdStateFactory instance = new GameAdStateFactory();

    @JsMethod
    public static GameAdStateFactory getInstance()
    {
        return GameAdStateFactory.instance;
    }

    private final GameAdState[] gameAdStateArray = {
            new GameAdState(new AppNameLicensingAdConfiguration())
            };

    private GameAdState gameAdState = this.gameAdStateArray[0];

    @JsMethod
    public GameAdState getCurrentInstance()
    {
        return this.gameAdState;
    }
    
    @Override
    @JsMethod
    public GameAdStateBase getInstanceForApp(final SoftwareInformation softwareInformation)
    throws Exception
    {
        //if(softwareInformation == TestSoftwareInfo.getInstance())
        //{
            this.gameAdState = this.gameAdStateArray[0];
            
            return this.gameAdStateArray[0];
        //}
        //else
        //{
          //  throw new Exception("No Such Ad Configuration: " + abeClientInformation);
        //}
    }
    
    @JsMethod
    public boolean isEnabled()
    {
    	return false;
    }
}
