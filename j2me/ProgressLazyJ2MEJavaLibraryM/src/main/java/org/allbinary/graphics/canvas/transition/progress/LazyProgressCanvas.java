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

import org.allbinary.canvas.Processor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author User
 */
public class LazyProgressCanvas extends ProgressCanvas {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    protected LazyProgressCanvas(final String title, final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) {
        super(title, backgroundBasicColor, foregroundBasicColor);        
    }
    
    @Override
    public void start()
    {
        super.start();
        this.hasPainted = false;
    }
    
    @Override
    public void end()
    {
        try {
            logUtil.put(commonStrings.START, this, commonStrings.END_METHOD_NAME);
            this.endActual();
            this.paintable = GAUGE_PAINTABLE;
            ImageCacheFactory.getInstance().runTask();
        } catch(Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.END_METHOD_NAME);
        }
    }
    
    public void inGame() {
        inGameProcessor = Processor.getInstance();
    }
    
    public void endFromInitialLazyLoadingComplete()
    {
        super.endFromInitialLazyLoadingComplete();
        //logUtil.put(commonStrings.START, this, END_FROM_INITIAL_LAZY_LOADING_COMPLETE);
        this.paintable = NullPaintable.getInstance();
    }
    
    public void endIfPaintedSinceStart()
    {
        if(this.paintable == GAUGE_PAINTABLE && this.hasPainted) {
            this.endFromInitialLazyLoadingComplete();
        }
    }
        
}
