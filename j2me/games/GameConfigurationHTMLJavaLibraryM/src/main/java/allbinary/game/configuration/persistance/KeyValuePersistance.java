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
package allbinary.game.configuration.persistance;

import java.util.Hashtable;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

import org.allbinary.util.HashtableUtil;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class KeyValuePersistance extends BasicPersitance
{    
    protected KeyValuePersistance(String recordId)
    {
        super(recordId);
    }
        
    public void loadAll() throws Exception
    {
        this.loadAll(1);
    }
    
    public void loadAll(int size) throws Exception
    {
        RecordStore recordStore = RecordStore.openRecordStore(
                this.getRecordStoreName(), true);

        RecordEnumeration recordEnum = recordStore.enumerateRecords(null, null,
                true);

        final String LOADING_ID = "Loading id: ";
        final String METHOD_NAME = "loadAll";
        
        //ByteArrayInputStream byteArrayInputStream;
        //DataInputStream inputStream;
        Hashtable hashtable;
        
        String name;
        String value;

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        while (recordEnum.hasNextElement())
        {
            int id = recordEnum.nextRecordId();

            LogUtil.put(LogFactory.getInstance(LOADING_ID + id, this, METHOD_NAME));
            
            //byteArrayInputStream = 
              //  new ByteArrayInputStream(recordStore.getRecord(id));
            //inputStream = new DataInputStream(byteArrayInputStream);
            hashtable = new Hashtable();

            for (int index = 0; index < size; index++)
            {
                //name = inputStream.readUTF();
                //inputStream.readUTF();
                //value = inputStream.readUTF();
                //hashtable.put(name, value);
            }

            this.getList().add(hashtable);
            this.getIds().add(smallIntegerSingletonFactory.getInstance(id));
        }

        recordStore.closeRecordStore();
    }
    
    public void save(Hashtable hashtable) throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Saving: " + hashtable, this, "save"));
        
        RecordStore recordStore = RecordStore.openRecordStore(
                this.getRecordStoreName(), true);

        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //DataOutputStream outputStream = new DataOutputStream(
          //      byteArrayOutputStream);

        String value;
        
        CommonSeps commonSeps = CommonSeps.getInstance();
        
        Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        int size = objectArray.length;
        for (int index = 0; index < size; index++)
        {
            //outputStream.writeUTF((String) objectArray[index]);
            //outputStream.writeUTF(commonSeps.EQUALS);
            value = (String) hashtable.get(objectArray[index]);
            //outputStream.writeUTF(value);
        }

        //byte[] savedGameBytes = byteArrayOutputStream.toString().getBytes();

        //recordStore.addRecord(savedGameBytes, 0, savedGameBytes.length);

        recordStore.closeRecordStore();
    }

    public Hashtable get(int index)
    {
        return (Hashtable) this.getList().objectArray[index];
    }
}
