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

import javax.microedition.lcdui.Canvas;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.NullRunnable;

/**
 *
 * @author User
 */
public class AlwaysRepaintBehavior extends RepaintBehavior {
    
    private static final AlwaysRepaintBehavior instance = new AlwaysRepaintBehavior();

    /**
     * @return the instance
     */
    public static AlwaysRepaintBehavior getInstance() {
        return instance;
    }
        
    private final String NAME = "AlwaysRepaintBehavior";
    
    @Override
    public void repaint(final Canvas canvas) {
        
        final Features features = Features.getInstance();
        final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();
        
        if(features.isFeature(openGLFeatureFactory.OPENGL)) {
            DisplayInfoSingleton.getInstance().process();
        } else {
            final Thread thread = new Thread(new NullRunnable() {
                
                @Override
                public void run() {
                    try {
                        //System.out.println("TWB:AlwaysRepaintBehavior:repaint");
                        canvas.repaint();
                        DisplayInfoSingleton.getInstance().process();
                    } catch (Exception e) {
                        final CommonStrings commonStrings = CommonStrings.getInstance();
                        logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                    }
                }
            }, NAME);
            thread.start();
        }
        
    }
    
    @Override
    public void onChangeRepaint(final Canvas canvas) {
        
    }
}
