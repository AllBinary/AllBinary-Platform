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

import jsinterop.annotations.JsType;

import org.allbinary.game.GameType;
import org.allbinary.game.state.GameState;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class GameAdStateBase {

    //protected final LogUtil logUtil = LogUtil.getInstance();
    @JsProperty
    protected final AdConfiguration adConfigurationP;
    @JsProperty
    protected final Advertisements advertisementsP = new Advertisements();
    @JsProperty
    protected boolean okayToShowAdsP;

    @JsConstructor
    public GameAdStateBase(final AdConfiguration adConfiguration) {
        this.adConfigurationP = adConfiguration;
    }

    @JsMethod
    public void initViewArray(final Object[] viewArray) {
    }

    @JsMethod
    public void onStart(final Object object) {
        this.init();
    }

    @JsMethod
    public void init() {
    }

    @JsMethod
    public boolean isShowingAtLocation(final int location) {
        return false;
    }

    @JsMethod
    public boolean isShowingAt() {
        return false;
    }

    @JsMethod
    public void show() {
    }

    @JsMethod
    public void hide() {
    }

    @JsMethod
    public void onEvent(final AllBinaryEventObject eventObject) {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    @JsMethod
    public void processAdState(final GameState gameState, final GameType gameType) {
    }

    @JsMethod
    public void processPageAdState() {
    }

    @JsMethod
    public void playingAdState() throws Exception {
        
    }
    
    //Paying players don't need to see the adverts in game
    @JsMethod
    public void playingAdStateWH(final int width, final int height) throws Exception {
    }

    @JsMethod
    public void notPlayingAdState() throws Exception {
    }

    @JsMethod
    public void setGameIsReady(final boolean gameIsReady) {
    }

    @JsMethod
    public boolean isGameIsReady() {
        return false;
    }

    @JsMethod
    public Advertisements getAdvertisements() {
        return this.advertisementsP;
    }

    @JsMethod
    public AdConfiguration getAdConfiguration() {
        return this.adConfigurationP;
    }

    @JsMethod
    public boolean isOkayToShowPageAd() {
        return false;
    }

    @JsMethod
    public void setOkayToShowAds(final boolean okayToShowAds) {
        this.okayToShowAdsP = okayToShowAds;
    }

    @JsMethod
    public boolean isOkayToShowAds() {
        return this.okayToShowAdsP;
    }

    @JsMethod
    public boolean isEnabled() {
        return false;
    }

}
