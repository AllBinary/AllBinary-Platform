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

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.form.ScreenInfo;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.util.visitor.Visitor;

public class LimitCommandsVisitor 
    extends Visitor
{
    private final boolean isNotSmallDisplay;

    public LimitCommandsVisitor()
    {
        DisplayInfoSingleton displayInfo =
                DisplayInfoSingleton.getInstance();

        isNotSmallDisplay = 
                (displayInfo.isPortrait() && displayInfo.getLastHeight() > ScreenInfo.getInstance().SMALL_WIDTH) ||
                (!displayInfo.isPortrait() && displayInfo.getLastHeight() > ScreenInfo.getInstance().SMALL_HEIGHT);
    }
    
    public Object visit(Object object)
    {
        Command command = (Command) object;

        BooleanFactory booleanFactory = BooleanFactory.getInstance();
        
        if (isNotSmallDisplay || command.getPriority() == 1
                || command == GameCommandsFactory.getInstance().EXIT_COMMAND
                || command == GameCommandsFactory.getInstance().EXIT_WITHOUT_PROGRESS_COMMAND)
        {
            return booleanFactory.TRUE;
        }
        else
        {
            return booleanFactory.FALSE;
        }        
    }
}
