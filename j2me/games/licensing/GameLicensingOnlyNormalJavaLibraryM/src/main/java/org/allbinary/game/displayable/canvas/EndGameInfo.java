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

import org.allbinary.game.GameInfo;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class EndGameInfo {
    
    private final String NORMAL_GAME_OVER = "Game Over";
    @JsProperty
    protected final String NORMAL_GAME_OVER_WIN = "You Won";

    //Initial value is the UNKNOWN state. That is if it is a license served 
    //game then it has not obtained info from the server yet.
    private String gameOverStr = StringUtil.getInstance().EMPTY_STRING;
    private String winStr = this.gameOverStr;

    @JsConstructor
    public EndGameInfo() {
        
    }
        
    @JsMethod
    public void update(final GameInfo gameInfo, final MyCanvas myCanvas)
    {
        this.setGameOverStr(this.NORMAL_GAME_OVER);
        this.setWinStr(this.NORMAL_GAME_OVER_WIN);
    }

    /**
     * @return the gameOverStr
     */
    @JsMethod
    public String getGameOverStr()
    {
        return this.gameOverStr;
    }

    /**
     * @param gameOverStr the gameOverStr to set
     */
    @JsMethod
    public void setGameOverStr(String gameOverStr)
    {
        this.gameOverStr = gameOverStr;
    }

    /**
     * @return the winStr
     */
    @JsMethod
    public String getWinStr()
    {
        return this.winStr;
    }

    /**
     * @param winStr the winStr to set
     */
    @JsMethod
    public void setWinStr(String winStr)
    {
        this.winStr = winStr;
    }

}
