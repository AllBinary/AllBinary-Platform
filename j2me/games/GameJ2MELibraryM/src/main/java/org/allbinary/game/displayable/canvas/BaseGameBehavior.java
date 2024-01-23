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
import org.allbinary.game.configuration.feature.TouchFeatureFactory;
import org.allbinary.graphics.opengles.CurrentDisplayableFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.PreLogUtil;

/**
 *
 * @author User
 */
public class BaseGameBehavior extends DemoGameBehavior {

    private static final BaseGameBehavior instance = new BaseGameBehavior();

    /**
     * @return the instance
     */
    public static DemoGameBehavior getInstance() {
        return instance;
    }

    public void init() throws Exception {
        final GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();

        gameAdState.playingAdState();
    }

    public void pause(final AllBinaryGameCanvas allBinaryGameCanvas) {
        
        final Features features = Features.getInstance();

        if (features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD) 
                //|| features.isDefault(HTMLFeatureFactory.getInstance().HTML)
                ) {
            //LogUtil.put(LogFactory.getInstance("pause", this, METHOD_NAME));
            final GameCanvasPauseRunnable gameRunnable = new GameCanvasPauseRunnable(allBinaryGameCanvas);

            final CurrentDisplayableFactory currentDisplayableFactory
                    = CurrentDisplayableFactory.getInstance();

            currentDisplayableFactory.setRunnable(gameRunnable);
        }

    }

    public void unPause(final AllBinaryGameCanvas allBinaryGameCanvas) {

        if (Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD)) {
            //LogUtil.put(LogFactory.getInstance("unPause", this, "unPause"));
            final GameCanvasRunnable gameRunnable = new GameCanvasRunnable(allBinaryGameCanvas);
            final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();

            currentDisplayableFactory.setRunnable(gameRunnable);
        }
    }

    public void updateTouch(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.updateTouch2();
    }
    
    // Show/Hide the screen buttons
    public void updateScreenButtonPaintable(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.updateScreenButtonPaintable2();
    }
    
    public void setGameState(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.setGameState();
    }

    public void removeAllGameKeyInputListeners(final AllBinaryGameCanvas allBinaryGameCanvas) {
        allBinaryGameCanvas.removeAllGameKeyInputListeners2();
    }
    
    public void updateEndGameProcessor(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.updateEndGameProcessor2();
    }
    
    public void buildGame(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.buildGame2();
    }
    
    public void run(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.run2();
    }
    
    public void setHighScore(final AllBinaryGameCanvas allBinaryGameCanvas, final String name, final long score, final boolean autoSubmit, final boolean isLast) throws Exception {
        allBinaryGameCanvas.setHighScore2(name, score, autoSubmit, isLast);
    }
    
}
