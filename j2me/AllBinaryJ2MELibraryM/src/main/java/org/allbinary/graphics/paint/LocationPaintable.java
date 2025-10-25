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
package org.allbinary.graphics.paint;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author user
 */
public class LocationPaintable
    implements LocationPaintableInterface {

    private static final LocationPaintable instance = new LocationPaintable();

    /**
     * @return the instance
     */
    public static LocationPaintable getInstance() {
        return instance;
    }
    
    @Override
    public void paint(final Graphics graphics, final int x, final int y) {
    }

}
