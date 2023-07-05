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
package org.allbinary.game.displayable.canvas;

import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.UpGameKeyEventHandler;

/**
 *
 * @author user
 */
public class GameEventHandlerUtil {

    public static void removeAllListeners()
    {
        UpGameKeyEventHandler.getInstance().removeAllListeners();
        DownGameKeyEventHandler.getInstance().removeAllListeners();
    }
}
