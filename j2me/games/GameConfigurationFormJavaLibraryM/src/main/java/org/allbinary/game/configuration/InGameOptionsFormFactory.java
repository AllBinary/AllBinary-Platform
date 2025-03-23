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

import javax.microedition.lcdui.CommandListener;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.init.Init;

public class InGameOptionsFormFactory
{
    private static final InGameOptionsFormFactory instance = new InGameOptionsFormFactory();

    private static InGameOptionsForm SINGLETON;

    public static void init(CommandListener commandListener, Init initInterface, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
    {
        try
        {
            if (SINGLETON == null)
            {
                initInterface.init();
                SINGLETON = new InGameOptionsForm(commandListener, title, backgrounBasicColor, foregroundBasicColor);
            }
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, instance, CommonStrings.getInstance().INIT, e));
        }
    }
    
    public static InGameOptionsForm getInstance()
    {
        return SINGLETON;
    }
}
