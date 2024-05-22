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
package org.allbinary.canvas;

import org.allbinary.AppletUtil;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.graphics.displayable.MyCanvas;

public class CustomGameMenuUtil
{
    public static void add(final MyCanvas canvas)
    {
        final GameCommandsFactory gameCommandsFactory = GameCommandsFactory.getInstance();

        if (AppletUtil.isAppletLoader(canvas)) {
        } else if(Features.getInstance().isDefault(HTMLFeatureFactory.getInstance().HTML)) {
        } else {
            canvas.addCommand(gameCommandsFactory.EXIT_COMMAND);
        }
    }
}
