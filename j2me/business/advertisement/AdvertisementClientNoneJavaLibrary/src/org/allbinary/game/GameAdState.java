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
package org.allbinary.game;

import org.allbinary.business.advertisement.AdConfiguration;
import org.allbinary.business.advertisement.Advertisements;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.game.GameType;
import allbinary.game.state.GameState;
import allbinary.input.event.VirtualKeyboardEvent;
import allbinary.input.event.VirtualKeyboardEventHandler;
import allbinary.input.event.VirtualKeyboardEventListenerInterface;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GameAdState implements VirtualKeyboardEventListenerInterface
{
    private final AdConfiguration adConfiguration;
    
    private final Advertisements advertisements = new Advertisements();

    public GameAdState(AdConfiguration adConfiguration)
    {        
        this.adConfiguration = adConfiguration;

        VirtualKeyboardEventHandler.getInstance().addListener(this);
    }

    public void init(Object object)
    {
        this.init();
    }
    
    public void init()
    {        
    }

    public boolean isShowingAt()
    {
        return false;
    }

    public boolean isShowingAt(int location)
    {
        return false;
    }
    
    public void show()
    {
    }

    public void hide()
    {
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }
    
    public void onVirtualKeyboardEvent(VirtualKeyboardEvent virtualKeyboardEvent) 
    throws Exception
    {
    }
    
    public void processAdState(GameState gameState, GameType gameType)
    {
    }

    public void processPageAdState()
    {
    }
    
    //Paying players don't need to see the adverts in game
    public void playingAdState()
    throws Exception
    {
    }
    
    public void notPlayingAdState() throws Exception
    {        
    }

    public void setGameIsReady(boolean gameIsReady)
    {
    }

    public boolean isGameIsReady()
    {
        return false;
    }

    public Advertisements getAdvertisements()
    {
        return advertisements;
    }

    public AdConfiguration getAdConfiguration()
    {
        return adConfiguration;
    }
    
    private boolean okayToShowAds;
    
    public boolean isOkayToShowPageAd()
    {
        return false;
    }
    
    public void setOkayToShowAds(boolean okayToShowAds)
    {
        this.okayToShowAds = okayToShowAds;
    }

    public boolean isOkayToShowAds()
    {
        return okayToShowAds;
    }
}
