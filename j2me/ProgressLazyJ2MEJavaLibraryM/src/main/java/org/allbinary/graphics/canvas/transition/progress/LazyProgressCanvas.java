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
package org.allbinary.graphics.canvas.transition.progress;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author User
 */
public class LazyProgressCanvas extends ProgressCanvas {

    private final Paintable GAUGE_PAINTABLE = new Paintable() {
        public void paint(Graphics graphics) {
            final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
            graphics.fillRect(0, 0, displayInfoSingleton.getLastWidth(), displayInfoSingleton.getLastHeight());
            gauge.paint(graphics, 0, 0);
        }
    };
    
    private Paintable paintable = GAUGE_PAINTABLE;
    
    protected LazyProgressCanvas(final String title, final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) {
        super(title, backgroundBasicColor, foregroundBasicColor);
    }
    
    @Override    
    public void start()
    {
        super.start();
        this.paintable = GAUGE_PAINTABLE;
    }
    
    @Override    
    public void end()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.END_METHOD_NAME));
        this.endActual();
        this.paintable = GAUGE_PAINTABLE;
    }
    
    public void endFromInitialLazyLoadingComplete()
    {
        super.endFromInitialLazyLoadingComplete();
        this.paintable = NullPaintable.getInstance();
    }
    
    public void paint(Graphics graphics)
    {
        this.paintable.paint(graphics);
    }
    
}
