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

import org.allbinary.game.GameTypeFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class DemoGameBehavior {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final DemoGameBehavior instance = new DemoGameBehavior();

    /**
     * @return the instance
     */
    public static DemoGameBehavior getInstance() {
        return instance;
    }

    public void init() throws Exception {
    
    }

    public void pause(final AllBinaryGameCanvas allBinaryGameCanvas) {
        
    }
    
    public void unPause(final AllBinaryGameCanvas allBinaryGameCanvas) {
        
    }

    public void updateTouch(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        
    }
    
    // Show/Hide the screen buttons
    public void updateScreenButtonPaintable(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.setTouchPaintable(NullPaintable.getInstance());
    }
    
    public void setGameState(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {

    }

    public void removeAllGameKeyInputListeners(final AllBinaryGameCanvas allBinaryGameCanvas) {
        
    }
    
    public void updateEndGameProcessor(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        
    }
    
    public void buildGame(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        
    }

    //Don't keep running thread if in bot/demo mode
    public void run(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(GameTypeFactory.getInstance().BOT.toString(), this, commonStrings.RUN);
    }
    
    public void setHighScore(final AbeClientInformationInterface abeClientInformation, final AllBinaryGameCanvas allBinaryGameCanvas, final String name, final long score, final boolean autoSubmit, final boolean isLast) throws Exception {

    }
    
}
