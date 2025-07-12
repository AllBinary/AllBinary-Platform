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

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.GameFeatureChoiceGroups;
import org.allbinary.game.configuration.feature.GameFeatureFormUtil;
import org.allbinary.game.configuration.feature.GameFeatureItemCommandListener;
import org.allbinary.game.configuration.feature.GameFeatureItemStateListener;
import org.allbinary.game.configuration.feature.SensorFeatureFactory;
import org.allbinary.game.configuration.persistance.GameConfigurationPersistanceSingleton;
import org.allbinary.game.configuration.persistance.KeyValuePersistance;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.input.gyro.OrientationData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

public class GameOptionsForm extends CommandForm
    //CommandForm
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    /*
    public static GameOptionsForm getInstance()
    {
        return SINGLETON;
    }
    */

    protected GameOptionsForm(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
        throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.addConfiguration();

        GameFeatureFormUtil gameFeatureFormUtil = 
            GameFeatureFormUtil.getInstance();

        gameFeatureFormUtil.addChoiceGroup(this, 
                GameFeatureChoiceGroups.getExclusiveInstance().get(),
                Choice.EXCLUSIVE);

        gameFeatureFormUtil.addChoiceGroup(this, 
                GameFeatureChoiceGroups.getMultipleInstance().get(),
                Choice.MULTIPLE);

        this.initCommands(commandListener);

        this.setItemStateListener(new GameFeatureItemStateListener(this));
                //GameFeatureItemStateListener.getInstance());

        this.addTextFieldsIfSimulated();

    }

    public void close(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        super.close();
        this.save(abeClientInformation);
    }

    private void addTextFieldsIfSimulated()
    {
        String key = OrientationData.getInstance().ORIENTATION_SENSOR_INPUT;
        Hashtable hashtable = 
            GameFeatureChoiceGroups.getExclusiveInstance().get();
        if(hashtable != null)
        {
            BasicArrayList list = (BasicArrayList) hashtable.get(key);
            if(list != null && list.contains(
                    SensorFeatureFactory.getInstance().SIMULATED_ORIENTATION_SENSORS))
            {
                this.addTextFields();
            }
        }
    }
    
    private void addTextFields()
    {
        Hashtable hashtable = GameConfigurationTextInput.getHashtable();
        int size = hashtable.size();

        Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        for (int index = 0; index < size; index++)
        {
            GameConfigurationTextInput gameConfigurationTextInput = 
                (GameConfigurationTextInput) hashtable.get(objectArray[index]);

            TextField textField = new TextField(gameConfigurationTextInput
                    .getLabel(), gameConfigurationTextInput.getText(), 30,
                    TextField.ANY);

            this.append(textField);
        }
    }

    private void addConfiguration()
    {
        final String METHOD_NAME = "addConfiguration";
        final String NAME = "Name: ";
        
        BasicArrayList list = GameConfigurationSingleton.getInstance()
                .getOptionsBasicArrayList();

        Command GAUGE_CHANGE = MyCommandsFactory.getInstance().GAUGE_CHANGE;
        
        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            GameConfiguration gameConfiguration =
                (GameConfiguration) list.objectArray[index];

            logUtil.put(new StringMaker().append(NAME).append(gameConfiguration.toString()).toString(), this, METHOD_NAME);

            GameConfigurationGauge gauge = new GameConfigurationGauge(gameConfiguration);

            gauge.setDefaultCommand(GAUGE_CHANGE);
            gauge.setItemCommandListener(new GameFeatureItemCommandListener(this));
                    //.getInstance());
            // this.setCommandListener(l);

            this.append(gauge);
        }
    }

    public void initCommands(CommandListener cmdListener)
    {
        GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        this.removeAllCommands();
        this.addCommand(gameCommandsFactory.CLOSE_OPTIONS);
        this.addCommand(gameCommandsFactory.DEFAULT_OPTIONS);

        this.setCommandListener(cmdListener);
    }

    public void save(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        for (int index = 0; index < this.size(); index++)
        {
            Item item = this.get(index);
            if (item instanceof GameConfigurationGauge)
            {
                GameConfigurationUtil.getInstance().update(
                        (GameConfigurationGauge) item);
            }
            else if (item instanceof TextField)
            {
                GameConfigurationTextInput.update((TextField) item);
            }
        }

        GameConfigurationUtil.getInstance().updateCompetitionValue();

        Hashtable hashtable = new Hashtable();
        
        GameConfiguration SCALE = GameConfigurationCentral.getInstance().SCALE;
        
        hashtable.put(SCALE.getName(), SCALE.getValue().toString());

        KeyValuePersistance keyValuePersistance =
            GameConfigurationPersistanceSingleton.getInstance();
        
        keyValuePersistance.clear();
        keyValuePersistance.loadAll(abeClientInformation);

        BasicArrayList list = keyValuePersistance.getIds();

        keyValuePersistance.save(abeClientInformation, hashtable);

        for (int index = 0; index < list.size(); index++)
        {
            Integer integer = (Integer) list.objectArray[index];
            keyValuePersistance.delete(abeClientInformation, integer.intValue());
        }

        // Keep Loaded Value until next static initialization
        // this.SOME_GAME_CONFIGURATION.setValue(valueToKeep);
    }
}