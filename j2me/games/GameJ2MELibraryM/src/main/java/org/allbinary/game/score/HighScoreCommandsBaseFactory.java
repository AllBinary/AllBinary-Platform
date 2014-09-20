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
package org.allbinary.game.score;

import javax.microedition.lcdui.Command;

import org.allbinary.J2MEUtil;

public class HighScoreCommandsBaseFactory {
    
    public final Command[] HIGH_SCORE_COMMANDS;

    protected HighScoreCommandsBaseFactory(Command[] HIGH_SCORE_COMMANDS)
    {
        if(J2MEUtil.isJ2ME())
        {
            //TWB - Hackish but it is better than a whole new library
            this.HIGH_SCORE_COMMANDS = new Command[] 
                    {
                    HighScoreCommands.getInstance().PERSONAL,
                    };
        }
        else
        {
            this.HIGH_SCORE_COMMANDS = HIGH_SCORE_COMMANDS;
        }
    }

    public boolean isHighScoreCommand(Command command)
    {
        for(int index = HIGH_SCORE_COMMANDS.length - 1; index >= 0; index--)
        {
            if(command == HIGH_SCORE_COMMANDS[index])
            {
                return true;
            }
        }
        return false;
    }

    public int getIndex(Command command)
    throws Exception
    {
        for(int index = HIGH_SCORE_COMMANDS.length - 1; index >= 0; index--)
        {
            if(command == HIGH_SCORE_COMMANDS[index])
            {
                return index;
            }
        }
        throw new Exception("No Such Command");
    }
}
