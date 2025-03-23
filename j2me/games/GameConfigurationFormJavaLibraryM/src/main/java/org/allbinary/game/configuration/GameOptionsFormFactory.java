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

public class GameOptionsFormFactory
{
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
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().INIT, e));
            return null;
        }

        //return SINGLETON;
    }
}
