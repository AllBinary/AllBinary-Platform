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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;
import playn.core.PlayN;

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
            //HTML
            PlayN.openURL((String) list.remove(0));

        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "process", e));
        }
    }
}