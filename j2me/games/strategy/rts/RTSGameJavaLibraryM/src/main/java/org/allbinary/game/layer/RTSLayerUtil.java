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


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.GameInfo;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.part.PartInterface;
import org.allbinary.game.part.weapon.BasicWeaponPart;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.racetrack.BaseRaceTrackGeographicMap;

public class RTSLayerUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
                weaponProperties.getSpeed().getUnscaled(), 
                weaponProperties.getDamage() / lastLevel * currentLevel, 
                weaponProperties.getDissipation()
                );

        //logUtil.put(
          //      "WeaponProperties: ").append(newWeaponProperties, 
            //    this, "createWeaponProperties");

        return newWeaponProperties;
    }

    public void downgrade(final RTSLayer rtsLayer)
    {
        rtsLayer.setLevel(rtsLayer.getLevel() - 1);

        final PartInterface[] partInterfaceArray = rtsLayer.getPartInterfaceArray();
        final int size = partInterfaceArray.length;
        BasicWeaponPart partInterface;
        for (int index = size; --index >= 0;)
        {
            partInterface = (BasicWeaponPart) partInterfaceArray[index];

            partInterface.setWeaponProperties(
                    this.createWeaponProperties(partInterface
                    .getWeaponProperties(), rtsLayer.getLevel(), rtsLayer.getLevel() + 1));
        }
        rtsLayer.select();
    }

    public void upgrade(final RTSLayer rtsLayer)
    {
        rtsLayer.setLevel(rtsLayer.getLevel() + 1);

        final PartInterface[] partInterfaceArray = rtsLayer.getPartInterfaceArray();
        final int size = partInterfaceArray.length;
        BasicWeaponPart partInterface;
        for (int index = size; --index >= 0;)
        {
            partInterface = (BasicWeaponPart) partInterfaceArray[index];

            partInterface.setWeaponProperties(
                    this.createWeaponProperties(partInterface
                    .getWeaponProperties(), rtsLayer.getLevel(), rtsLayer.getLevel() - 1));
        }
        rtsLayer.select();
    }
    
    private final long MAX_RELOAD_TIME = (long) Integer.MAX_VALUE / 100000;
    //1000 = 1000 10000 = 100000
    public long getCostExponential(final long cost)
    {
        return ((cost * cost * cost) / (cost * 1000));
    }

    private int getWeaponPropertiesCost(final WeaponProperties weaponProperties)
    {
        //logUtil.put(
          //      "Damage: ").append(weaponProperties.getDamage() +
            //    "+Range: ").append(weaponProperties.getRange() +
              //  "+Reload: ").append((MAX_RELOAD_TIME / weaponProperties.getReloadTime()), 
                //this, "getWeaponPropertiesCost");

        //I artificially reduce the cost of reload time since they miss a bunch
        long cost = (weaponProperties.getDamage() + weaponProperties.getRange() + 
                ((MAX_RELOAD_TIME / weaponProperties.getReloadTime()) >> 1));

        //logUtil.put("Pre Cost: ").append(cost, this, "getWeaponPropertiesCost");
        cost = this.getCostExponential(cost);
        //logUtil.put("Cost: ").append(cost, this, "getWeaponPropertiesCost");
        return (int) cost;
    }
    
    public int getCost(RTSLayer rtsLayer)
    {
        int total = 0;

        final PartInterface[] partInterfaceArray = rtsLayer.getPartInterfaceArray();
        final int size = partInterfaceArray.length;
        
        BasicWeaponPart partInterface;
        WeaponProperties weaponProperties;
        for (int index = size - 1; index >= 0; index--)
        {
            partInterface = (BasicWeaponPart) partInterfaceArray[index];
            weaponProperties = partInterface.getWeaponProperties();
            total += this.getWeaponPropertiesCost(weaponProperties);
        }

        return total;
    }

    public int getDowngradeCost(final RTSLayer rtsLayer)
    {
        final PartInterface[] partInterfaceArray = rtsLayer.getPartInterfaceArray();
        final int size = partInterfaceArray.length;
        
        int downgradeCost = 0;
        BasicWeaponPart partInterface;
        WeaponProperties weaponProperties;
        int downgradeWeaponCost;
        int currentWeaponCost;
        for (int index = size - 1; index >= 0; index--)
        {
            partInterface = (BasicWeaponPart) partInterfaceArray[index];
            weaponProperties = partInterface.getWeaponProperties();

            downgradeWeaponCost =
                this.getWeaponPropertiesCost(
                    this.createWeaponProperties(
                            weaponProperties, 
                            rtsLayer.getLevel() - 1, rtsLayer.getLevel()));

            currentWeaponCost = 
                this.getWeaponPropertiesCost(weaponProperties);
                    
            downgradeCost += (currentWeaponCost - downgradeWeaponCost);
             
            //logUtil.put(
              //      "DowngradedWeapon Cost: ").append(downgradeWeaponCost +
                //    " CurrentWeapon Cost: ").append(currentWeaponCost +
                  //  " Part Cost: ").append(downgradeCost, this, "getDowngradeCost");
        }

        downgradeCost = downgradeCost * 9 / 10;
        
        logUtil.put(new StringMaker().append("Total Cost: ").append(downgradeCost).toString(), this, "getDowngradeCost");

        return downgradeCost;
    }

    public int getUpgradeCost(final RTSLayer rtsLayer)
    {
        int upgradeCost = 0;

        final PartInterface[] partInterfaceArray = rtsLayer.getPartInterfaceArray();
        final int size = partInterfaceArray.length;
        
        BasicWeaponPart partInterface;
        WeaponProperties weaponProperties;
        int upgradedWeaponCost;
        int currentWeaponCost;        
        for (int index = size - 1; index >= 0; index--)
        {
            partInterface = (BasicWeaponPart) partInterfaceArray[index];
            weaponProperties = partInterface.getWeaponProperties();

            upgradedWeaponCost = 
                this.getWeaponPropertiesCost(
                    this.createWeaponProperties(
                            weaponProperties, 
                            rtsLayer.getLevel() + 1, rtsLayer.getLevel()));
            
            currentWeaponCost = 
                this.getWeaponPropertiesCost(weaponProperties);
            
            upgradeCost += (upgradedWeaponCost - currentWeaponCost);
            
            //logUtil.put(
              //      "UpgradedWeapon Cost: ").append(upgradedWeaponCost +
                //    " CurrentWeapon Cost: ").append(currentWeaponCost +
                  //  " Part Cost: " + upgradeCost, this, "getUpgradeCost");
        }
        logUtil.put(new StringMaker().append("Total Cost: ").append(upgradeCost).toString(), this, "getUpgradeCost");

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
        
        final FakeLayerManager layerManager = new FakeLayerManager(GameInfo.NONE);
        layerManager.setGeographicMapInterface(new BasicGeographicMap[] { baseRaceTrackGeographicMap});
        hashtable.put(AllBinaryGameLayerManager.ID, layerManager);
        
        hashtable.put(DirectionFactory.getInstance().NAME, 
                DirectionFactory.getInstance().LEFT);
        
        hashtable.put(Group.ID, BasicGroupFactory.getInstance().NONE_ARRAY);
        
        return hashtable;
    }
    
}
