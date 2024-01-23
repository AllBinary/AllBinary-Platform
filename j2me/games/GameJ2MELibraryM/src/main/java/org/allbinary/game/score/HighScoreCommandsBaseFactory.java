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
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class HighScoreCommandsBaseFactory {
    
    public final Command[] HIGH_SCORE_COMMANDS;

    protected HighScoreCommandsBaseFactory(Command[] HIGH_SCORE_COMMANDS)
    {
        if(J2MEUtil.isJ2ME())
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.START).append("J2ME", this, commonStrings.CONSTRUCTOR));
            
            //TWB - Hackish but it is better than a whole new library
            this.HIGH_SCORE_COMMANDS = new Command[] 
                    {
                    HighScoreCommands.getInstance().PERSONAL,
                    };
        }
        else
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.START).append("not J2ME", this, commonStrings.CONSTRUCTOR));

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
