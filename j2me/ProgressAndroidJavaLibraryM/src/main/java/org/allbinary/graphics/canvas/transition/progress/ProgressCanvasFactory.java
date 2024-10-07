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

import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.logic.string.StringUtil;

public class ProgressCanvasFactory {

    private final static ProgressCanvas instance = 
        // new AndroidTitleProgressBar(StringUtil.getInstance().EMPTY_STRING);
        new AndroidBasicTitleProgressBar(StringUtil.getInstance().EMPTY_STRING, 
                BasicColorFactory.getInstance().BLACK, 
                BasicColorFactory.getInstance().WHITE);
        // new AndroidProgressDialog(StringUtil.getInstance());
         //new ProgressCanvas(StringUtil.getInstance().EMPTY_STRING, 
                //BasicColorFactory.getInstance().BLACK, 
                //BasicColorFactory.getInstance().WHITE);
    
    public static ProgressCanvas getInstance()
    {
        return instance;
    }

    public static PaintableInterface getLazyInstance() {
        return NullPaintable.getInstance();
    }
    
}
