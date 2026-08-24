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
package org.allbinary.game.displayable;

import jsinterop.annotations.JsType;
import org.allbinary.thread.ARunnable;


import javax.microedition.lcdui.Canvas;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.NullRunnable;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class RepaintBehavior {
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final RepaintBehavior instance = new RepaintBehavior();

    /**
     * @return the instance
     */
    @JsMethod
    public static RepaintBehavior getInstance() {
        return RepaintBehavior.instance;
    }
    
    @JsMethod
    public void repaint(final Canvas canvas) {

    }

    private final String NAME = "RepaintBehavior";
    @JsMethod
    public void onChangeRepaint(final Canvas canvas) {

        final Features features = Features.getInstance();
        final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();
        
        if(features.isFeature(openGLFeatureFactory.OPENGL)) {
            DisplayInfoSingleton.getInstance().process();
        } else {
            final Thread thread = new Thread(new ARunnable() {
                
                @Override
                @JsMethod
                public void run() {
                    try {
                        //System.out.println("TWB:RepaintBehavior:repaint");
                        canvas.repaint();
                        DisplayInfoSingleton.getInstance().process();
                    } catch (Exception e) {
                        final CommonStrings commonStrings = CommonStrings.getInstance();
                        final LogUtil logUtil = LogUtil.getInstance();
                        logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                    }
                }
            }, this.NAME);
            thread.start();
        }

    }

}
