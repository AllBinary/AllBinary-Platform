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

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.part.weapon.BasicWeaponPart;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.racetrack.BaseRaceTrackGeographicMap;

public class RTSLayerUtil
{
    private static final RTSLayerUtil instance = new RTSLayerUtil();
    
    public static RTSLayerUtil getInstance()
    {
        return instance;
    }

    private WeaponProperties createWeaponProperties(
            final WeaponProperties weaponProperties,
            final int currentLevel, final int lastLevel)
    {
        long reloadTime = weaponProperties.getReloadTime();

        if (lastLevel > currentLevel)
        {
            reloadTime = reloadTime + 50;
        } else if (lastLevel < currentLevel)
        {
            reloadTime = reloadTime - 50;
        }

        final WeaponProperties newWeaponProperties = new WeaponProperties(
                reloadTime, 
                weaponProperties.getTargetingTime(),
                //weaponProperties.getSpeed().getUnscaled() / lastLevel * currentLevel,
                (int) weaponProperties.getSpeed().getUnscaled(), 
                weaponProperties.getDamage() / lastLevel * currentLevel, 
                weaponProperties.getDissipation()
                );

        //LogUtil.put(LogFactory.getInstance(
          //      "WeaponProperties: ").append(newWeaponProperties, 
            //    this, "createWeaponProperties"));

        return newWeaponProperties;
    }

    public void downgrade(final RTSLayer rtsLayer)
    {
        rtsLayer.setLevel(rtsLayer.getLevel() - 1);

        BasicWeaponPart partInterface;
        for (int index = rtsLayer.getPartInterfaceArray().length; --index >= 0;)
        {
            partInterface = (BasicWeaponPart) rtsLayer.getPartInterfaceArray()[index];

            partInterface.setWeaponProperties(
                    this.createWeaponProperties(partInterface
                    .getWeaponProperties(), rtsLayer.getLevel(), rtsLayer.getLevel() + 1));
        }
        rtsLayer.select();
    }

    public void upgrade(final RTSLayer rtsLayer)
    {
        rtsLayer.setLevel(rtsLayer.getLevel() + 1);

        BasicWeaponPart partInterface;
        for (int index = rtsLayer.getPartInterfaceArray().length; --index >= 0;)
        {
            partInterface = (BasicWeaponPart) rtsLayer.getPartInterfaceArray()[index];

            partInterface.setWeaponProperties(
                    this.createWeaponProperties(partInterface
                    .getWeaponProperties(), rtsLayer.getLevel(), rtsLayer.getLevel() - 1));
        }
        rtsLayer.select();
    }
    
    private final long MAX_RELOAD_TIME = Integer.MAX_VALUE / 100000;
    //1000 = 1000 10000 = 100000
    public int getCostExponential(final long cost)
    {
        return (int) ((cost * cost * cost) / (cost * 1000));
    }

    private int getWeaponPropertiesCost(final WeaponProperties weaponProperties)
    {
        //LogUtil.put(LogFactory.getInstance(
          //      "Damage: ").append(weaponProperties.getDamage() +
            //    "+Range: ").append(weaponProperties.getRange() +
              //  "+Reload: ").append((MAX_RELOAD_TIME / weaponProperties.getReloadTime()), 
                //this, "getWeaponPropertiesCost"));

        //I artificially reduce the cost of reload time since they miss a bunch
        int cost = (int) (weaponProperties.getDamage() + weaponProperties.getRange() + 
                ((MAX_RELOAD_TIME / weaponProperties.getReloadTime()) >> 1));

        //LogUtil.put(LogFactory.getInstance("Pre Cost: ").append(cost, this, "getWeaponPropertiesCost"));
        cost = this.getCostExponential(cost);
        //LogUtil.put(LogFactory.getInstance("Cost: ").append(cost, this, "getWeaponPropertiesCost"));
        return cost;
    }
    
