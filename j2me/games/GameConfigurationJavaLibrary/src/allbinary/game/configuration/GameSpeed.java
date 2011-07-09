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
package allbinary.game.configuration;

public class GameSpeed
{
    private static final GameSpeed instance = new GameSpeed();
    
    public final int getSpeed()
    {
        return (GameConfigurationCentral.getInstance().SPEED.getMaxValue().intValue() - 
                GameConfigurationCentral.getInstance().SPEED.getValue().intValue() + 1);
    }
    
    public final int getDelay()
    {
        return 16 * (GameConfigurationCentral.getInstance().SPEED.getMaxValue().intValue() - 
                GameConfigurationCentral.getInstance().SPEED.getValue().intValue());
    }

    public static GameSpeed getInstance()
    {
        return instance;
    }
}
