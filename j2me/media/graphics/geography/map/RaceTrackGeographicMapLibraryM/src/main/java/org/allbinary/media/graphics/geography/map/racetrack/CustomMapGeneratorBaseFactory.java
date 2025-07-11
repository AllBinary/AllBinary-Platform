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
package org.allbinary.media.graphics.geography.map.racetrack;

/**
 *
 * @author User
 */
public class CustomMapGeneratorBaseFactory {
    
    private static final CustomMapGeneratorBaseFactory instance = new CustomMapGeneratorBaseFactory();

    /**
     * @return the instance
     */
    public static CustomMapGeneratorBaseFactory getInstance() {
        return instance;
    }
    
    public CustomMapGeneratorBaseFactory() {
        
    }
    
    public CustomMapGeneratorBase getInstance(final RaceTrackGeographicMap raceTrackGeographicMap) throws Exception {
        return new CustomMapGeneratorBase();
    }

}
