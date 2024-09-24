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
package org.allbinary.game.displayable.canvas;

import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.paint.PaintableComposite;
import org.allbinary.graphics.paint.PaintableInterface;

public class DemoCanvasProgressUtil
{
    public static void showProgress(DemoCanvas demoCanvas)
    {
        DemoCanvasProgressUtil.showProgress(demoCanvas, false);
    }
    
    public static void showProgress(DemoCanvas demoCanvas, boolean background)
    {
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        progressCanvas.startBackground(background);

        PaintableInterface[] paintableArray = new PaintableInterface[2];
        
        paintableArray[0] = progressCanvas;
        paintableArray[1] = demoCanvas.getDefaultPaintableInterface();
        
        demoCanvas.setDefaultPaintableInterface(
                new PaintableComposite(paintableArray));
        
        demoCanvas.setPaintableInterface(demoCanvas.getDefaultPaintableInterface());
    }

}
