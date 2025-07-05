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
package org.allbinary.media.image;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.microemu.app.BareMain;

/**
 *
 * @author User
 */
public class ImageSwtUtil {

    private final static ImageSwtUtil instance = new ImageSwtUtil();

    /**
     * @return the instance
     */
    public static ImageSwtUtil getInstance() {
        return instance;
    }

    //public final Color TRANSPARENT_COLOR = new Color(BareMain.shell.getDisplay(), new RGB(0, 0, 0));
    //public final RGBA TRANSPARENT_COLOR = new RGBA(0,0,0,0);
}
