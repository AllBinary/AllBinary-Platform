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

import java.util.Hashtable;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;

import org.allbinary.game.state.GameState;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class NullGameCanvasRunnable implements GameCanvasRunnableInterface {

    public static final NullGameCanvasRunnable NULL_GAME_CANVAS_RUNNABLE = new NullGameCanvasRunnable();
    
    @Override
    public String getTitle() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public boolean isPaused() {
        return false;
    }

    @Override
    public void unPause() {

    }

    @Override
    public void pause() {

    }

    @Override
    public boolean isHighScoreSubmitted() {
        return false;
    }

    @Override
    public void setHighScoreSubmitted(boolean highScoreSubmitted) throws Exception {

    }

    @Override
    public void setGameOver() throws Exception {

    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void initCommands(CommandListener cmdListener) {

    }

    @Override
    public Hashtable getCurrentStateHashtable() throws Exception {
        return NullUtil.getInstance().NULL_TABLE;
    }

    @Override
    public Hashtable getLoadStateHashtable() throws Exception {
        return NullUtil.getInstance().NULL_TABLE;
    }

    @Override
    public void setLoadStateHashtable(Hashtable hashtable) throws Exception {

    }

    @Override
    public int getType() {
        return -1;
    }

    @Override
    public void setGameState(GameState gameState) throws Exception {

    }

    @Override
    public GameState getGameState() {
        return GameState.NO_GAME_STATE;
    }

    @Override
    public void itemStateChanged(Item item) {

    }

    @Override
    public void paint(Graphics graphics) {

    }

    @Override
    public void paintThreed(Graphics graphics) {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void setRunning(boolean isRunning) {

    }

    @Override
    public void setThread(Thread thread) throws Exception {

    }

    @Override
    public void run() {

    }

}
