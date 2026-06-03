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
package org.allbinary.game.layer.building;

import java.util.Hashtable;

import org.allbinary.game.combat.damage.DamageFloaters;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.game.tracking.TrackingEventHandler;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.NullUtil;

/**
 *
 * @author User
 */
public class SimulatedBuildingPropertiesFactory extends BuildingPropertiesFactory {

    private static final SimulatedBuildingPropertiesFactory instanceC = new SimulatedBuildingPropertiesFactory();
    
    /**
     * @return the instance
     */
    public static SimulatedBuildingPropertiesFactory getInstance() {
        return SimulatedBuildingPropertiesFactory.instanceC;
    }
    
    @Override
    public int getEfficiencyPerLevel(final BuildingLayer buildingLayer) {
        return 0;
    }
        
    @Override
    public DamageFloaters getDamageFloaters(final BuildingLayer buildingLayer) {
        final DamageFloaters damageFloaters = DamageFloaters.getInstance();
        this.damageFloatersPaintableInterface = damageFloaters;
        return damageFloaters;
    }

    @Override
    public Paintable getHealthBar(final BuildingLayer buildingLayer) throws Exception {
        return NullPaintable.getInstance();
    }
    
    @Override
    public Hashtable getHashtable() {
        return NullUtil.getInstance().NULL_TABLE;
    }    
        
    @Override
    public TrackingEvent getTrackingEvent(final BuildingLayer buildingLayer) {
        return new TrackingEvent(TrackingEventHandler.getInstance());
    }
    
}
