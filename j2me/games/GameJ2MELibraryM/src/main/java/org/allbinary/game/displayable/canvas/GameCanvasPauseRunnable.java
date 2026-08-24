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

import jsinterop.annotations.JsType;

import org.allbinary.AndroidUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GameCanvasPauseRunnable extends GameRunnable
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    @JsConstructor
    public GameCanvasPauseRunnable(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }

    @Override
    @JsMethod
    public void run()
    {
    }
    
    @Override
    @JsMethod
    public void processLoopSleep()
    throws Exception
    {
        ////No need to pause for OpenGL on J2SE.
        //It would seem that the threading for OpenGL on J2SE does need pause now.
        //if(AndroidUtil.isAndroid()) {
            try {
                final Features features = Features.getInstance();
                final boolean isOpenGL = features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL);

                if (isOpenGL) {
                    this.allBinaryGameCanvas.processSleep();
                }
            } catch (Exception e) {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        //}
        //allBinaryGameCanvas.processLoopSleep();
    }
}

