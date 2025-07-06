/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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

import org.allbinary.game.GameType;
import org.allbinary.graphics.color.BasicColor;


/**
 *
 * @author user
 */
public interface AdvancedRTSPlayerLayerInterface
extends RTSPlayerLayerInterface
{
    AdvancedPlayerOwnedRTSLayers getAdvancedPlayerOwnedRTSLayers();
    boolean isLocalPlayer();
    //Not really a local player but all should show
    GameType getGameType();
    
    BasicColor getDecalBasicColor();
}
