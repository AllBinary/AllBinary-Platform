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

import org.allbinary.game.configuration.persistance.GameConfigurationPersistanceSingleton;
import org.allbinary.game.configuration.persistance.KeyValuePersistance;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class GameConfigurationCentral
{

    private static Object SINGLETON = NullUtil.getInstance().NULL_OBJECT;

    public static GameConfigurationCentral getInstance()
    {
        if(GameConfigurationCentral.SINGLETON == NullUtil.getInstance().NULL_OBJECT) {
            GameConfigurationCentral.SINGLETON = new GameConfigurationCentral();
        }
        
        return (GameConfigurationCentral) GameConfigurationCentral.SINGLETON;
    }

    protected final LogUtil logUtil = LogUtil.getInstance();
    
    //private static String[] ORIENTATIONS = {"Vertical", CommonPhoneStrings.getInstance().UP, CommonPhoneStrings.getInstance().DOWN, CommonPhoneStrings.getInstance().LEFT, CommonPhoneStrings.getInstance().RIGHT};

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

    // TWB - Ignore this but do not delete
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

    private int gameControlFidelity = 36;
    
    private GameConfigurationCentral()
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        this.SCALE = new GameConfiguration("Scale",
                smallIntegerSingletonFactory.getAt(2),
                smallIntegerSingletonFactory.getAt(2),
                smallIntegerSingletonFactory.getAt(3));
        this.ORIENTATION = new GameConfiguration("Orientation",
                smallIntegerSingletonFactory.getAt(0),
                smallIntegerSingletonFactory.getAt(0),
                smallIntegerSingletonFactory.getAt(8));
        this.SENSOR_UPDATE_RATE = new GameConfiguration("Sensor Update Rate",
                smallIntegerSingletonFactory.getAt(0),
                smallIntegerSingletonFactory.getAt(0),
                smallIntegerSingletonFactory.getAt(3));
        this.VIBRATION = new GameConfiguration("Vibration",
                smallIntegerSingletonFactory.getAt(0),
                smallIntegerSingletonFactory.getAt(0),
                smallIntegerSingletonFactory.getAt(3));
        this.CHALLENGE_LEVEL = new GameConfiguration("Global Challenge Level",
                smallIntegerSingletonFactory.getAt(3),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));
        this.COLLIDE_DAMAGE = new GameConfiguration(
                "Collide Damage Challenge Level",
                smallIntegerSingletonFactory.getAt(3),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));
        this.DURABILITY_CHALLENGE_LEVEL = new GameConfiguration(
                "Enemy Durability Challenge Level",
                smallIntegerSingletonFactory.getAt(3),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));
        this.SPEED_CHALLENGE_LEVEL = new GameConfiguration(
                "Enemy Speed Challenge Level",
                smallIntegerSingletonFactory.getAt(3),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));
        this.ATTACK_CHALLENGE_LEVEL = new GameConfiguration(
                "Enemy Attack Challenge Level",
                smallIntegerSingletonFactory.getAt(3),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));

        this.CONTROL_LEVEL = new GameConfiguration("Control Fidelity",
                smallIntegerSingletonFactory.getAt(5),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));

        this.PLAYER_INPUT_WAIT = new GameConfiguration("Input Wait",
                smallIntegerSingletonFactory.getAt(5),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));

        // More is slower - tie too frame processing like a ship firing rate or
        // enemy activity level
        this.SPEED = new GameConfiguration("Game Speed (Device Specific)",
                smallIntegerSingletonFactory.getAt(5),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(10));

        // public GameConfiguration GRAPHICS = new
        // GameConfiguration(smallIntegerSingletonFactory.getInstance(0));
        this.SOUND_VOLUME = new GameConfiguration("Sound Volume",
                smallIntegerSingletonFactory.getAt(6),
                smallIntegerSingletonFactory.getAt(0),
                smallIntegerSingletonFactory.getAt(10));
        this.MAX_GAME_OBJECTS = new GameConfiguration("Max Game Objects",
                smallIntegerSingletonFactory.getAt(20),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(50));
        this.MAX_LAYERS = new GameConfiguration("Max Layers",
                smallIntegerSingletonFactory.getAt(20),
                smallIntegerSingletonFactory.getAt(1),
                smallIntegerSingletonFactory.getAt(50));
    }
    
    public void load(final AbeClientInformationInterface abeClientInformation)
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        try
        {
            final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
            
            final KeyValuePersistance keyValuePersistance = GameConfigurationPersistanceSingleton.getInstance();

            keyValuePersistance.clear();
            keyValuePersistance.loadAll(abeClientInformation);

            final BasicArrayList list = keyValuePersistance.getIds();

            if (list.size() > 0)
            {
                final Hashtable hashtable = keyValuePersistance.get(0);
                final Object object = (Object) this.SCALE.getName();
                final String value = (String) hashtable.get(object);

                this.SCALE.setValue(smallIntegerSingletonFactory.getAt(Integer.valueOf(value).intValue()));
            } else
            {
                this.logUtil.putF("No Game Configuration To Load", this, commonStrings.LOAD);
            }
        } catch (Exception e)
        {
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.LOAD, e);
        }

        // Load Scale Value
        // Keep Loaded Value until next static initialization
        // valueToKeep = this.SOME_GAME_CONFIGURATION.getValue();
    }

    public void setGameControlFidelity(final int gameControlFidelity) {
        this.gameControlFidelity = gameControlFidelity;
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
        final StringMaker stringBuffer = new StringMaker();

        final CommonSeps commonStrings = CommonSeps.getInstance();

        // stringBuffer.append(this.INPUT_WAIT.getName());
        // stringBuffer.append(commonStrings.EQUALS);
        // stringBuffer.append(this.INPUT_WAIT.getValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SCALE.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.SCALE.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.ORIENTATION.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.ORIENTATION.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SENSOR_UPDATE_RATE.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.SENSOR_UPDATE_RATE.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.VIBRATION.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.VIBRATION.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.CHALLENGE_LEVEL.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.COLLIDE_DAMAGE.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.COLLIDE_DAMAGE.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.DURABILITY_CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.DURABILITY_CHALLENGE_LEVEL.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SPEED_CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.SPEED_CHALLENGE_LEVEL.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.ATTACK_CHALLENGE_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.ATTACK_CHALLENGE_LEVEL.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.CONTROL_LEVEL.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.CONTROL_LEVEL.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.SPEED.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.SPEED.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.MAX_GAME_OBJECTS.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.MAX_GAME_OBJECTS.getValue().intValue());

        stringBuffer.append(commonStrings.COMMA_SEP);

        stringBuffer.append(this.MAX_LAYERS.getName());
        stringBuffer.append(commonStrings.EQUALS);
        stringBuffer.appendint(this.MAX_LAYERS.getValue().intValue());

        // stringBuffer.append(commonStrings.COMMA_SEP);

        // stringBuffer.append(this.SOUND_VOLUME.getName());
        // stringBuffer.append(commonStrings.EQUALS);
        // stringBuffer.append(this.SOUND_VOLUME.getValue());

        return stringBuffer.toString();
    }
}
