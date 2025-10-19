/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.layer.AllBinaryGameLayerManager;

/**
 *
 * @author User
 */
public class NullAllBinaryGameCanvas implements AllBinaryGameCanvasInterface {

    public static final NullAllBinaryGameCanvas NULL_ALLBINARY_GAME_CANVAS = new NullAllBinaryGameCanvas();

    @Override    
    public AllBinaryGameLayerManager getLayerManager() {
        throw new RuntimeException();
    }

    @Override
    public void addCommand(Command cmd) {
        throw new RuntimeException();
    }

    @Override
    public int getHeight() {
        throw new RuntimeException();
    }

    @Override
    public int getWidth() {
        throw new RuntimeException();
    }

    @Override
    public void removeCommand(Command cmd) {
        throw new RuntimeException();
    }

    @Override
    public void setCommandListener(CommandListener l) {
        throw new RuntimeException();
    }

}
