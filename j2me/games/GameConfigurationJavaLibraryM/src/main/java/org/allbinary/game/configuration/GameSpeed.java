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
package org.allbinary.game.configuration;

public class GameSpeed
{
    private static final GameSpeed instance = new GameSpeed();

    public final int getSpeed()
    {
        GameConfiguration speedGameConfiguration = GameConfigurationCentral.getInstance().SPEED;

        return (speedGameConfiguration.getMaxValue().intValue() - 
                speedGameConfiguration.getValue().intValue() + 1);
    }

    public final int getDelay()
    {
        GameConfiguration speedGameConfiguration = GameConfigurationCentral.getInstance().SPEED;

        return 19 * (speedGameConfiguration.getMaxValue().intValue() - 
                speedGameConfiguration.getValue().intValue());
    }

    public static GameSpeed getInstance()
    {
        return instance;
    }
}
