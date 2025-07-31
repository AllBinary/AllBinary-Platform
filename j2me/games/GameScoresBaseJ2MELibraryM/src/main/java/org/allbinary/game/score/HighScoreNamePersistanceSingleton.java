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
import javax.microedition.rms.RecordStoreException;

import org.allbinary.game.GameInfo;
import org.allbinary.game.configuration.persistance.NullRecordComparator;
import org.allbinary.game.configuration.persistance.NullRecordFilter;
import org.allbinary.game.configuration.persistance.NullRecordStore;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.persistance.PlatformRecordIdUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class HighScoreNamePersistanceSingleton
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static HighScoreNamePersistanceSingleton SINGLETON = new HighScoreNamePersistanceSingleton();

    public static HighScoreNamePersistanceSingleton getInstance()
    {
        return SINGLETON;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
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
        RecordStore recordStore = NullRecordStore.NULL_RECORD_STORE;
        try {

        logUtil.put(new StringMaker().append("Deleting: ").append(deleteId).toString(), this, commonStrings.delete);

        recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        recordStore.deleteRecord(deleteId);

        } catch(Exception e) {
            throw e;
        } finally {
            if(recordStore != null) {
                PreLogUtil.put("Closing RecordStore", this, commonStrings.delete);
                recordStore.closeRecordStore();
            }
        }

    }

    public BasicArrayList getIds()
    {
        return this.nameBasicArrayList;
    }

    public String load(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo)
    {        
        RecordStore recordStore = NullRecordStore.NULL_RECORD_STORE;

        try
        {
            // If not loaded try loading
            if (this.name == StringUtil.getInstance().EMPTY_STRING)
            {
                final String LOADING_ID = "Loading id: ";
                
                recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);
                
                final RecordEnumeration recordEnum = recordStore.enumerateRecords(NullRecordFilter.NULL_RECORD_FILTER, NullRecordComparator.NULL_RECORD_COMPARATOR, true);

                final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
                
                byte[] recordAsBytes;
                ByteArrayInputStream byteArrayInputStream;
                DataInputStream inputStream;
                while (recordEnum.hasNextElement())
                {
                    final int id = recordEnum.nextRecordId();

                    logUtil.put(new StringMaker().append(LOADING_ID).append(id).toString(), this, commonStrings.LOAD);

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

            }
        } catch (Exception e)
        {
            this.save(abeClientInformation, gameInfo, this.name);
            logUtil.put(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(ExceptionUtil.getInstance().getStackTrace(e)).toString(), this, commonStrings.LOAD);
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, commonStrings.LOAD);
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.LOAD, e);
            }
        }

        return this.name;
    }

    public void save(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo, final String name)
    {
        RecordStore recordStore = NullRecordStore.NULL_RECORD_STORE;
        try
        {
            logUtil.put(new StringMaker().append("Saving: ").append(name).toString(), this, commonStrings.SAVE);

            recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);

            outputStream.writeUTF(name);

            //final byte[] savedGameBytes = byteArrayOutputStream.toString().getBytes();
            final byte[] savedGameBytes = byteArrayOutputStream.toByteArray();

            recordStore.addRecord(savedGameBytes, 0, savedGameBytes.length);

            this.name = name;
            
        } catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.SAVE, e);
        } finally {
            try {
                if (recordStore != null) {
                    PreLogUtil.put("Closing RecordStore", this, commonStrings.SAVE);
                    recordStore.closeRecordStore();
                }
            } catch(RecordStoreException e) {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.SAVE, e);
            }
        }

    }
}
