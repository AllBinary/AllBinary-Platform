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
package org.allbinary.game.configuration.feature;

import java.util.Hashtable;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Item;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.GameConfigurationGauge;
import org.allbinary.game.configuration.GameConfigurationUtil;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.logic.string.StringMaker;

public class GameFeatureFormUtil
{
    private static final GameFeatureFormUtil instance = new GameFeatureFormUtil();
    
    public static GameFeatureFormUtil getInstance()
    {
        return instance;
    }
    
    public ChoiceGroup getChoiceGroup(Hashtable hashtable, String name, int option)
    {
        final StringMaker stringMaker = new StringMaker();
        
        final ChoiceGroup choiceGroup = new ChoiceGroup(name, option);

        final BasicArrayList list = (BasicArrayList) hashtable.get(name);

        final Features features = Features.getInstance();
        
        final int size = list.size();
        for (int index = 0; index < size; index++)
        {
            final Feature gameFeature = (Feature) list.objectArray[index];
            
            stringMaker.delete(0, stringMaker.length());
            LogUtil.put(LogFactory.getInstance(stringMaker.append(name).append(": Adding Choice: ").append(gameFeature.toString()).toString(), this, "getChoiceGroup"));
            choiceGroup.append(gameFeature.toString(), null);
            if (features.isFeature(gameFeature))
            {
                choiceGroup.setSelectedIndex(index, true);
            }
        }
        return choiceGroup;
    }

    public void addChoiceGroup(CommandForm form, Hashtable hashtable, int option)
    {
        final StringMaker stringMaker = new StringMaker();

        final int size = hashtable.size();
        final Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        for (int index = 0; index < size; index++)
        {
            String name = (String) objectArray[index];

            stringMaker.delete(0, stringMaker.length());
            LogUtil.put(LogFactory.getInstance(stringMaker.append("Adding Choice Group: ").append(name).toString(), this, "addChoiceGroup"));

            form.append(this.getChoiceGroup(hashtable, name, option));
        }
    }

    public void setDefault(CommandForm form) throws Exception
    {
        for (int index = 0; index < form.size(); index++)
        {
            Item item = form.get(index);
            if (item instanceof GameConfigurationGauge)
            {
                GameConfigurationUtil.getInstance().setDefault((GameConfigurationGauge) item);
            }
            else if (item instanceof ChoiceGroup)
            {
                GameFeatureUtil.getInstance().setDefault((ChoiceGroup) item);
                /*
                 * if (GameFeatureUtil.isExclusive(item.getLabel())) {
                 * GameFeatureUtil.setDefault((ChoiceGroup) item); } else {
                 * GameFeatureUtil.setDefault((ChoiceGroup) item); }
                 */
            }
        }
    }    
}
