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
package org.allbinary.game.layer;

import org.allbinary.game.combat.destroy.event.DestroyedEvent;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.game.input.form.NullRTSFormInputFactory;
import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.layer.capital.Capital;
import org.allbinary.game.layer.hud.basic.notification.GameNotificationHud;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.media.audio.Sound;

/**
 *
 * @author User
 */
public class NullRTSLayer extends NullPathFindingLayer
    implements RTSPlayerLayerInterface {

    public static final NullRTSLayer NULL_RTS_LAYER = new NullRTSLayer();

    @Override
    public String getName() {
        throw new RuntimeException();
    }

    @Override
    public void processInput(AllBinaryLayerManager layerManager) throws Exception {
        throw new RuntimeException();
    }

    @Override
    public void initInputProcessors() {
        throw new RuntimeException();
    }

    @Override
    public GameNotificationHud getGameNotificationHud() {
        throw new RuntimeException();
    }

    @Override
    public ScrollSelectionForm getCurrentScrollSelectionForm() {
        throw new RuntimeException();
    }

    @Override
    public void setCurrentScrollSelectionForm(ScrollSelectionForm currentScrollSelectionForm) {
        throw new RuntimeException();
    }

    @Override
    public ScrollSelectionForm getBuildingScrollSelectionForm() {
        throw new RuntimeException();
    }

    @Override
    public PlayerGameInput getPlayerGameInput() {
        throw new RuntimeException();
    }

    @Override
    public void onEvent(AllBinaryEventObject eventObject) {
        throw new RuntimeException();
    }

    @Override
    public void onDestroyed(DestroyedEvent destroyedEvent)
        throws Exception {
        throw new RuntimeException();
    }

    @Override
    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
        throws Exception {
        throw new RuntimeException();
    }

    @Override
    public Capital getCapital() {
        throw new RuntimeException();
    }

    @Override
    public RTSFormInput getRTSFormInput() {
        return NullRTSFormInputFactory.getInstance();
    }

    @Override
    public void add(Sound sound) {
        throw new RuntimeException();
    }

    @Override
    public PlayerOwnedRTSLayers getPlayerOwnedRTSLayers() {
        throw new RuntimeException();
    }

    @Override
    public boolean implmentsArtificialIntelligenceCompositeInterface() {
        throw new RuntimeException();
    }

}
