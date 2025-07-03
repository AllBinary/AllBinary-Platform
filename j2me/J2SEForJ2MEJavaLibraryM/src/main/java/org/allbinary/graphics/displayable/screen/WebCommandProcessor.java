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

import java.awt.Desktop;
import java.net.URI;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class WebCommandProcessor {

    private static final WebCommandProcessor instance = new WebCommandProcessor();

    /**
     * @return the instance
     */
    public static WebCommandProcessor getInstance() {
        return instance;
    }

    private final AboutCommandProcessor aboutCommandProcessor = AboutCommandProcessor.getInstance();

    //Hack
    public final BasicArrayList list = new BasicArrayList();
    
    public void process(final CommandListener midletCommandListener, final Command command, final Canvas canvas) {
        try {
            //J2SE
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI((String) list.remove(0)));
            }

        } catch(Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e));
        }
    }
}