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

import jsinterop.annotations.JsType;

import javax.microedition.rms.RecordStore;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.persistance.PlatformRecordIdUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class BasicPersitance
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    @JsProperty
    protected final PersistanceStrings persistanceStrings = PersistanceStrings.getInstance();
    private final PlatformRecordIdUtil platformRecordIdUtil = PlatformRecordIdUtil.getInstance();
    
    private final String recordId;
    
    @JsProperty
    protected final BasicArrayList valueList = new BasicArrayListD();
    @JsProperty
    protected final BasicArrayList idList = new BasicArrayListD();
    
    @JsConstructor
    protected BasicPersitance(final String recordId)
    {
        this.recordId = recordId;
    }

    @JsMethod
    public void deleteRecoreStore(final AbeClientInformationInterface abeClientInformation) throws Exception 
    {
        RecordStore.deleteRecordStore(this.getRecordId(abeClientInformation));
    }
    
    //Load all needs to be called already
    @JsMethod
    public void deleteAll(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        int size = this.idList.size();
        for(int index = 0; index < size; index++)
        {
            Integer integer = (Integer) this.idList.objectArray[index];
            this.delete(abeClientInformation, integer.intValue());
        }
        
        this.clear();
    }
    
    @JsMethod
    public void delete(final AbeClientInformationInterface abeClientInformation, final int deleteId) throws Exception
    {
        RecordStore recordStore = NullRecordStore.NULL_RECORD_STORE;
        
        try {
            
        this.logUtil.putF(new StringMaker().append(this.persistanceStrings.DELETING_WITH_ID).appendint(deleteId).toString(), this, this.commonStrings.delete);
        
        recordStore = RecordStore.openRecordStore(this.getRecordId(abeClientInformation), true);

        recordStore.deleteRecord(deleteId);

        } catch(Exception e) {
            throw e;
        } finally {
            if(recordStore != null) {
                PreLogUtil.put(this.persistanceStrings.CLOSING_RECORDSTORE, this, this.commonStrings.delete);
                recordStore.closeRecordStore();
            }
        }

    }
    
    @JsMethod
    public String getRecordId(final AbeClientInformationInterface abeClientInformation) {
        return this.platformRecordIdUtil.getRecordId(abeClientInformation, this.recordId);
    }

    @JsMethod
    public BasicArrayList getList()
    {
        return this.valueList;
    }

    @JsMethod
    public BasicArrayList getIds()
    {
        return this.idList;
    }

    @JsMethod
    public void clear()
    {
        this.valueList.clear();
        this.idList.clear();
    }
}
