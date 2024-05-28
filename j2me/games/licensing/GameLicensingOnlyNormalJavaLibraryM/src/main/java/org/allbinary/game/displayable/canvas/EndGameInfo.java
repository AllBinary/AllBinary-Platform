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

import org.allbinary.game.GameInfo;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.string.StringUtil;

public class EndGameInfo {
    
    private final String NORMAL_GAME_OVER = "Game Over";
    protected final String NORMAL_GAME_OVER_WIN = "You Won";

    //Initial value is the UNKNOWN state. That is if it is a license served 
    //game then it has not obtained info from the server yet.
    private String gameOverStr = StringUtil.getInstance().EMPTY_STRING;
    private String winStr = this.gameOverStr;

    public EndGameInfo() {
        
    }
        
    public void update(final GameInfo gameInfo, final MyCanvas myCanvas)
    {
        this.setGameOverStr(this.NORMAL_GAME_OVER);
        this.setWinStr(this.NORMAL_GAME_OVER_WIN);
    }

    /**
     * @return the gameOverStr
     */
    public String getGameOverStr()
    {
        return gameOverStr;
    }

    /**
     * @param gameOverStr the gameOverStr to set
     */
    public void setGameOverStr(String gameOverStr)
    {
        this.gameOverStr = gameOverStr;
    }

    /**
     * @return the winStr
     */
    public String getWinStr()
    {
        return winStr;
    }

    /**
     * @param winStr the winStr to set
     */
    public void setWinStr(String winStr)
    {
        this.winStr = winStr;
    }

}
