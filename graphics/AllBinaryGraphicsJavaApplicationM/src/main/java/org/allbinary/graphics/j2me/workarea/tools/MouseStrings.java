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
package org.allbinary.graphics.j2me.workarea.tools;

import org.allbinary.string.CommonSeps;

/**
 *
 * @author user
 */
public class MouseStrings {

    private static final MouseStrings instance = new MouseStrings();

    /**
     * @return the instance
     */
    public static MouseStrings getInstance()
    {
        return instance;
    }

    public final String MOUSE_PRESSED = "mousePressed";
    public final String MOUSE_RELEASED = "mouseReleased";
    public final String MOUSE_CLICKED = "mouseClicked";

    public final String MOUSE_DRAGGED = "mouseDragged";
    public final String MOUSE_MOVED = "mouseMoved";

    public final String MOUSE_PRESSED_LABEL = "MousePressed" + CommonSeps.getInstance().COLON_SEP;
    public final String MOUSE_RELEASED_LABEL = "MouseReleased" + CommonSeps.getInstance().COLON_SEP;
    public final String MOUSE_CLICKED_LABEL = "MouseClicked" + CommonSeps.getInstance().COLON_SEP;

    public final String MOUSE_DRAGGED_LABEL = "MouseDragged" + CommonSeps.getInstance().COLON_SEP;
    public final String MOUSE_MOVED_LABEL = "MouseMoved" + CommonSeps.getInstance().COLON_SEP;

}
