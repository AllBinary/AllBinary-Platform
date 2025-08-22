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
package org.allbinary.game.configuration.persistance;

/**
 *
 * @author User
 */
public class GamePersistanceStrings {
    
    private static final GamePersistanceStrings instance = new GamePersistanceStrings();

    /**
     * @return the instance
     */
    public static GamePersistanceStrings getInstance() {
        return instance;
    }
    
    public final String SAVED_GAME_RECORD_ID = "_SG";
    public final String SAVED_GAME_CONFIGURATION_RECORD_ID = "_SGC";    
 
    public final String DEFAULT_INPUT_MAPPING_RECORD_ID = "_DIM";
    public final String SAVED_INPUT_CONFIGURATION_RECORD_ID = "_SIC";
    
}
