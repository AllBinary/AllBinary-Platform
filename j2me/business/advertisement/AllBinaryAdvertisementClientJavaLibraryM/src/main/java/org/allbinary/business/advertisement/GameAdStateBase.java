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
package org.allbinary.business.advertisement;

import org.allbinary.game.GameType;
import org.allbinary.game.state.GameState;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

/**
 *
 * @author User
 */
public class GameAdStateBase {

    //protected final LogUtil logUtil = LogUtil.getInstance();
    protected final AdConfiguration adConfigurationP;
    protected final Advertisements advertisementsP = new Advertisements();
    protected boolean okayToShowAdsP;

    public GameAdStateBase(final AdConfiguration adConfiguration) {
        this.adConfigurationP = adConfiguration;
    }

    public void initViewArray(final Object[] viewArray) {
    }

    public void onStart(final Object object) {
        this.init();
    }

    public void init() {
    }

    public boolean isShowingAtLocation(final int location) {
        return false;
    }

    public boolean isShowingAt() {
        return false;
    }

    public void show() {
    }

    public void hide() {
    }

    public void onEvent(final AllBinaryEventObject eventObject) {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    public void processAdState(final GameState gameState, final GameType gameType) {
    }

    public void processPageAdState() {
    }

    public void playingAdState() throws Exception {
        
    }
    
    //Paying players don't need to see the adverts in game
    public void playingAdStateWH(final int width, final int height) throws Exception {
    }

    public void notPlayingAdState() throws Exception {
    }

    public void setGameIsReady(final boolean gameIsReady) {
    }

    public boolean isGameIsReady() {
        return false;
    }

    public Advertisements getAdvertisements() {
        return this.advertisementsP;
    }

    public AdConfiguration getAdConfiguration() {
        return this.adConfigurationP;
    }

    public boolean isOkayToShowPageAd() {
        return false;
    }

    public void setOkayToShowAds(final boolean okayToShowAds) {
        this.okayToShowAdsP = okayToShowAds;
    }

    public boolean isOkayToShowAds() {
        return this.okayToShowAdsP;
    }

    public boolean isEnabled() {
        return false;
    }

}
