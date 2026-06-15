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

import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;

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
        return AboutPaintableFactory.instance;
    }

    public Paintable[] paintableArray = new Paintable[] { NullPaintable.getInstance() };
}