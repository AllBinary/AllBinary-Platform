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
package org.allbinary.media.graphics.geography.map.topview;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCellTypeFactory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellType;
import org.allbinary.util.BasicArrayList;

public class BasicTopViewGeographicMapCellType //extends GeographicMapCellType
{
    public final String name;
    public final int cost;
    private final int[] types;

//    public BasicTopViewGeographicMapCellType(final int type) {
//        //super(type);
//        new GeographicMapCellType(type);
//        this.types = new int[1];
//        this.types[0] = type;
//    }
//    
//    public BasicTopViewGeographicMapCellType(final int[] types) {
//        //super(Integer.MIN_VALUE);
//        
//        final int size = types.length;
//        for(int index = 0; index < size; index++) {
//            new GeographicMapCellType(types[index]);
//        }
//        this.types = types;
//    }
//
//    public BasicTopViewGeographicMapCellType(final BasicArrayList types) {
//        //super(Integer.MIN_VALUE);
//        
//        final int size = types.size();
//        final int[] typeArray = new int[size];
//        int type;
//        for(int index = 0; index < size; index++) {
//            type = ((Integer) types.get(index)).intValue();
//            new GeographicMapCellType(type);
//            typeArray[index] = type;
//        }
//        
//        this.types = typeArray;
//    }

    public BasicTopViewGeographicMapCellType(final String name, final int type, final int cost) {
        //super(type);
        if(GeographicMapCellTypeFactory.getInstance().getGeographicMapCellTypeArray()[type] == null) {
            new RaceTrackGeographicMapCellType(name, type, cost);
        } else {
            //final BasicTopViewGeographicMapStrings basicTopViewGeographicMapStrings = BasicTopViewGeographicMapStrings.getInstance();
            //LogUtil.put(LogFactory.getInstance(basicTopViewGeographicMapStrings.ALREADY_EXISTS + type, this, CommonStrings.getInstance().CONSTRUCTOR));
        }

        this.name = name;
        this.cost = cost;
        this.types = new int[1];
        this.types[0] = type;
    }
    
    public BasicTopViewGeographicMapCellType(final String name, final int[] types, final int cost) {
        //super(Integer.MIN_VALUE);
        
        final int size = types.length;
        int type;
        for(int index = 0; index < size; index++) {
            
            type = types[index];
            if (GeographicMapCellTypeFactory.getInstance().getGeographicMapCellTypeArray()[type] == null) {
                new RaceTrackGeographicMapCellType(name, type, cost);
            } else {
                //final BasicTopViewGeographicMapStrings basicTopViewGeographicMapStrings = BasicTopViewGeographicMapStrings.getInstance();
                //LogUtil.put(LogFactory.getInstance(basicTopViewGeographicMapStrings.ALREADY_EXISTS + type, this, CommonStrings.getInstance().CONSTRUCTOR));
            }
            
        }
        this.name = name;
        this.types = types;
        this.cost = cost;
    }

    public BasicTopViewGeographicMapCellType(final String name, final BasicArrayList types, final int cost) {
        //super(Integer.MIN_VALUE);
        
        final int size = types.size();
        final int[] typeArray = new int[size];
        int type;
        for(int index = 0; index < size; index++) {
            type = ((Integer) types.get(index)).intValue();
            
            if (GeographicMapCellTypeFactory.getInstance().getGeographicMapCellTypeArray()[type] == null) {
                new RaceTrackGeographicMapCellType(name, type, cost);
            } else {
                //final BasicTopViewGeographicMapStrings basicTopViewGeographicMapStrings = BasicTopViewGeographicMapStrings.getInstance();
                //LogUtil.put(LogFactory.getInstance(basicTopViewGeographicMapStrings.ALREADY_EXISTS + type, this, CommonStrings.getInstance().CONSTRUCTOR));
            }
            
            typeArray[index] = type;
        }
        
        this.name = name;
        this.cost = cost;
        this.types = typeArray;
    }
    
    public boolean isType(final GeographicMapCellType type) {
        return this.isType(type.getType());
    }

    public boolean isType(final int type) {
        final int size = types.length;
        for(int index = 0; index < size; index++) {
            if(types[index] == type) {
                return true;
            }
        }
        return false;
    }
    
    public int[] getTypes() {
        return this.types;
    }
    
    public String toString() {
        final StringMaker stringMaker = new StringMaker();

        final int size = types.length;
        for(int index = 0; index < size; index++) {
            stringMaker.append(types[index]).append(CommonSeps.getInstance().COMMA);
        }        

        return stringMaker.toString();
    }
}
