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

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class GameOptionsFormFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final GameOptionsFormFactory instance = new GameOptionsFormFactory();
    
    private GameOptionsFormFactory()
    {
        
    }
    
    public static GameOptionsFormFactory getInstance()
    {
        return instance;
    }
    
    public GameOptionsForm init(
            CommandListener commandListener
            //, InitInterface initInterface
            , String title, BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
    {        
        try
        {
        //if(GameOptionsForm.getInstance() == null)
        //{
            //initInterface.init();
            //SINGLETON = new GameOptionsForm(commandListener, title);
        //}
            return new GameOptionsForm(commandListener, title, backgrounBasicColor, foregroundBasicColor);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
            return null;
        }

        //return SINGLETON;
    }
}
