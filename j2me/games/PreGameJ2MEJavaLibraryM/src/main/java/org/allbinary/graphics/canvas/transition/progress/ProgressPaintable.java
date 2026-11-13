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
package org.allbinary.graphics.canvas.transition.progress;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.Paintable;

public class ProgressPaintable extends Paintable {

    private final ProgressCanvas progressCanvas;

    public ProgressPaintable(final ProgressCanvas progressCanvas) {
        this.progressCanvas = progressCanvas;
    }

    @Override
    public void paint(final Graphics graphics) {
        this.progressCanvas.paint2(graphics);
    }
}
