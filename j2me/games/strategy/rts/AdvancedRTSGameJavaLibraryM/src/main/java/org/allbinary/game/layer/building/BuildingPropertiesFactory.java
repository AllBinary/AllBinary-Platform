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
import org.allbinary.game.combat.damage.PtsDamageFloaters;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.game.health.HealthBar;
import org.allbinary.game.health.HealthBarTwodAnimation;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.layer.AllBinaryLayer;

/**
 *
 * @author User
 */
public class BuildingPropertiesFactory {

    private static final BuildingPropertiesFactory instance = new BuildingPropertiesFactory();
    
    /**
     * @return the instance
     */
    public static BuildingPropertiesFactory getInstance() {
        return BuildingPropertiesFactory.instance;
    }

    public int getEfficiencyPerLevel(final BuildingLayer buildingLayer) {
        return 10000 / buildingLayer.getMaxLevel() + 10000 % buildingLayer.getMaxLevel();
    }
    
    public Paintable damageFloatersPaintableInterface = NullPaintable.getInstance();

    public DamageFloaters getDamageFloaters(final BuildingLayer buildingLayer) {
        DamageFloaters damageFloaters = DamageFloaters.getInstance();
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().DAMAGE_FLOATERS)) {
            damageFloaters = new PtsDamageFloaters(buildingLayer);
            damageFloatersPaintableInterface = damageFloaters;
        } else {
            damageFloaters = new DamageFloaters();
        }
        return damageFloaters;
    }

    public Paintable getHealthBar(final BuildingLayer buildingLayer) throws Exception {
        Paintable healthBar = NullPaintable.getInstance();
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().HEALTH_BARS))
        {
            healthBar = new HealthBar(buildingLayer, buildingLayer.getHealthInterface(),
                new HealthBarTwodAnimation((AllBinaryLayer) buildingLayer, BasicHudFactory.getInstance().BOTTOMLEFT), -1);
        }
        return healthBar;
    }

    public Hashtable getHashtable() {
        return new Hashtable();
    }    
    
    public TrackingEvent getTrackingEvent(final BuildingLayer buildingLayer) {
        return new TrackingEvent(buildingLayer);
    }
    
}
