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
package org.allbinary.game.input;

import javax.microedition.lcdui.Canvas;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.system.security.licensing.LockedFeatureNotificationUtil;
import org.allbinary.logic.system.security.licensing.LockedUtil;
import org.allbinary.canvas.Processor;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.PreGameSelectorPaintable;
import org.allbinary.game.input.event.GameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEventUtil;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;
import org.allbinary.media.graphics.geography.map.racetrack.MultiLevelRaceTrackGeographicMapInterfaceFactoryInterface;
import org.allbinary.thread.ABRunnable;
import org.allbinary.thread.SecondaryThreadPool;
import org.allbinary.time.TimeDelayHelper;

public class MapSelectionGameInputProcessor extends Processor 
    implements GameInputProcessorInterface, PlayerGameInputCompositeInterface
{
    private final AllBinaryGameCanvas gameCanvas;
    private final MultiLevelRaceTrackGeographicMapInterfaceFactoryInterface raceTrackGeographicMapInterfaceFactoryInterface;
    private final PreGameSelectorPaintable preGameSelectorPaintable;
    private final TimeDelayHelper inputTimeHelper = new TimeDelayHelper(650);

    private final PlayerGameInput playerGameInput;

    private final int lockedIndex;
    
    private final ABRunnable abRunnable = new ABRunnable() {
        public void run() {
            try {
                this.setRunning(true);

                SecondaryPlayerQueueFactory.getInstance().add(SelectSound.getInstance());

                // this.gameCanvas.getStartIntermissionInterface().setEnabled(false);
                // this.gameCanvas.getEndLevelIntermissionInterface().setEnabled(true);
                // this.gameCanvas.setGameState(Intermission.WAIT_LEVEL_INTERMISSION_GAME_STATE);
                final int track = preGameSelectorPaintable.getPreGameSelectionForm().getSelectedIndex() + 1;

                final int wave = raceTrackGeographicMapInterfaceFactoryInterface.getFirstWaveWithTrack(track);
                gameCanvas.getLayerManager().getGameInfo().setCurrentLevel(wave);

                gameCanvas.buildGame(false);

                GameKeyEventHandler.getInstance().removeListener(getPlayerGameInput());
                
                this.setRunning(false);

            } catch (Exception e) {
                this.setRunning(false);
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
            }
        }
    };
    
    public MapSelectionGameInputProcessor(
            AllBinaryGameCanvas gameCanvas,
            MultiLevelRaceTrackGeographicMapInterfaceFactoryInterface raceTrackGeographicMapInterfaceFactoryInterface,
            PreGameSelectorPaintable mapSelectorPaintable,
            int lockedIndex)
    {
        this.gameCanvas = gameCanvas;
        this.raceTrackGeographicMapInterfaceFactoryInterface = raceTrackGeographicMapInterfaceFactoryInterface;
        this.preGameSelectorPaintable = mapSelectorPaintable;

        this.playerGameInput = new GameInputProcessorComposite("Map Selection", this);
        
        this.lockedIndex = lockedIndex;
    }

    public void process() throws Exception
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().PROCESS));
        this.getPlayerGameInput().update();
    }

    public void onInput(BasicArrayList list) throws Exception
    {
        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            Object object = list.get(index);
            int key = GameKeyEventUtil.getKey(object);

            if (key == Canvas.LEFT || key == Canvas.RIGHT || key == Canvas.UP || key == Canvas.DOWN)
            {
                if (inputTimeHelper.isTime())
                {

                    SecondaryPlayerQueueFactory.getInstance().add(SelectSound.getInstance());

                    this.preGameSelectorPaintable.getPreGameSelectionForm().processInput(key);

                    break;

                }
            } else if (key == Canvas.KEY_NUM0)
            // if (key == GameKey.KEY_NUM0.getId().intValue())
            {
                int selectedIndex = this.preGameSelectorPaintable.getPreGameSelectionForm().getSelectedIndex();
                
                if(selectedIndex < this.lockedIndex || !LockedUtil.getInstance().isLockedFeature())
                {
                    //PreLogUtil.put("selectedIndex: " + selectedIndex + " LockedUtil.getInstance().isLockedFeature(): " + LockedUtil.getInstance().isLockedFeature(), this, "onInput");

                    if (!abRunnable.isRunning()) {
                        abRunnable.setRunning(true);
                        //SecondaryThreadPool.getInstance().runTask(abRunnable);
                        Thread thread = new Thread(abRunnable);
                        thread.start();
                    }
                    
                    break;
                }
                else
                {
                    LockedFeatureNotificationUtil.getInstance().fire();
                }
            }

            list.clear();
        }
    }

    public void processInput(AllBinaryLayerManager layerManager) throws Exception
    {
        
    }
    
    public void initInputProcessors()
    {
        
    }
    
    public String getName()
    {
        return this.toString();
    }
    
    public PlayerGameInput getPlayerGameInput()
    {
        return playerGameInput;
    }
}
