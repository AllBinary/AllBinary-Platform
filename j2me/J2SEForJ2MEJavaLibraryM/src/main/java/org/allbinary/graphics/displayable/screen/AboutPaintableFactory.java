/*
* AllBinary Open License Version 1
* Copyright (c) 2022 AllBinary
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

package org.allbinary.graphics.displayable.screen;

import org.allbinary.game.paint.AboutPaintable;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class AboutPaintableFactory {

    private static final AboutPaintableFactory instance = new AboutPaintableFactory();
    
    /**
     * @return the instance
     */
    public static AboutPaintableFactory getInstance() {
        return instance;
    }

    private AboutPaintableFactory() {
        /*
        final String[] INTENTS = {
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                "Email Intent",
                StringUtil.getInstance().EMPTY_STRING,
                };
        
        final String[] LINKS = {
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                //"https://www.allbinary.com/Privacy.jsp"
                };        
        */

        final String[] INFO = {
                "Built with the",
                "AllBinary Platform",
                StringUtil.getInstance().EMPTY_STRING,
                //StringUtil.getInstance()
                //"More info at: http://"
                "Comments or Questions:",
                "support@allbinary.com",
                //"Privacy Policy"                
                };
        
        final String[] DEVELOPERS = {
                "Developed By:", "Travis Berthelot"
                };
        
        this.paintableArray = AboutPaintable.getInstance(INFO, DEVELOPERS).getPaintableArrayInstance();        
    }
    
    public Paintable[] paintableArray = new Paintable[] { NullPaintable.getInstance() };
}