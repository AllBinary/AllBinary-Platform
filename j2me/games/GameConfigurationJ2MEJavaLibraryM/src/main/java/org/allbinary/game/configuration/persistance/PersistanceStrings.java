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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class PersistanceStrings {
    
    private static final PersistanceStrings instance = new PersistanceStrings();

    /**
     * @return the instance
     */
    @JsMethod
    public static PersistanceStrings getInstance() {
        return PersistanceStrings.instance;
    }

    @JsProperty
    public final String SAVING = "Saving: ";
    @JsProperty
    public final String NUMBER_OF_RECORDS = "Number of records: ";
    
    @JsProperty
    public final String LOADING_ID = "Loading data with id: ";
    @JsProperty
    public final String LOAD_ALL = "loadAll";
    
    @JsProperty
    public final String DELETING_WITH_ID = "Deleting data with id: ";
    @JsProperty
    public final String CLOSING_RECORDSTORE = "Closing RecordStore";
    
    @JsProperty
    public final String NOT_SAVING = "Not Saving: ";

    @JsProperty
    public final String ERROR_LOADING = "Error Loading gameActionInput: ";
    @JsProperty
    public final String ERROR_LOADING_ID = "Error Loading id: ";
    @JsProperty
    public final String ID = " id: ";
    @JsProperty
    public final String GAME_ACTION_INPUT = " GameActionInput: ";
    
}
