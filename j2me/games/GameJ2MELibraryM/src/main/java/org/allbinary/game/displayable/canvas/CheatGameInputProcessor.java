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

import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventUtil;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.special.SpecialGameInputFactory;
import org.allbinary.game.layer.special.SpecialGameInputInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

public class CheatGameInputProcessor extends PlayerGameInput 
{
    private final String PROCESS_GAME = "processGame";

    private final AllBinaryGameCanvas gameCanvas;
    public static SpecialGameInputInterface inputProcessor = SpecialGameInputFactory.NO_SPECIAL_GAME_INPUT;

    public CheatGameInputProcessor(AllBinaryGameCanvas gameCanvas)
    {
        super(new BasicArrayList(), -1);

        this.gameCanvas = gameCanvas;
    }

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
    
//    public static int x;
//    public static int y;
//    public static int z;

    @Override    
    public synchronized void update()
    {
        try
        {
            final AllBinaryGameLayerManager gameLayerManager = this.gameCanvas.getLayerManager();

            final BasicArrayList list = this.getGameKeyEventList();

            final int size = list.size();
            for (int index = 0; index < size; index++)
            {
                final Object object = list.objectArray[index];
                final int key = GameKeyEventUtil.getKey(object);

                if (key == gameKeyFactory.LEVEL_DOWN.getId())
                {
                    inputProcessor.strafeLeft();
                    
                    gameLayerManager.getGameInfo().previousGameLevel();
                    logUtil.put(new StringMaker().append("Down Level Cheat: ").append(gameLayerManager.getGameInfo().getCurrentLevel()).toString(),this, PROCESS_GAME);
                    this.gameCanvas.buildGame(true);
                    break;
                }
                else if (key == gameKeyFactory.LEVEL_UP.getId())
                {
                    inputProcessor.strafeRight();
                    
                    gameLayerManager.getGameInfo().nextGameLevel();
                    logUtil.put(new StringMaker().append("Up Level Cheat: ").append(gameLayerManager.getGameInfo().getCurrentLevel()).toString(),this, PROCESS_GAME);
                    this.gameCanvas.buildGame(true);
                    break;
                }
                
                if (key == gameKeyFactory.LEFT.getId())
                {   
                    inputProcessor.left();
//                    x+=1;
//                    logUtil.put(new StringMaker().append("x: ").append(x).toString(),this, PROCESS_GAME);
                    break;
                }
                else if (key == gameKeyFactory.RIGHT.getId())
                {   
                    inputProcessor.right();
//                    x-=1;
//                    logUtil.put(new StringMaker().append("x: ").append(x).toString(),this, PROCESS_GAME);
                    break;
                }
                else if (key == gameKeyFactory.UP.getId())
                {
                    inputProcessor.up();
//                    y+=1;
//                    logUtil.put(new StringMaker().append("y: ").append(y).toString(),this, PROCESS_GAME);   
                    break;
                }
                else if (key == gameKeyFactory.DOWN.getId())
                {
                    inputProcessor.down();
//                    y-=1;
//                    logUtil.put(new StringMaker().append("y: ").append(y).toString(),this, PROCESS_GAME);
                    break;
                }
                //3
                else if (key == gameKeyFactory.KEY_NUM1.getId())
                {
                    inputProcessor.special1(gameLayerManager, GameKeyEvent.NONE);
//                    z+=1;
//                    logUtil.put(new StringMaker().append("z: ").append(z).toString(),this, PROCESS_GAME);
                    break;
                }
                //4
                else if (key == gameKeyFactory.KEY_NUM3.getId())
                {
                    inputProcessor.special2(gameLayerManager, GameKeyEvent.NONE);
//                    z-=1;
//                    logUtil.put(new StringMaker().append("z: ").append(z).toString(),this, PROCESS_GAME);
                    break;
                }
                
            }

            list.clear();
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e);
        }
    }

}