    public int getCost(RTSLayer rtsLayer)
    {
        int total = 0;

        BasicWeaponPart partInterface;
        WeaponProperties weaponProperties;
        for (int index = rtsLayer.getPartInterfaceArray().length - 1; index >= 0; index--)
        {
            partInterface = (BasicWeaponPart) rtsLayer.getPartInterfaceArray()[index];
            weaponProperties = partInterface.getWeaponProperties();
            total += this.getWeaponPropertiesCost(weaponProperties);
        }

        return total;
    }

    public int getDowngradeCost(final RTSLayer rtsLayer)
    {
        int downgradeCost = 0;
        BasicWeaponPart partInterface;
        WeaponProperties weaponProperties;
        int downgradeWeaponCost;
        int currentWeaponCost;
        for (int index = rtsLayer.getPartInterfaceArray().length - 1; index >= 0; index--)
        {
            partInterface =
                (BasicWeaponPart) rtsLayer.getPartInterfaceArray()[index];
            weaponProperties = partInterface.getWeaponProperties();

            downgradeWeaponCost =
                this.getWeaponPropertiesCost(
                    this.createWeaponProperties(
                            weaponProperties, 
                            rtsLayer.getLevel() - 1, rtsLayer.getLevel()));

            currentWeaponCost = 
                this.getWeaponPropertiesCost(weaponProperties);
                    
            downgradeCost += (currentWeaponCost - downgradeWeaponCost);
             
            //LogUtil.put(LogFactory.getInstance(
              //      "DowngradedWeapon Cost: ").append(downgradeWeaponCost +
                //    " CurrentWeapon Cost: ").append(currentWeaponCost +
                  //  " Part Cost: ").append(downgradeCost, this, "getDowngradeCost"));
        }

        downgradeCost = downgradeCost * 9 / 10;
        
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Total Cost: ").append(downgradeCost).toString(), this, "getDowngradeCost"));

        return downgradeCost;
    }

    public int getUpgradeCost(final RTSLayer rtsLayer)
    {
        int upgradeCost = 0;

        BasicWeaponPart partInterface;
        WeaponProperties weaponProperties;
        int upgradedWeaponCost;
        int currentWeaponCost;        
        for (int index = rtsLayer.getPartInterfaceArray().length - 1; index >= 0; index--)
        {
            partInterface =
                (BasicWeaponPart) rtsLayer.getPartInterfaceArray()[index];

            weaponProperties = partInterface.getWeaponProperties();

            upgradedWeaponCost = 
                this.getWeaponPropertiesCost(
                    this.createWeaponProperties(
                            weaponProperties, 
                            rtsLayer.getLevel() + 1, rtsLayer.getLevel()));
            
            currentWeaponCost = 
                this.getWeaponPropertiesCost(weaponProperties);
            
            upgradeCost += (upgradedWeaponCost - currentWeaponCost);
            
            //LogUtil.put(LogFactory.getInstance(
              //      "UpgradedWeapon Cost: ").append(upgradedWeaponCost +
                //    " CurrentWeapon Cost: ").append(currentWeaponCost +
                  //  " Part Cost: " + upgradeCost, this, "getUpgradeCost"));
        }
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Total Cost: ").append(upgradeCost).toString(), this, "getUpgradeCost"));

        return upgradeCost;
    }
    
    public final void destroyAndClear(BasicArrayList list)
        throws Exception
    {
        RTSLayer rtsLayer;
        for (int index = list.size() - 1; index >= 0; index--)
        {
            rtsLayer = (RTSLayer) list.get(index);
            rtsLayer.setDestroyed(true);
        }
        
        list.clear();
    }
    
    public final Hashtable createFakeRTSLayerHashtable(
            final BaseRaceTrackGeographicMap baseRaceTrackGeographicMap)
    {
        Hashtable hashtable = new Hashtable();
        
        final FakeLayerManager layerManager = new FakeLayerManager(null);
        layerManager.setGeographicMapInterface(new BasicGeographicMap[] { baseRaceTrackGeographicMap});
        hashtable.put(AllBinaryGameLayerManager.ID, layerManager);
        
        hashtable.put(DirectionFactory.getInstance().NAME, 
                DirectionFactory.getInstance().LEFT);
        
        hashtable.put(Group.ID, BasicGroupFactory.getInstance().NONE_ARRAY);
        
        return hashtable;
    }
    
}
