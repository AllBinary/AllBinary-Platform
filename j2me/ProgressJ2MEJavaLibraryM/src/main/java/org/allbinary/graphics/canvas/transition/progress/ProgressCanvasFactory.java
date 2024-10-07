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

import org.allbinary.logic.string.StringUtil;

import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.PaintableInterface;

public class ProgressCanvasFactory {

    private static ProgressCanvas PROGRESS_FORM_SCREEN = new ProgressCanvas(
            StringUtil.getInstance().EMPTY_STRING, 
            BasicColorFactory.getInstance().BLACK, 
            BasicColorFactory.getInstance().WHITE);
    
    public static ProgressCanvas getInstance()
    {
        return PROGRESS_FORM_SCREEN;
    }
    
    public static PaintableInterface getLazyInstance() {
        return NullPaintable.getInstance();
    }
    
}
