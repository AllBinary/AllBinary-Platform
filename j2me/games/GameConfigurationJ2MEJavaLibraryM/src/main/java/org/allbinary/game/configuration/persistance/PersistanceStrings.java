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
public class PersistanceStrings {
    
    protected static final PersistanceStrings instance = new PersistanceStrings();

    /**
     * @return the instance
     */
    public static PersistanceStrings getInstance() {
        return instance;
    }

    public final String SAVING = "Saving: ";
    public final String NUMBER_OF_RECORDS = "Number of records: ";
    
    public final String LOADING_ID = "Loading data with id: ";
    public final String LOAD_ALL = "loadAll";
    
    public final String DELETING_WITH_ID = "Deleting data with id: ";
    public final String CLOSING_RECORDSTORE = "Closing RecordStore";
    
    public final String NOT_SAVING = "Not Saving: ";

    public final String ERROR_LOADING = "Error Loading gameActionInput: ";
    public final String ERROR_LOADING_ID = "Error Loading id: ";
    public final String ID = " id: ";
    public final String GAME_ACTION_INPUT = " GameActionInput: ";
    
}
