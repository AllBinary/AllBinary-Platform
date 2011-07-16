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
package allbinary.game.displayable.canvas;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.PlayerGameInput;
import allbinary.game.input.event.GameKeyEventUtil;
import allbinary.game.layer.AllBinaryGameLayerManager;

public class CheatGameInputProcessor extends PlayerGameInput 
{
    private final AllBinaryGameCanvas gameCanvas;

    public CheatGameInputProcessor(AllBinaryGameCanvas gameCanvas)
    {
        super(new BasicArrayList());

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
                Object object = list.get(index);
                int key = GameKeyEventUtil.getKey(object);

                if (key == gameKeyFactory.LEVEL_DOWN.getId())
                {
                    gameLayerManager.getGameInfo().previousGameLevel();
                    LogUtil.put(LogFactory.getInstance("Down Level Cheat: "
                            + gameLayerManager.getGameInfo().getCurrentLevel(),
                            this, "processGame"));
                    this.gameCanvas.buildGame(true);
                    break;
                }
                else if (key == gameKeyFactory.LEVEL_UP.getId())
                {
                    gameLayerManager.getGameInfo().nextGameLevel();
                    LogUtil.put(LogFactory.getInstance("Up Level Cheat: "
                            + gameLayerManager.getGameInfo().getCurrentLevel(),
                            this, "processGame"));
                    this.gameCanvas.buildGame(true);
                    break;
                }
            }

            list.clear();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }

}
