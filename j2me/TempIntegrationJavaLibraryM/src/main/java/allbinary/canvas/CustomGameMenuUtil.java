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
package allbinary.canvas;

import abcs.logic.communication.log.PreLogUtil;
import allbinary.AppletUtil;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.HTMLFeatureFactory;
import allbinary.graphics.displayable.MyCanvas;

public class CustomGameMenuUtil
{
    public static void add(MyCanvas canvas)
    {
        GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();

        if (!AppletUtil.isAppletLoader(canvas) && 
                !Features.getInstance().isDefault(HTMLFeatureFactory.getInstance().HTML))
        {
            canvas.addCommand(gameCommandsFactory.EXIT_COMMAND);
        }
    }
}
