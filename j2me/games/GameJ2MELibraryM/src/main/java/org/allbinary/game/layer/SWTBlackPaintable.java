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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.paint.Paintable;

class SWTBlackPaintable extends Paintable {

    private final BasicColorSetUtil basicSetColorUtil = BasicColorSetUtil.getInstance();
    private final BasicColor BLACK = BasicColorFactory.getInstance().BLACK;

    public void paint(Graphics graphics) {
        this.basicSetColorUtil.setBasicColorP(graphics, this.BLACK);
    }
}
