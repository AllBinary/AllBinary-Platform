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
package org.allbinary.media.graphics.geography.map;

/**
 *
 * @author User
 */
public class GeographicMapCellHistoryProgressBase {
    
    private static final GeographicMapCellHistoryProgressBase instance = new GeographicMapCellHistoryProgressBase();

    /**
     * @return the instance
     */
    public static GeographicMapCellHistoryProgressBase getInstance() {
        return instance;
    }
    
    public GeographicMapCellHistoryProgressBase() {
    }

    public void init() {
        throw new RuntimeException();
    }

    public boolean isAnyProgress() {
        throw new RuntimeException();
    }
    
}
