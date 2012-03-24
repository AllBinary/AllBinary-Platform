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

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.StringItem;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.canvas.Processor;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.configuration.persistance.GamePersistanceSingleton;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.displayable.screen.CommandForm;
import allbinary.graphics.displayable.screen.ScreenRepaintProcessorFactory;

public class LoadGameForm extends CommandForm
{
    /*
     * private static LoadGameForm FORM;
     * 
     * public static LoadGameForm getInstance( CommandListener commandListener,
     * String title) throws Exception { FORM = new LoadGameForm(commandListener,
     * title); return FORM; }
     * 
     * public static LoadGameForm getInstance() { return FORM; }
     */

    private final Processor repaintProcessor =
            ScreenRepaintProcessorFactory.getInstance().getInstance(this);

    private boolean areChoices;
    
    public LoadGameForm(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
            throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));

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

        this.repaintProcessor.process();
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
            LogUtil.put(LogFactory.getInstance("Adding Choice: " + object.toString(), this, METHOD_NAME));

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