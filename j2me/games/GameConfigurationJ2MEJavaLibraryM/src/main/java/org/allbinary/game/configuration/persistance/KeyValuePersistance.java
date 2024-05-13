/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Hashtable;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

import org.allbinary.util.HashtableUtil;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class KeyValuePersistance extends BasicPersitance
{    
    protected KeyValuePersistance(final String recordId)
    {
        super(recordId);
    }
        
    public void loadAll(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        this.loadAll(abeClientInformation, 1);
    }
    
    public void loadAll(final AbeClientInformationInterface abeClientInformation, int size) throws Exception
    {
        final RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        final RecordEnumeration recordEnum = recordStore.enumerateRecords(null, null,
                true);

        final String LOADING_ID = "Loading id: ";
        final String METHOD_NAME = "loadAll";
        
        Hashtable hashtable;
        
        String name;
        String value;

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        byte[] recordAsBytes;
        ByteArrayInputStream byteArrayInputStream;
        DataInputStream inputStream;        
        while (recordEnum.hasNextElement())
        {
            final int id = recordEnum.nextRecordId();

            LogUtil.put(LogFactory.getInstance(new StringMaker().append(LOADING_ID).append(id).toString(), this, METHOD_NAME));
            
            recordAsBytes = recordStore.getRecord(id);
            if(recordAsBytes != null) {
                byteArrayInputStream = new ByteArrayInputStream(recordAsBytes);
                inputStream = new DataInputStream(byteArrayInputStream);
                hashtable = new Hashtable();

                for (int index = 0; index < size; index++) {
                    name = inputStream.readUTF();
                    inputStream.readUTF();
                    value = inputStream.readUTF();
                    hashtable.put(name, value);
                }

                this.getList().add(hashtable);
                this.getIds().add(smallIntegerSingletonFactory.getInstance(id));
            }
        }

        recordStore.closeRecordStore();
    }
    
    public void save(final AbeClientInformationInterface abeClientInformation, final Hashtable hashtable) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Saving: ").append(hashtable).toString(), this, "save"));
        
        final RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream outputStream = new DataOutputStream(
                byteArrayOutputStream);

        String value;
        
        final CommonSeps commonSeps = CommonSeps.getInstance();
        
        final Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        final int size = objectArray.length;
        for (int index = 0; index < size; index++)
        {
            outputStream.writeUTF((String) objectArray[index]);
            outputStream.writeUTF(commonSeps.EQUALS);
            value = (String) hashtable.get(objectArray[index]);
            outputStream.writeUTF(value);
        }

        final byte[] savedGameBytes = byteArrayOutputStream.toString().getBytes();

        recordStore.addRecord(savedGameBytes, 0, savedGameBytes.length);

        recordStore.closeRecordStore();
    }

    public Hashtable get(int index)
    {
        return (Hashtable) this.getList().objectArray[index];
    }
}
