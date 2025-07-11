/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

/**
 *
 * @author User
 */
public class BasicTopViewGeographicMapStrings {

    private static final BasicTopViewGeographicMapStrings instance = new BasicTopViewGeographicMapStrings();

    /**
     * @return the instance
     */
    public static BasicTopViewGeographicMapStrings getInstance() {
        return instance;
    }
    
    public final String DEFAULT = "Default";
    public final String OTHER = "Other";
    public final String WALL = "Wall";
    public final String OFF_MAP = "OffMap";
    public final String FLOOR = "Floor";
    public final String DOOR = "Door";
    public final String STAIRS_UP = "StairsUp";
    public final String STAIRS_DOWN = "StairsDown";
    
    //public final String ALREADY_EXISTS = "GeographicMapCellType already exists: ";
}
