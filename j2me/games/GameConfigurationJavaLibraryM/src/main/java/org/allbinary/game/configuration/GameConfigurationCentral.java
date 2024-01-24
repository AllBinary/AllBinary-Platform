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

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.persistance.GameConfigurationPersistanceSingleton;
import org.allbinary.game.configuration.persistance.KeyValuePersistance;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class GameConfigurationCentral
{
    private static final GameConfigurationCentral SINGLETON = new GameConfigurationCentral();

    public static GameConfigurationCentral getInstance()
    {
        return SINGLETON;
    }

    //private static String[] ORIENTATIONS = {"Vertical", "Up", "Down", "Left", "Right"};

    public final GameConfiguration SCALE;
    public final GameConfiguration ORIENTATION;
    public final GameConfiguration SENSOR_UPDATE_RATE;
    public final GameConfiguration VIBRATION;
    public final GameConfiguration CHALLENGE_LEVEL;
    public final GameConfiguration COLLIDE_DAMAGE;
    public final GameConfiguration DURABILITY_CHALLENGE_LEVEL;
    public final GameConfiguration SPEED_CHALLENGE_LEVEL;
    public final GameConfiguration ATTACK_CHALLENGE_LEVEL;

    public final GameConfiguration CONTROL_LEVEL;

    public final GameConfiguration PLAYER_INPUT_WAIT;
    
    // More is slower - tie too frame processing like a ship firing rate or
    // enemy activity level
    public final GameConfiguration SPEED;

    // public final GameConfiguration GRAPHICS = new
    // GameConfiguration(SmallIntegerSingletonFactory.getInstance(0));
    public final GameConfiguration SOUND_VOLUME;
    public final GameConfiguration MAX_GAME_OBJECTS;
    public final GameConfiguration MAX_LAYERS;

    // TWB - Ignore this shit
    // Good Total Frames 4 * n
    // Good Angle Increments AngleIncrementInfo.TOTAL_ANGLE/(4*n)
    // 90, 45, 30.33, 22.5, 18, 15, 12.86, 11.xx, 10
    // 1, 2, 3, 4, 5, 6, 7, 8, 9
    //Frames for 360 degrees
    
    //4 16 6 6 20 30 90 180
    //where x and y are whole numbers
    //90 / x = y * 4 = total increments
    //public final int[] FIDELITY = {4,8,24,30,36,40,60,90,180,360};
    //angle increment                    30 12 10 9  6  4  2   1  

    public int gameControlFidelity = 36;
    
    private GameConfigurationCentral()
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        SCALE = new GameConfiguration("Scale",
                smallIntegerSingletonFactory.getInstance(2),
                smallIntegerSingletonFactory.getInstance(2),
                smallIntegerSingletonFactory.getInstance(3));
        ORIENTATION = new GameConfiguration("Orientation",
                smallIntegerSingletonFactory.getInstance(0),
                smallIntegerSingletonFactory.getInstance(0),
                smallIntegerSingletonFactory.getInstance(8));
        SENSOR_UPDATE_RATE = new GameConfiguration("Sensor Update Rate",
                smallIntegerSingletonFactory.getInstance(0),
                smallIntegerSingletonFactory.getInstance(0),
                smallIntegerSingletonFactory.getInstance(3));
        VIBRATION = new GameConfiguration("Vibration",
                smallIntegerSingletonFactory.getInstance(0),
                smallIntegerSingletonFactory.getInstance(0),
                smallIntegerSingletonFactory.getInstance(3));
        CHALLENGE_LEVEL = new GameConfiguration("Global Challenge Level",
                smallIntegerSingletonFactory.getInstance(3),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));
        COLLIDE_DAMAGE = new GameConfiguration(
                "Collide Damage Challenge Level",
                smallIntegerSingletonFactory.getInstance(3),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));
        DURABILITY_CHALLENGE_LEVEL = new GameConfiguration(
                "Enemy Durability Challenge Level",
                smallIntegerSingletonFactory.getInstance(3),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));
        SPEED_CHALLENGE_LEVEL = new GameConfiguration(
                "Enemy Speed Challenge Level",
                smallIntegerSingletonFactory.getInstance(3),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));
        ATTACK_CHALLENGE_LEVEL = new GameConfiguration(
                "Enemy Attack Challenge Level",
                smallIntegerSingletonFactory.getInstance(3),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));

        CONTROL_LEVEL = new GameConfiguration("Control Fidelity",
                smallIntegerSingletonFactory.getInstance(5),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));

        PLAYER_INPUT_WAIT = new GameConfiguration("Input Wait",
                smallIntegerSingletonFactory.getInstance(5),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));

        // More is slower - tie too frame processing like a ship firing rate or
        // enemy activity level
        SPEED = new GameConfiguration("Game Speed (Device Specific)",
                smallIntegerSingletonFactory.getInstance(5),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(10));

        // public GameConfiguration GRAPHICS = new
        // GameConfiguration(smallIntegerSingletonFactory.getInstance(0));
        SOUND_VOLUME = new GameConfiguration("Sound Volume",
                smallIntegerSingletonFactory.getInstance(6),
                smallIntegerSingletonFactory.getInstance(0),
                smallIntegerSingletonFactory.getInstance(10));
        MAX_GAME_OBJECTS = new GameConfiguration("Max Game Objects",
                smallIntegerSingletonFactory.getInstance(20),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(50));
        MAX_LAYERS = new GameConfiguration("Max Layers",
                smallIntegerSingletonFactory.getInstance(20),
                smallIntegerSingletonFactory.getInstance(1),
                smallIntegerSingletonFactory.getInstance(50));
    }
    
    public void load()
    {
        try
        {
            final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
            
            KeyValuePersistance keyValuePersistance = 
                GameConfigurationPersistanceSingleton.getInstance();

            keyValuePersistance.clear();
            keyValuePersistance.loadAll();

            BasicArrayList list = keyValuePersistance.getIds();

            if (list.size() > 0)
            {
                Hashtable hashtable = keyValuePersistance.get(0);
                String value = (String) hashtable.get(this.SCALE.getName());

                this.SCALE.setValue(smallIntegerSingletonFactory.getInstance(Integer.valueOf(value).intValue()));
            } else
            {
                LogUtil.put(LogFactory.getInstance("No Game Configuration To Load", this, "load"));
            }
        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "load", e));
        }

        // Load Scale Value
        // Keep Loaded Value until next static initialization
        // valueToKeep = this.SOME_GAME_CONFIGURATION.getValue();
    }

    public int getGameControlFidelity()
    {
        //int fidelity = GameConfigurationCentral.getInstance().CONTROL_LEVEL.getValue().intValue();
        //9, 18, 36, 72, 144, 288, 360
        //40, 60
        return this.gameControlFidelity; //(FIDELITY[fidelity + 1]);
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        CommonSeps commonStrings = CommonSeps.getInstance();

        // stringBuffer.append(this.INPUT_WAIT.getName());
        // stringBuffer.append(commonStrings.EQUALS);
        // stringBuffer.append(this.INPUT_WAIT.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SCALE.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.SCALE.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.ORIENTATION.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.ORIENTATION.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SENSOR_UPDATE_RATE.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.SENSOR_UPDATE_RATE.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.VIBRATION.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.VIBRATION.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.CHALLENGE_LEVEL.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.COLLIDE_DAMAGE.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.COLLIDE_DAMAGE.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.DURABILITY_CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.DURABILITY_CHALLENGE_LEVEL.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SPEED_CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.SPEED_CHALLENGE_LEVEL.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.ATTACK_CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.ATTACK_CHALLENGE_LEVEL.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.CONTROL_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.CONTROL_LEVEL.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SPEED.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.SPEED.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.MAX_GAME_OBJECTS.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.MAX_GAME_OBJECTS.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.MAX_LAYERS.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.append(this.MAX_LAYERS.getValue());

        // stringBuffer.append(commonStrings.COMMA_SEP);

        // stringBuffer.append(this.SOUND_VOLUME.getName());
        // stringBuffer.append(commonStrings.EQUALS);
        // stringBuffer.append(this.SOUND_VOLUME.getValue());

        return stringBuffer.toString();
    }
}
