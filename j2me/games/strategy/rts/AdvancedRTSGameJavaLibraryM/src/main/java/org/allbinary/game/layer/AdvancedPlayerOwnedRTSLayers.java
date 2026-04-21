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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class AdvancedPlayerOwnedRTSLayers
    extends PlayerOwnedRTSLayers
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final BasicArrayList unitsList = new BasicArrayListD();

    private final BasicArrayList waypointsList = new BasicArrayListD();

    private final BasicArrayList buildingList = new BasicArrayListD();
    
    private final BasicArrayList baseList = new BasicArrayListD();
    private final BasicArrayList garrisonList = new BasicArrayListD();
    private final BasicArrayList houseList = new BasicArrayListD();
    private final BasicArrayList labList = new BasicArrayListD();
    private final BasicArrayList factoryList = new BasicArrayListD();

    public void addUnit(RTSLayer rtsLayer)
    {
        this.unitsList.add(rtsLayer);
        this.rtsLayerList.add(rtsLayer);
    }

    public void addWaypoint(RTSLayer rtsLayer)
    {
        this.waypointsList.add(rtsLayer);
        this.rtsLayerList.add(rtsLayer);
    }

    public void addBase(RTSLayer rtsLayer)
    {
        this.baseList.add(rtsLayer);
        this.buildingList.add(rtsLayer);
        this.rtsLayerList.add(rtsLayer);
    }    

    public void addGarrison(RTSLayer rtsLayer)
    {
        this.garrisonList.add(rtsLayer);
        this.buildingList.add(rtsLayer);
        this.rtsLayerList.add(rtsLayer);
    }

    public void addHouse(RTSLayer rtsLayer)
    {
        this.houseList.add(rtsLayer);
        this.buildingList.add(rtsLayer);
        this.rtsLayerList.add(rtsLayer);
    }

    public void addLab(RTSLayer rtsLayer)
    {
        this.labList.add(rtsLayer);
        this.buildingList.add(rtsLayer);
        this.rtsLayerList.add(rtsLayer);
    }

    public void addFactory(RTSLayer rtsLayer)
    {
        this.factoryList.add(rtsLayer);
        this.buildingList.add(rtsLayer);
        this.rtsLayerList.add(rtsLayer);
    }
    
    public void removeUnit(AllBinaryGameLayer gameLayer)
    {
        this.unitsList.remove(gameLayer);
        this.rtsLayerList.remove(gameLayer);
    }

    public void removeWaypoint(AllBinaryGameLayer gameLayer)
    {
        this.waypointsList.remove(gameLayer);
        this.rtsLayerList.remove(gameLayer);
    }

    /*
    public void removeBase(AllBinaryGameLayer gameLayer)
    {
        this.baseList.remove(gameLayer);
        this.rtsLayerList.remove(gameLayer);
    }

    public void removeGarrison(AllBinaryGameLayer gameLayer)
    {
        this.garrisonList.remove(gameLayer);
        this.rtsLayerList.remove(gameLayer);
    }
    
    public void removeHouse(AllBinaryGameLayer layerInterface)
    {
        //final StringMaker stringBuffer = new StringMaker();

        //stringBuffer.append("removeHouse: ");
        //stringBuffer.append(layerInterface.getName());
        
        //this.logUtil.putF(stringBuffer.toString(), this, "removeHouse");
        
        this.houseList.remove(layerInterface);
        this.rtsLayerList.remove(layerInterface);
    }

    public void removeLab(AllBinaryGameLayer gameLayer)
    {
        this.labList.remove(gameLayer);
        this.rtsLayerList.remove(gameLayer);
    }
    
    public void removeFactory(AllBinaryGameLayer gameLayer)
    {
        this.factoryList.remove(gameLayer);
        this.rtsLayerList.remove(gameLayer);
    }
    */
    
    /**
     * @return the unitsList
     */
    public BasicArrayList getUnitsList()
    {
        return unitsList;
    }
    

    /**
     * @return the waypointsList
     */
    public BasicArrayList getWaypointsList()
    {
        return waypointsList;
    }
    
    /**
     * @return the baseList
     */
    public BasicArrayList getBaseList()
    {
        return baseList;
    }

    /**
     * @return the garrisonList
     */
    public BasicArrayList getGarrisonList()
    {
        return garrisonList;
    }

    /**
     * @return the houseList
     */
    public BasicArrayList getHouseList()
    {
        return houseList;
    }

    /**
     * @return the labList
     */
    public BasicArrayList getLabList()
    {
        return labList;
    }

    /**
     * @return the factoryList
     */
    public BasicArrayList getFactoryList()
    {
        return factoryList;
    }

    public int getTotalBuildings()
    {
        return baseList.size() + this.garrisonList.size() +
            this.houseList.size() + this.labList.size() + this.factoryList.size();
    }
    
    public void removeBuilding(AllBinaryGameLayer layerInterface)
    {
        this.buildingList.add(layerInterface);
        
        /*
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("removeBuilding: ");
        stringBuffer.append(layerInterface.getName());
        
        this.logUtil.putF(stringBuffer.toString(), this, "removeBuilding");
        */
        
        if(this.getBaseList().remove(layerInterface))
        {
            this.rtsLayerList.remove(layerInterface);
        }
        else
            if(this.getFactoryList().remove(layerInterface))
            {
                this.rtsLayerList.remove(layerInterface);
            }
            else
                if(this.getGarrisonList().remove(layerInterface))
                {
                    this.rtsLayerList.remove(layerInterface);
                }
                else
                    if(this.getLabList().remove(layerInterface))
                    {
                        this.rtsLayerList.remove(layerInterface); 
                    }
                    else
                        if(this.getHouseList().remove(layerInterface))
                        {
                            this.rtsLayerList.remove(layerInterface);
                        }
    }
 
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(super.toString());
        stringBuffer.append(" Bases: ");
        stringBuffer.appendint(this.getBaseList().size());
        stringBuffer.append(" Factory: ");
        stringBuffer.appendint(this.getFactoryList().size());
        stringBuffer.append(" Garrison: ");
        stringBuffer.appendint(this.getGarrisonList().size());
        stringBuffer.append(" Labs: ");
        stringBuffer.appendint(this.getLabList().size());
        stringBuffer.append(" Houses: ");
        stringBuffer.appendint(this.getHouseList().size());
        stringBuffer.append(" Waypoints: ");
        stringBuffer.appendint(this.getWaypointsList().size());
        stringBuffer.append(" Units: ");
        stringBuffer.appendint(this.getUnitsList().size());

        return stringBuffer.toString();
    }

    public BasicArrayList getBuildingList()
    {
        return buildingList;
    }
    
}
