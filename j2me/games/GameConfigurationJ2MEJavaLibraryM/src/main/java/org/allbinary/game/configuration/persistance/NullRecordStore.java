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

import javax.microedition.rms.RecordStore;

/**
 *
 * @author User
 */
public class NullRecordStore extends RecordStore {
    
    public static NullRecordStore NULL_RECORD_STORE = new NullRecordStore();
}
