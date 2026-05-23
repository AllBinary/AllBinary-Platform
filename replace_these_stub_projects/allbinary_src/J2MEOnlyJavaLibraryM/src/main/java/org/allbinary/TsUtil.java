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
package org.allbinary;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

import org.allbinary.logic.NullUtil;

/**
 *
 * @author User
 */
public class TsUtil {

    private static final TsUtil instance = new TsUtil();

    /**
     * @return the instance
     */
    public static TsUtil getInstance() {
        return TsUtil.instance;
    }

    public int hashCode(Object object) {
        return object.hashCode();
    }

    public void waitFor(Object any, final long timeoutMillis) throws InterruptedException {
        final Object object = (Object) any;
        object.wait(timeoutMillis);
    }

    public byte[] getRecord(final Object object, final int recordId) 
        throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
        final RecordStore recordStore = (RecordStore) object;
        
        byte[] data;

        synchronized (this) {
            data = new byte[recordStore.getRecordSize(recordId)];
            recordStore.getRecord(recordId, data, 0);
        }

        return data.length < 1 ? NullUtil.getInstance().NULL_BYTE_ARRAY : data;

    }

    public int compareTo(final String a, final String b) {
        return a.compareTo(b);
    }

    public boolean equalIgnoreCase(final String a, final String b) {
        return a.equalsIgnoreCase(b);
    }

    public ClassLoader getClassClassLoader(final Object object) {
        return object.getClass().getClassLoader();
    }
    
    public int toNumber(final char value) {
        return (int) value;
    }

    public int toNumber(final int value) {
        return value;
    }
    
    public boolean isItemListener(Displayable owner) {
        if (owner instanceof Form) {
            return true;
        } else {
            return false;
        }
    }
    
}
