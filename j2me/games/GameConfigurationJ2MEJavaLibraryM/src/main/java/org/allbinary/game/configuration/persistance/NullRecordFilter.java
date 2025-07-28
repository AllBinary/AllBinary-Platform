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

import javax.microedition.rms.RecordFilter;

/**
 *
 * @author User
 */
public class NullRecordFilter implements RecordFilter {
    
    public static NullRecordFilter NULL_RECORD_FILTER = new NullRecordFilter();
    
    @Override
    public boolean matches(byte[] candidate) {
        return true;
    }

}
