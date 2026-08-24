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
import jsinterop.annotations.JsProperty;


@JsType
public class EndGameInfo {
    
    //private final String NORMAL_GAME_OVER = "Game Over";
    @JsProperty
    protected final String NORMAL_GAME_OVER_WIN = "You Won";

    private final String TEASER_DEMO_GAME_END_LAST_LEVEL = "End of Demo.";
    private final String TEASER_DEMO_GAME_END = "Demo Game Over.";

    //Initial value is the UNKNOWN state. That is if it is a license served 
    //game then it has not obtained info from the server yet.
    private String gameOverStr = StringUtil.getInstance().EMPTY_STRING;
    private String winStr = this.gameOverStr;

    @JsMethod
    public final void update(final GameInfo gameInfo, final MyCanvas myCanvas)
    {
        if (gameInfo.isLastLevel()) {
            this.setWinStr(this.TEASER_DEMO_GAME_END_LAST_LEVEL);
            this.setGameOverStr(this.TEASER_DEMO_GAME_END_LAST_LEVEL);
        } else {
            this.setWinStr(this.NORMAL_GAME_OVER_WIN);
            this.setGameOverStr(this.TEASER_DEMO_GAME_END);
        }
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
