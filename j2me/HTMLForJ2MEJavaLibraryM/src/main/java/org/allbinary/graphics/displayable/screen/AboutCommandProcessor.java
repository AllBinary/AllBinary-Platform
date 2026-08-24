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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public class AboutCommandProcessor {

    private static final AboutCommandProcessor instance = new AboutCommandProcessor();

    /**
     * @return the instance
     */
    @JsMethod
    public static AboutCommandProcessor getInstance() {
        return AboutCommandProcessor.instance;
    }
    
    @JsMethod
    public void process(final CommandListener midletCommandListener, final Command command, final Canvas canvas) {
        midletCommandListener.commandAction(command, canvas);
    }
}