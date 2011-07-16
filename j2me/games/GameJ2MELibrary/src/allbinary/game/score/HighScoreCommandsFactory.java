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
public class HighScoreCommandsFactory extends HighScoreCommandsBaseFactory
{
    private static final HighScoreCommandsFactory instance =
            new HighScoreCommandsFactory();

    /**
     * @return the instance
     */
    public static HighScoreCommandsFactory getInstance()
    {
        return instance;
    }

    private HighScoreCommandsFactory()
    {
        super(new Command[] 
        {
            HighScoreCommands.getInstance().PERSONAL,
            HighScoreCommands.getInstance().WORLD}
        );
    }
}
