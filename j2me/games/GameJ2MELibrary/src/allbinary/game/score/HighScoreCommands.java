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
package allbinary.game.score;

import javax.microedition.lcdui.Command;

/**
 *
 * @author user
 */
public class HighScoreCommands
{
    private static final HighScoreCommands instance = new HighScoreCommands();

    /**
     * @return the instance
     */
    public static HighScoreCommands getInstance()
    {
        return instance;
    }

    public final Command DISPLAY = new Command("Scores", Command.SCREEN, 1);

    public final Command PERSONAL = new Command("Personal", Command.SCREEN, 1);
    public final Command WORLD = new Command("World", Command.SCREEN, 1);
}
