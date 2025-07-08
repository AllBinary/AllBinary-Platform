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

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.game.state.GameState;
import org.allbinary.input.event.VirtualKeyboardEvent;
import org.allbinary.input.event.VirtualKeyboardEventHandler;
import org.allbinary.input.event.VirtualKeyboardEventListenerInterface;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class GameAdState implements VirtualKeyboardEventListenerInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final AdConfiguration adConfiguration;
    
    private final Advertisements advertisements = new Advertisements();

    public GameAdState(AdConfiguration adConfiguration)
    {        
        this.adConfiguration = adConfiguration;

        VirtualKeyboardEventHandler.getInstance().addListener(this);
    }

    public void initViewArray(Object[] viewArray)
    {

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
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
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
    
    public boolean isEnabled()
    {
    	return false;
    }
}
