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

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.StringItem;

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.persistance.GamePersistanceSingleton;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class LoadGameForm extends CommandForm
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    /*
     * private static LoadGameForm FORM;
     * 
     * public static LoadGameForm getInstance( CommandListener commandListener,
     * String title) throws Exception { FORM = new LoadGameForm(commandListener,
     * title); return FORM; }
     * 
     * public static LoadGameForm getInstance() { return FORM; }
     */

    private boolean areChoices;
    
    public LoadGameForm(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
            throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.update();

        this.initCommands(commandListener);
    }

    public void initCommands(CommandListener cmdListener)
    {
        GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        this.removeAllCommands();
        this.addCommand(gameCommandsFactory.LOAD_FILE);
        this.addCommand(gameCommandsFactory.DELETE_FILE);
        this.addCommand(gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS);

        this.setCommandListener(cmdListener);
    }

    public void update() throws Exception
    {
        BasicArrayList list = GamePersistanceSingleton.getInstance().getIds();
        this.deleteAll();
        if (list.size() > 0)
        {
            this.add(list, "Select A Saved Game", Choice.EXCLUSIVE);
            this.areChoices = true;
        }
        else
        {
            this.append(new StringItem("No Saved Games", StringUtil.getInstance().EMPTY_STRING));
            this.areChoices = false;
        }

        super.update();
    }

    private void add(BasicArrayList list, String name, int option)
    {
        ChoiceGroup choiceGroup = this.getChoiceGroup(list, name, option);

        if (list.size() > 0)
        {
            choiceGroup.setSelectedIndex(0, true);
        }

        this.append(choiceGroup);
    }

    private ChoiceGroup getChoiceGroup(BasicArrayList list, String name, int option)
    {
        final String METHOD_NAME = "getChoiceGroup";
        
        ChoiceGroup choiceGroup = new ChoiceGroup(name, option);

        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            Object object = list.objectArray[index];
            logUtil.put(new StringMaker().append("Adding Choice: ").append(object.toString()).toString(), this, METHOD_NAME);

            choiceGroup.append(object.toString(), null);
        }
        return choiceGroup;
    }

    public int getSelectedId()
    {
        if (this.areChoices)
        {
            ChoiceGroup choiceGroup = (ChoiceGroup) this.get(0);
            return choiceGroup.getSelectedIndex();
        }
        else
        {
            return -1;
        }
    }

    public int getSelectedText()
    {
        if (this.areChoices)
        {
            ChoiceGroup choiceGroup = (ChoiceGroup) this.get(0);
            return Integer.valueOf(choiceGroup.getString(choiceGroup.getSelectedIndex())).intValue();
        }
        else
        {
            return -1;
        }
    }
}