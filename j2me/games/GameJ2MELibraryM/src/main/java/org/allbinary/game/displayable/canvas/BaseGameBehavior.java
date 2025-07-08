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
package org.allbinary.game.displayable.canvas;

import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.game.GameAdState;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.opengles.CurrentDisplayableFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

/**
 *
 * @author User
 */
public class BaseGameBehavior extends DemoGameBehavior {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private static final BaseGameBehavior instance = new BaseGameBehavior();

    /**
     * @return the instance
     */
    public static DemoGameBehavior getInstance() {
        return instance;
    }

    //@Override
    public void init() throws Exception {
        final GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();

        gameAdState.playingAdState();
    }

    //@Override
    public void pause(final AllBinaryGameCanvas allBinaryGameCanvas) {
        
        final Features features = Features.getInstance();

//        if(SWTUtil.isSWT) {
//
//            final Runnable runnable = new Runnable() {
//                public void run() {
//                    try {
//                        allBinaryGameCanvas.processSleep();
//                    } catch (Exception e) {
//                        final CommonStrings commonStrings = CommonStrings.getInstance();
//                        logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
//                    }
//                }
//            };
//            logUtil.put("Set SWT Thread and assign runnable: " + runnable, this, "pause");
//
//            final SWTProcessorUtil swtProcessorUtil = SWTProcessorUtil.getInstance();
//            final SWTRunnableProcessor swtRunnableProcessor = SWTRunnableProcessor.getInstance();
//            swtRunnableProcessor.runnable = runnable;
//            swtProcessorUtil.swtProcessor = swtRunnableProcessor;
//
//            final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();
//
//            currentDisplayableFactory.setRunnable(allBinaryGameCanvas.gamePauseRunnable);
//            
//        } else 
            if (features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD) 
                //|| features.isDefault(HTMLFeatureFactory.getInstance().HTML)
                ) {
            
            //logUtil.put("pause", this, METHOD_NAME);

            final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();

            currentDisplayableFactory.setRunnable(allBinaryGameCanvas.gamePauseRunnable);
        }

    }

    //@Override
    public void unPause(final AllBinaryGameCanvas allBinaryGameCanvas) {

//        if(SWTUtil.isSWT) {
//            
//            final Runnable runnable = new Runnable() {
//                public void run() {
//                    try {
//                        allBinaryGameCanvas.run3();
//                    } catch (Exception e) {
//                        final CommonStrings commonStrings = CommonStrings.getInstance();
//                        logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
//                    }
//                }
//            };
//            logUtil.put("Set SWT Thread and assign runnable: " + runnable, this, gameStrings.UNPAUSE);
//
//            final SWTProcessorUtil swtProcessorUtil = SWTProcessorUtil.getInstance();
//            final SWTRunnableProcessor swtRunnableProcessor = SWTRunnableProcessor.getInstance();
//            swtRunnableProcessor.runnable = runnable;
//            swtProcessorUtil.swtProcessor = swtRunnableProcessor;
//
//            final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();
//
//            currentDisplayableFactory.setRunnable(allBinaryGameCanvas.gameRunnable);
//            
//        } else 
            if (Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD)) {
            //logUtil.put(gameStrings.UNPAUSE, this, gameStrings.UNPAUSE);
            final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();

            currentDisplayableFactory.setRunnable(allBinaryGameCanvas.gameRunnable);
            currentDisplayableFactory.setDisplayable(allBinaryGameCanvas);
            
        }
    }

    //@Override
    public void updateTouch(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.updateTouch2();
    }
    
    // Show/Hide the screen buttons
    //@Override
    public void updateScreenButtonPaintable(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.updateScreenButtonPaintable2();
    }
    
    //@Override
    public void setGameState(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.setGameState();
    }

    //@Override
    public void removeAllGameKeyInputListeners(final AllBinaryGameCanvas allBinaryGameCanvas) {
        allBinaryGameCanvas.removeAllGameKeyInputListeners2();
    }
    
    //@Override
    public void updateEndGameProcessor(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.updateEndGameProcessor2();
    }
    
    //@Override
    public void buildGame(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.buildGame2();
    }
    
    //@Override
    public void run(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.run2();
    }

    //@Override
    public void setHighScore(final AbeClientInformationInterface abeClientInformation, final AllBinaryGameCanvas allBinaryGameCanvas, final String name, final long score, final boolean autoSubmit, final boolean isLast) throws Exception {
        allBinaryGameCanvas.setHighScore2(abeClientInformation, name, score, autoSubmit, isLast);
    }
    
}
