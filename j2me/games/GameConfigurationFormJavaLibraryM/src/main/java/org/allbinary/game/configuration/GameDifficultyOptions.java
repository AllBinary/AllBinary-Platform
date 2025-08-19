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
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.persistance.GameDifficultyFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

public class GameDifficultyOptions extends CommandForm
{
    
    private final BasicArrayList list;

    public GameDifficultyOptions(CommandListener commandListener, String title, BasicArrayList list,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
            throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        
        this.list = list;

        this.update();

        this.initCommands(commandListener);
        
        final GameDifficultyFactory gameDifficultyFactory = GameDifficultyFactory.getInstance();
        
        this.setSelectedId(gameDifficultyFactory.getLevel());
    }

    @Override
    public void open()
    {
        super.open();
    }
    
    @Override
    public void close() throws Exception
    {
        super.close();
        this.save();
    }
    
    public void save() throws Exception
    {
        GameDifficultyFactory gameDifficultyFactory =
            GameDifficultyFactory.getInstance();
        
        gameDifficultyFactory.setLevel(this.getSelectedId());
    }
    
    @Override
    public void initCommands(CommandListener cmdListener)
    {
        GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        this.removeAllCommands();
        this.addCommand(gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS);

        this.setCommandListener(cmdListener);
    }

    @Override
    public void update() throws Exception
    {
        this.deleteAll();

        this.add(list, "Difficulty", Choice.EXCLUSIVE);

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
        final String METHOD_NAME = "addChoiceGroup";
        final String NAME = "Adding Choice: ";

        ChoiceGroup choiceGroup = new ChoiceGroup(name, option);

        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            Object object = list.objectArray[index];

            logUtil.put(new StringMaker().append(NAME).append(object.toString()).toString(), this, METHOD_NAME);

            choiceGroup.append(object.toString(), NullCanvas.NULL_IMAGE);
        }
        return choiceGroup;
    }

    public void setSelectedId(int id)
    {
        ChoiceGroup choiceGroup = (ChoiceGroup) this.get(0);
        choiceGroup.setSelectedIndex(id, true);
    }
    
    public int getSelectedId()
    {
        ChoiceGroup choiceGroup = (ChoiceGroup) this.get(0);
        return choiceGroup.getSelectedIndex();
    }

    public int getSelectedText()
    {
        final ChoiceGroup choiceGroup = (ChoiceGroup) this.get(0);
        final int value = Integer.valueOf(choiceGroup.getString(choiceGroup.getSelectedIndex())).intValue();
        return value;
    }
}