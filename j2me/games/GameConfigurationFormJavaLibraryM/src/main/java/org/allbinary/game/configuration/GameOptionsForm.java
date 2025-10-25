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
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

public class GameOptionsForm extends CommandForm
    //CommandForm
{

    /*
    public static GameOptionsForm getInstance()
    {
        return SINGLETON;
    }
    */

    public GameOptionsForm(final CommandListener commandListener, final String title,
            final BasicColor backgrounBasicColor, final BasicColor foregroundBasicColor)
        throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.addConfiguration();

        final GameFeatureFormUtil gameFeatureFormUtil = 
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
        final Hashtable hashtable = GameFeatureChoiceGroups.getExclusiveInstance().get();
        if(hashtable != null)
        {
            Object listCanBeNull = hashtable.get(key);
            if(listCanBeNull != null)
            {
                final BasicArrayList list = (BasicArrayList) listCanBeNull;
                if(list.contains(SensorFeatureFactory.getInstance().SIMULATED_ORIENTATION_SENSORS)) {
                    this.addTextFields();
                }
            }
        }
    }
    
    private void addTextFields()
    {
        final Hashtable hashtable = GameConfigurationTextInput.getHashtable();
        final int size = hashtable.size();

        final Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        GameConfigurationTextInput gameConfigurationTextInput;
        TextField textField;
        for (int index = 0; index < size; index++)
        {
            gameConfigurationTextInput = (GameConfigurationTextInput) hashtable.get((Object) objectArray[index]);

            textField = new TextField(gameConfigurationTextInput.getLabel(), 
                gameConfigurationTextInput.getText(), 30,TextField.ANY);

            this.append(textField);
        }
    }

    private void addConfiguration()
    {
        final String METHOD_NAME = "addConfiguration";
        final String NAME = "Name: ";
        
        final BasicArrayList list = GameConfigurationSingleton.getInstance()
                .getOptionsBasicArrayList();

        final Command GAUGE_CHANGE = MyCommandsFactory.getInstance().GAUGE_CHANGE;
        
        final StringMaker stringMaker = new StringMaker();
        
        final int size = list.size();
        GameConfiguration gameConfiguration;
        GameConfigurationGauge gauge;
        for (int index = 0; index < size; index++)
        {
            gameConfiguration = (GameConfiguration) list.objectArray[index];

            stringMaker.delete(0, stringMaker.length());
            logUtil.put(stringMaker.append(NAME).append(gameConfiguration.toString()).toString(), this, METHOD_NAME);

            gauge = new GameConfigurationGauge(gameConfiguration);

            gauge.setDefaultCommand(GAUGE_CHANGE);
            gauge.setItemCommandListener(new GameFeatureItemCommandListener(this));
                    //.getInstance());
            // this.setCommandListener(l);

            this.append(gauge);
        }
    }

    @Override
    public void initCommands(final CommandListener cmdListener)
    {
        final GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        this.removeAllCommands();
        this.addCommand(gameCommandsFactory.CLOSE_OPTIONS);
        this.addCommand(gameCommandsFactory.DEFAULT_OPTIONS);

        this.setCommandListener(cmdListener);
    }

    public void save(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        final int size = this.size();
        Item item;
        for (int index = 0; index < size; index++)
        {
            item = this.get(index);
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

        final Hashtable hashtable = new Hashtable();
        
        final GameConfiguration SCALE = GameConfigurationCentral.getInstance().SCALE;
        
        hashtable.put(SCALE.getName(), SCALE.getValue().toString());

        final KeyValuePersistance keyValuePersistance =
            GameConfigurationPersistanceSingleton.getInstance();
        
        keyValuePersistance.clear();
        keyValuePersistance.loadAll(abeClientInformation);

        final BasicArrayList list = keyValuePersistance.getIds();

        keyValuePersistance.save(abeClientInformation, hashtable);

        final int size2 = list.size();
        Integer integer;
        for (int index = 0; index < size2; index++)
        {
            integer = (Integer) list.objectArray[index];
            keyValuePersistance.delete(abeClientInformation, integer.intValue());
        }

        // Keep Loaded Value until next static initialization
        // this.SOME_GAME_CONFIGURATION.setValue(valueToKeep);
    }
}