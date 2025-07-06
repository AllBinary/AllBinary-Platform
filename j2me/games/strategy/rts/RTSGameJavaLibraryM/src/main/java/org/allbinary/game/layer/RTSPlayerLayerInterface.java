/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

import org.allbinary.game.input.BuildMenuCompositeInterface;
import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.layer.capital.Capital;

import org.allbinary.game.combat.destroy.event.DestroyedEventListenerInterface;
import org.allbinary.game.input.PlayerGameInputCompositeInterface;
import org.allbinary.game.layer.hud.basic.notification.GameNotificationHudCompositeInterface;
import org.allbinary.game.tick.TickableInterface;
import org.allbinary.media.audio.Sound;

public interface RTSPlayerLayerInterface
extends GameNotificationHudCompositeInterface,
   BuildMenuCompositeInterface,
   PlayerGameInputCompositeInterface,
   DestroyedEventListenerInterface,
   TickableInterface,
   PathFindingLayerInterface
{
    Capital getCapital();

    RTSFormInput getRTSFormInput();

    void add(Sound sound);

    PlayerOwnedRTSLayers getPlayerOwnedRTSLayers();
    
    boolean implmentsArtificialIntelligenceCompositeInterface();
}
