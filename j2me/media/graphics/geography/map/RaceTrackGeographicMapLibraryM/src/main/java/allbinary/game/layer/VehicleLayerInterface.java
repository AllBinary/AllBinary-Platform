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
package allbinary.game.layer;

import org.allbinary.util.BasicArrayList;

import allbinary.animation.RotationAnimation;
import allbinary.layer.LayerInterface;
import allbinary.logic.math.BasicDecimal;
import allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import allbinary.media.graphics.geography.map.GeographicMapCellPositionTracking;

public interface VehicleLayerInterface extends LayerInterface, VehiclePropertiesCompositeInterface
{    
    BasicArrayList getGameKeyEventList();

    BasicDecimal getSpeedBasicDecimal();

    boolean isReadyForExplosion();
    
    RotationAnimation getRotationAnimationInterface();

    void setRotationAnimationInterface(RotationAnimation animationInterface);

    boolean isDestroyed();
    
    boolean isFinish();

    int getFinalPosition();
    
    void handleFinish() throws Exception;
    
    GeographicMapCellPositionTracking getGeographicMapCellPositionTracking();

    GeographicMapCellHistory[] getGeographicMapCellHistoryArray();

    void setGeographicMapCellHistoryArray(
            GeographicMapCellHistory[] geographicMapCellHistory);
}