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

import javax.microedition.rms.RecordComparator;

/**
 *
 * @author User
 */
public class NullRecordComparator implements RecordComparator {
    
    public static final NullRecordComparator NULL_RECORD_COMPARATOR = new NullRecordComparator();
    
    @Override
    public int compare(byte[] rec1, byte[] rec2) {
        return 0;
    }

}
