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
package org.allbinary.game.score;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
    
import org.allbinary.game.GameInfo;
import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.persistance.PlatformRecordIdUtil;

public class HighScoreNamePersistanceSingleton
{
    private static HighScoreNamePersistanceSingleton SINGLETON = new HighScoreNamePersistanceSingleton();

    public static HighScoreNamePersistanceSingleton getInstance()
    {
        return SINGLETON;
    }

    private final PlatformRecordIdUtil platformRecordIdUtil = PlatformRecordIdUtil.getInstance();
    
    //_Saved_Name
    private final String RECORD_ID = "_SN";
    private String name = StringUtil.getInstance().EMPTY_STRING;
    private BasicArrayList nameBasicArrayList = new BasicArrayList();

    // private BasicArrayList list = new BasicArrayList();

    public void clear()
    {
        this.name = StringUtil.getInstance().EMPTY_STRING;
    }

    public void deleteAll(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo) throws Exception
    {
        int size = nameBasicArrayList.size();
        for (int index = 0; index < size; index++)
        {
            final Integer integer = (Integer) this.nameBasicArrayList.objectArray[index];
            this.delete(abeClientInformation, gameInfo, integer.intValue());
        }

        this.clear();
    }
    
    private String getRecordId(final AbeClientInformationInterface abeClientInformation) {
        return platformRecordIdUtil.getRecordId(abeClientInformation, RECORD_ID);
    }
    
    public void delete(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo, final int deleteId) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Deleting: ").append(deleteId).toString(), this, "delete"));

        final RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        recordStore.deleteRecord(deleteId);

        recordStore.closeRecordStore();
    }

    public BasicArrayList getIds()
    {
        return this.nameBasicArrayList;
    }

    public String load(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo)
    {
        final String LOAD = "load";
        
        try
        {
            // If not loaded try loading
            if (this.name == StringUtil.getInstance().EMPTY_STRING)
            {
                final String LOADING_ID = "Loading id: ";
                
                final RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);
                
                RecordEnumeration recordEnum = recordStore.enumerateRecords(null, null, true);

                final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
                
                byte[] recordAsBytes;
                ByteArrayInputStream byteArrayInputStream;
                DataInputStream inputStream;
                while (recordEnum.hasNextElement())
                {
                    final int id = recordEnum.nextRecordId();

                    LogUtil.put(LogFactory.getInstance(new StringMaker().append(LOADING_ID).append(id).toString(), this, LOAD));

                    recordAsBytes = recordStore.getRecord(id);
                    byteArrayInputStream = new ByteArrayInputStream(recordAsBytes);
                    inputStream = new DataInputStream(byteArrayInputStream);

                    //PreLogUtil.put("inputStream.available(): " + inputStream.available(), this, LOAD);
                    while (inputStream.available() > 0)
                    {
                        this.name = inputStream.readUTF();
                    }

                    nameBasicArrayList.add(smallIntegerSingletonFactory.getInstance(id));
                }

                recordStore.closeRecordStore();
            }
        } catch (Exception e)
        {
            this.save(abeClientInformation, gameInfo, this.name);
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(ExceptionUtil.getInstance().getStackTrace(e)).toString(), this, LOAD));
        }
        return this.name;
    }

    public void save(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo, final String name)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Saving: ").append(name).toString(), this, "save"));

            final RecordStore recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

            outputStream.writeUTF(name);

            //final byte[] savedGameBytes = byteArrayOutputStream.toString().getBytes();
            final byte[] savedGameBytes = byteArrayOutputStream.toByteArray();

            recordStore.addRecord(savedGameBytes, 0, savedGameBytes.length);

            recordStore.closeRecordStore();
            
            this.name = name;
            
        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "save", e));
        }
    }
}
