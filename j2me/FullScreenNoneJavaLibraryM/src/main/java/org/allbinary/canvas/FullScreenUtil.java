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
package org.allbinary.canvas;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import jsinterop.annotations.JsMethod;


@JsType
public class FullScreenUtil
{
    private static final FullScreenUtil instance = new FullScreenUtil();

    /**
     * @return the instance
     */
    @JsMethod
    public static FullScreenUtil getInstance() {
        return FullScreenUtil.instance;
    }
    
    @JsMethod
    public final void initOnRun(final Canvas fullScreenInterface, final CommandListener commandListener)
        throws Exception
    {
        if (commandListener != null)
        {
            this.init(fullScreenInterface);
        }
    }

    @JsMethod
    public final void init(final Canvas fullScreenInterface)
    {
    }

    @JsMethod
    public final boolean isScreenChange(final boolean isFullScreen)
    {
        final MainFeatureFactory mainFeatureFactory = MainFeatureFactory.getInstance();

        final Features features = Features.getInstance();

        if (features.isFeature(mainFeatureFactory.FULL_SCREEN) != isFullScreen)
        {
            return true;
        }

        return false;
    }

    @JsMethod
    public final boolean isScreenChangeCanvas(final Canvas fullScreenInterface)
    {
        return this.isScreenChange(false);
    }
}
