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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.game.input.event.GameKeyEventUtil;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.logic.string.StringMaker;

public class CheatGameInputProcessor extends PlayerGameInput 
{
    private final AllBinaryGameCanvas gameCanvas;

    public CheatGameInputProcessor(AllBinaryGameCanvas gameCanvas)
    {
        super(new BasicArrayList(), -1);

        this.gameCanvas = gameCanvas;
    }

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
    
    public synchronized void update()
    {
        try
        {
            AllBinaryGameLayerManager gameLayerManager = this.gameCanvas
                    .getLayerManager();

            BasicArrayList list = this.getGameKeyEventList();

            int size = list.size();
            for (int index = 0; index < size; index++)
            {
                Object object = list.objectArray[index];
                int key = GameKeyEventUtil.getKey(object);

                if (key == gameKeyFactory.LEVEL_DOWN.getId())
                {
                    gameLayerManager.getGameInfo().previousGameLevel();
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append("Down Level Cheat: ").append(gameLayerManager.getGameInfo().getCurrentLevel()).toString(),
                            this, "processGame"));
                    this.gameCanvas.buildGame(true);
                    break;
                }
                else if (key == gameKeyFactory.LEVEL_UP.getId())
                {
                    gameLayerManager.getGameInfo().nextGameLevel();
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append("Up Level Cheat: ").append(gameLayerManager.getGameInfo().getCurrentLevel()).toString(),
                            this, "processGame"));
                    this.gameCanvas.buildGame(true);
                    break;
                }
            }

            list.clear();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e));
        }
    }

}
