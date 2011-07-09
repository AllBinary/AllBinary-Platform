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

import javax.microedition.lcdui.Item;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class GameConfigurationUtil
{
    private static final GameConfigurationUtil instance = new GameConfigurationUtil();
    
    public static GameConfigurationUtil getInstance()
    {
        return instance;
    }

    public void change(
            GameOptionsForm gameOptionsForm, 
            GameConfigurationGauge gauge) 
    throws Exception
    {
        this.update(gauge);

        GameConfigurationSingleton gameConfigurationSingleton = 
            GameConfigurationSingleton.getInstance();
        
        GameConfiguration gameConfiguration = gameConfigurationSingleton
                .getInstance(gauge.getLabel());
        
        this.updateChallange(gameOptionsForm, gameConfiguration);
    }

    private final String GAUGE_UPDATE = "Gauge Update: ";
    private final String FROM = " from: ";
    private final String TO = " to: ";
    
    public void update(GameConfigurationGauge gauge) throws Exception
    {
        GameConfigurationSingleton gameConfigurationSingleton = 
            GameConfigurationSingleton.getInstance();

        GameConfiguration gameConfiguration = gameConfigurationSingleton
                .getInstance(gauge.getLabel());

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        Integer value = smallIntegerSingletonFactory.getInstance(
                gauge.getValue() + gameConfiguration.getMinValue().intValue());

        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append(GAUGE_UPDATE);
        stringBuffer.append(gameConfiguration.getName());
        stringBuffer.append(FROM);
        stringBuffer.append(gameConfiguration.getValue());
        stringBuffer.append(TO);
        stringBuffer.append(value);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, CommonStrings.getInstance().UPDATE));
        
        gameConfiguration.setValue(value);
    }

    public void setDefault(GameConfigurationGauge gauge)
            throws Exception
    {
        GameConfigurationSingleton gameConfigurationSingleton = 
            GameConfigurationSingleton.getInstance();
        
        GameConfiguration gameConfiguration = gameConfigurationSingleton
                .getInstance(gauge.getLabel());

        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append("Gauge Default: ");
        stringBuffer.append(gameConfiguration.getName());
        stringBuffer.append(TO);
        stringBuffer.append(gameConfiguration.getDefaultValue());
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "setDefault"));

        gauge.setValue(gameConfiguration.getDefaultValue().intValue() - gameConfiguration.getMinValue().intValue());
        gameConfiguration.setValue(gameConfiguration.getDefaultValue());

    }

    public void updateChallange(
            GameOptionsForm gameOptionsForm, 
            GameConfiguration gameConfiguration)
            throws Exception
    {
        GameConfigurationSingleton gameConfigurationSingleton = 
            GameConfigurationSingleton.getInstance();
        
        GameConfigurationCentral gameConfigurationCentral = 
            GameConfigurationCentral.getInstance();
        
        if (gameConfiguration == gameConfigurationCentral.CHALLENGE_LEVEL)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "updateChallange"));

            gameConfigurationCentral.COLLIDE_DAMAGE.setValue(gameConfiguration
                    .getValue());
            gameConfigurationCentral.ATTACK_CHALLENGE_LEVEL.setValue(gameConfiguration
                    .getValue());
            gameConfigurationCentral.DURABILITY_CHALLENGE_LEVEL
                    .setValue(gameConfiguration.getValue());
            gameConfigurationCentral.SPEED_CHALLENGE_LEVEL.setValue(gameConfiguration
                    .getValue());

            for (int index = 0; index < gameOptionsForm.size(); index++)
            {
                Item item = gameOptionsForm.get(index);
                if (item instanceof GameConfigurationGauge)
                {
                    GameConfigurationGauge gauge = (GameConfigurationGauge) item;
                    String name = item.getLabel();
                    
                    GameConfiguration nextGameConfiguration = gameConfigurationSingleton.getInstance(name);
                    
                    if (nextGameConfiguration == gameConfigurationCentral.COLLIDE_DAMAGE)
                    {
                        gauge.setValue(gameConfigurationCentral.COLLIDE_DAMAGE
                                .getValue().intValue() - gameConfiguration.getMinValue().intValue());
                    }
                    else if (nextGameConfiguration == gameConfigurationCentral.DURABILITY_CHALLENGE_LEVEL)
                    {
                        gauge.setValue(gameConfigurationCentral.DURABILITY_CHALLENGE_LEVEL
                                        .getValue().intValue() - gameConfiguration.getMinValue().intValue());
                    }
                    else if (nextGameConfiguration == gameConfigurationCentral.ATTACK_CHALLENGE_LEVEL)
                    {
                        gauge.setValue(gameConfigurationCentral.ATTACK_CHALLENGE_LEVEL
                                .getValue().intValue() - gameConfiguration.getMinValue().intValue());
                    }
                    else if (nextGameConfiguration == gameConfigurationCentral.SPEED_CHALLENGE_LEVEL)
                    {
                        gauge.setValue(gameConfigurationCentral.SPEED_CHALLENGE_LEVEL
                                .getValue().intValue() - gameConfiguration.getMinValue().intValue());
                    }
                }
            }

        }
    }

    private int COMPETITION_VALUE = 1;

    public void updateCompetitionValue()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "updateCompetitionValue"));

        GameConfigurationCentral gameConfigurationCentral = 
            GameConfigurationCentral.getInstance();
        
        COMPETITION_VALUE = gameConfigurationCentral.COLLIDE_DAMAGE.getValue().intValue()
        + gameConfigurationCentral.ATTACK_CHALLENGE_LEVEL.getValue().intValue()
        + gameConfigurationCentral.DURABILITY_CHALLENGE_LEVEL.getValue().intValue() + 
        gameConfigurationCentral.SPEED_CHALLENGE_LEVEL.getValue().intValue();
        
        COMPETITION_VALUE = (COMPETITION_VALUE >> 2);

    }

    public int getCompetitionValue()
    {
        return COMPETITION_VALUE;
    }
}
