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
package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Command;

import org.allbinary.graphics.color.BasicColor;

public class CommandTextItem extends TextItem {

    private final Command command;

    public CommandTextItem(Command command, int layout, String altText, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(command.getLabel(), layout, altText, backgroundBasicColor, foregroundBasicColor);

        this.command = command;
    }

    @Override
    public boolean isFocusable()
    {
        return true;
    }

    /**
     * @return the command
     */
    public Command getCommand()
    {
        return command;
    }
}
